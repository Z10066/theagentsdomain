package com.highwayns.highwaymq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HighwaymqApplication {

	public static void main(String[] args) {
		SpringApplication.run(HighwaymqApplication.class, args);
	}

}
