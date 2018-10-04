package com.exposit.datetime_test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
public class AppConfig
{
    @Value("${app.timezone}")
    private String timeZone;

    @PostConstruct
    void started()
    {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        System.out.println("Default TZ: " + TimeZone.getDefault());
    }
}
