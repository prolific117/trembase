package com.tremendoc.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({"com.tremendoc.Controllers", "com.tremendoc.security", "com.tremendoc.Entity",
                "com.tremendoc.Request","com.tremendoc.security", "com.tremendoc.Filters",
                "com.tremendoc.Routes"})
@EntityScan("com.tremendoc.Entity")
@EnableJpaRepositories("com.tremendoc.Entity.Repository")
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/tremendoc/api");
        SpringApplication.run(Application.class, args);
    }
}
