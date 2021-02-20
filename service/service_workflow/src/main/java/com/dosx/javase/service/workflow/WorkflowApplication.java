package com.dosx.javase.service.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author me and my Dokky
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.dosx.javase"})
public class WorkflowApplication
{
    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }
}
