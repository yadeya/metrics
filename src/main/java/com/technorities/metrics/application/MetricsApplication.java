package com.technorities.metrics.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class MetricsApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(MetricsApplication.class, args);
		} catch (Exception e) {
			log.error("App failed", e);
		}
	}
}
