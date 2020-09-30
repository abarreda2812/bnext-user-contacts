package com.orangebank.delivery.preparator.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

	private String bootstrapAddress;
	private String producerTopicRequest;

	public KafkaTopicConfig(@Value(value = "${spring.kafka.producer.bootstrap-servers}") String bootstrapAddress,
			@Value("${spring.kafka.producer.sendTopic}") String producerTopicRequest) {
		this.bootstrapAddress = bootstrapAddress;
		this.producerTopicRequest = producerTopicRequest;
	}

	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic topic1() {
		return TopicBuilder.name(producerTopicRequest).partitions(1).replicas(1).build();
	}
}