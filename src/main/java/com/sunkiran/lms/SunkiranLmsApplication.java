package com.sunkiran.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SunkiranLmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunkiranLmsApplication.class, args);
	}

}
