package com.highwayns.loginservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.highwayns.loginservice"})
public class LoginserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginserviceApplication.class, args);
	}

}
