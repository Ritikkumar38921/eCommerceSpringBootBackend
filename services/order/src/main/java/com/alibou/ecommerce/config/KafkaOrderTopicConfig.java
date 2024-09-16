package com.alibou.ecommerce.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.alibou.ecommerce.kafka.OrderConfirmation;

@Configuration
public class KafkaOrderTopicConfig {

	@Bean
	public NewTopic orderTopic() {
		return TopicBuilder.name("order-topic").build();
	}

	@Bean(name = "bootStapServerConfig")
	public Map<String, Object> bootStapServerConfig() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		return map;
	}

	@Bean
	public ProducerFactory<String, OrderConfirmation> producerFactory(ApplicationContext applicationContext) {
		Map<String, Object> bootStapProperties = (Map<String, Object>) applicationContext
				.getBean("bootStapServerConfig");
		bootStapProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		bootStapProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		bootStapProperties.put(JsonDeserializer.TYPE_MAPPINGS,
				"orderConfirmation:com.alibou.ecommerce.kafka.OrderConfirmation");
		return new DefaultKafkaProducerFactory(bootStapProperties);
	}

	@Bean
	public KafkaTemplate<String, OrderConfirmation> kafkaTemplate(
			ProducerFactory<String, OrderConfirmation> producerFactory) {
		return new KafkaTemplate<String, OrderConfirmation>(producerFactory);
	}

}
