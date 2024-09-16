package com.alibou.ecommerce.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConfig {

	@Bean(name = "bootStapConfig")
	public Map<String, Object> bootStapConfig() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		map.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 300000); // 5 minutes
//		map.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
		return map;
	}
	
	
    @Bean
    public ConsumerFactory<String, Object> orderConsumerFactory(ApplicationContext applicationContext) {
    	
    	
    	
        Map<String, Object> props = (Map<String,Object>) applicationContext.getBean("bootStapConfig");
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "orderGroup");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.TYPE_MAPPINGS,
				"orderConfirmation:com.alibou.ecommerce.kafka.order.OrderConfirmation");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> orderKafkaListenerContainerFactory( ApplicationContext applicationContext ) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderConsumerFactory(applicationContext));
//        factory.getContainerProperties().setPollTimeout(Duration.ofMillis(3000).toSeconds());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Object> paymentConsumerFactory(ApplicationContext applicationContext) {
        Map<String, Object> props = (Map<String,Object>) applicationContext.getBean("bootStapConfig");
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "paymentGroup");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.TYPE_MAPPINGS,"paymentNotificationRequest:com.alibou.ecommerce.kafka.payment.PaymentNotificationRequest");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> paymentKafkaListenerContainerFactory(ApplicationContext applicationContext) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(paymentConsumerFactory(applicationContext));
        return factory;
    }

//	@Bean
//	public ConsumerFactory<String, Object> consumerFactory(ApplicationContext applicationContext) {
//		Map<String, Object> bootStapProperties = (Map<String, Object>) applicationContext.getBean("bootStapConfig");
//		bootStapProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		bootStapProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//		bootStapProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "orderGroup,paymentGroup");
//		bootStapProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//		bootStapProperties.put(JsonDeserializer.TYPE_MAPPINGS,
//				"orderConfirmation:com.alibou.ecommerce.kafka.order.OrderConfirmation,paymentConfirmation:com.alibou.ecommerce.kafka.payment.PaymentConfirmation");
//		bootStapProperties.put(JsonDeserializer.TRUSTED_PACKAGES, "com.alibou.ecommerce.kafka");
//		return new DefaultKafkaConsumerFactory<String, Object>(bootStapProperties);
//	}

}
