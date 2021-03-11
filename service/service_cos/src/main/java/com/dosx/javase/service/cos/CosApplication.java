package com.dosx.javase.service.cos;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {MybatisPlusAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.dosx.javase"})
public class CosApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(CosApplication.class, args);
    }
}
