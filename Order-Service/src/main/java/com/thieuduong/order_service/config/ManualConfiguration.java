package com.thieuduong.order_service.config;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration(proxyBeanMethods = false)
public class ManualConfiguration {
//	private final KafkaTemplate kafkaTemplate;
//
//	@PostConstruct
//	void setup() {
//		this.kafkaTemplate.setObservationEnabled(true);
//	}
}
