package com.shier.schdule.util;

import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HostnameSchedulerFactoryBean extends SchedulerFactoryBean {

    private Logger logger = LoggerFactory.getLogger(HostnameSchedulerFactoryBean.class);
    /**
     * 允许的主机列表，如果不设置则定时器正常工作
     */
    private List<String> hosts;

    public void afterPropertiesSet() throws IOException, SchedulerException, Exception {
        if (!this.isTimerHost()) {
            this.setTriggers(new Trigger[0]);
        }

        super.afterPropertiesSet();
    }

    public boolean isTimerHost() {
        if (hosts != null) {
            boolean allowed = false;
            String _hostName = "";

            try {
                //获取本机地址 modified by zhangzhaoxing
                //InetAddress localHost = InetAddress.getLocalHost();
                Set<String> serverIpList = this.getLocalSiteIPList();
                for (String hostName : hosts) {
                    _hostName = hostName;
                    InetAddress parseHost = InetAddress.getByName(hostName);
                    if (serverIpList.contains(parseHost.getHostAddress())) {
                        allowed = true;
                        logger.info("Timer Host matched:{}" + _hostName);
                        break;
                    }
                }

                if (!allowed) {
                    logger.info("定时器指定主机 =" + hosts + " 本主机:" + serverIpList + " 非调度器主机(注:金融云不能获取外网IP)!");
                }
                //end modified

//				InetAddress localHost = InetAddress.getLocalHost();
//				if (hosts.contains(localHost.getCanonicalHostName())) {
//					allowed = true;
//				} else if (hosts.contains(localHost.getHostName())) {
//					allowed = true;
//				} else if (hosts.contains(localHost.getHostAddress())) {
//					allowed = true;
//				}
//				if (!allowed) {
//					logger.info("Timer Hosts retricted ="+hosts + " This Host:"+localHost+" Not Timer Host!");
//					System.out.println("Timer Hosts retricted ="+hosts + " This Host:"+localHost+" Not Timer Host!");
//				}
            } catch (SocketException e) {
                logger.error("预定主机：[" + _hostName + "]绑定的定时器 不能使用.", e);
            } catch (UnknownHostException e) {
                logger.debug("定时器错误", e);
                logger.info("预定主机：[" + _hostName + "]绑定的定时器 不能使用.");
            } catch (Exception e) {
                logger.error("预定主机：[" + _hostName + "]绑定的定时器 不能使用.", e);
            }

            return allowed;
        } else
            throw new RuntimeException("Host's list is empty!");
    }

    private Set<String> getLocalSiteIPList() throws SocketException {
        Set<String> set = new HashSet<String>();
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
            Enumeration<InetAddress> ia = ni.getInetAddresses();
            while (ia.hasMoreElements()) {
                InetAddress ip = (InetAddress) ia.nextElement();
                if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    set.add(ip.getHostAddress());
                }
            }
        }

        return set;
    }


    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

}

