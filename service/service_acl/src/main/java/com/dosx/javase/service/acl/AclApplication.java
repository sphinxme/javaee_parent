package com.dosx.javase.service.acl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lucky us
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.dosx.javase"})
public class AclApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AclApplication.class, args);
    }

}
