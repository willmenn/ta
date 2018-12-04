package com.example.zipkin.server.sever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class SeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeverApplication.class, args);
	}
}
