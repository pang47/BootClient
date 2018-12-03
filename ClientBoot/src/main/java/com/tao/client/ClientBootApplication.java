package com.tao.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@SpringBootApplication
@EnableDubbo
public class ClientBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientBootApplication.class, args);
	}
}
