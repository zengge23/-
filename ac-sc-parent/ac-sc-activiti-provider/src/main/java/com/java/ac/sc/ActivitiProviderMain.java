package com.java.ac.sc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.java.ac.sc.mapper")
public class ActivitiProviderMain {
	public static void main(String[] args) {
		SpringApplication.run(ActivitiProviderMain.class,args);
	}
}
