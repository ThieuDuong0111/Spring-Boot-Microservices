//package com.thieuduong.order_service.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.KafkaTemplate;
//
//import jakarta.annotation.PostConstruct;
//
//@Configuration(proxyBeanMethods = false)
//public class ManualConfiguration {
//
//	private KafkaTemplate kafkaTemplate;
//
//	@PostConstruct
//	void setup() {
//		this.kafkaTemplate.setObservationEnabled(true);
//	}
//}
