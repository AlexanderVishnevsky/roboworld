package com.roboworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication

public class RoboworldApplication extends SpringBootServletInitializer {

    private static Class<RoboworldApplication> applicationClass = RoboworldApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(RoboworldApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }


}