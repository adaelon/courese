package com.zjx.courese.peerevaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PeerEvaluationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeerEvaluationApplication.class, args);
	}

}
