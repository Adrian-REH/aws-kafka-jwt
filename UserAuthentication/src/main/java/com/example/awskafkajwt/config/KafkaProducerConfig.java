package com.example.awskafkajwt.config;

import com.example.awskafkajwt.event.Event;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
   // private final String bootstrapAddress = "b-3.democluster1.72cs3z.c2.kafka.eu-west-3.amazonaws.com:9098,b-2.democluster1.72cs3z.c2.kafka.eu-west-3.amazonaws.com:9098,b-1.democluster1.72cs3z.c2.kafka.eu-west-3.amazonaws.com:9098";
    private final String bootstrapAddress = ":9098";

    @Bean
    public ProducerFactory<String, Event<?>> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Event<?>> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
