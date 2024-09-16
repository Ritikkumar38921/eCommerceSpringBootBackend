package com.alibou.ecommerce.configuration;

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

import com.alibou.ecommerce.notification.PaymentNotificationRequest;

@Configuration
public class KafkaPaymentTopicConfig {

	@Bean
	public NewTopic paymentTopic() {
		return TopicBuilder.name("payment-topic").build();
	}

	@Bean(name = "bootStapServerConfig1")
	public Map<String, Object> bootStapServerConfig() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		return map;
	}

	@Bean
	public ProducerFactory<String, PaymentNotificationRequest> producerFactory(ApplicationContext applicationContext) {
		Map<String, Object> bootStapProperties = (Map<String, Object>) applicationContext
				.getBean("bootStapServerConfig1");
		bootStapProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		bootStapProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		bootStapProperties.put(JsonDeserializer.TYPE_MAPPINGS,
				"paymentNotificationRequest:com.alibou.ecommerce.notification.PaymentNotificationRequest");
		return new DefaultKafkaProducerFactory(bootStapProperties);
	}

	@Bean
	public KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate(
			ProducerFactory<String, PaymentNotificationRequest> producerFactory) {
		return new KafkaTemplate<String, PaymentNotificationRequest>(producerFactory);
	}

}
