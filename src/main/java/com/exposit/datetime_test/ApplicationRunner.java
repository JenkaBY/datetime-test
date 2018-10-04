package com.exposit.datetime_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.exposit.datetime_test.model")
@EnableJpaRepositories({"com.exposit.datetime_test.repository"})
public class ApplicationRunner
{
    private static final String UTC = "UTC";

    public static void main(String[] args)
    {
        SpringApplication.run(ApplicationRunner.class, args);
    }
}
