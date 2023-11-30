package com.zjx.courese.peerevaluation;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoDataSourceProxy
public class PeerEvaluationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeerEvaluationApplication.class, args);
	}

}
