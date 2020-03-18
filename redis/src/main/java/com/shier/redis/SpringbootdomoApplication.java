package com.shier.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:spring/applicationContext.xml")
@SpringBootApplication
public class SpringbootdomoApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootdomoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdomoApplication.class, args);
    }
}

