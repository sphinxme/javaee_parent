package com.dosx.javase.service.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.dosx.javase"})
public class HRApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HRApplication.class, args);
    }
}
