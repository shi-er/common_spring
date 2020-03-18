package com.shier.schdule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:schdule.xml")
@SpringBootApplication
public class SchduleAplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SchduleAplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SchduleAplication.class, args);
    }
}
