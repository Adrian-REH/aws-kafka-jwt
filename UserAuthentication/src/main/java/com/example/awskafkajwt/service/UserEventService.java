package com.example.awskafkajwt.service;

import com.example.awskafkajwt.entity.User;
import com.example.awskafkajwt.event.Event;
import com.example.awskafkajwt.event.EventType;
import com.example.awskafkajwt.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class UserEventService {
    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("{topic.customer.name:customers}")
    private String topicUser;

    public void publishAuth(User user){
        UserCreatedEvent created = new UserCreatedEvent();
        created.setData(user);
        created.setId(UUID.randomUUID().toString());
        created.setDate(new Date());


        // DEBO CORROBORAR SI ESTA AUTHORIZADO O NO RESPECTO AL USUARIO QUE ME ENVIO
        created.setType(EventType.AUTHORIZED);

        this.producer.send(topicUser,created);
    }


    /**
     * Consumo el evento de Message service, y verifico si es correcto y lo devuelvo la verificacion
     * @param event
     */
    @KafkaListener(
            topics = "${topic.customer.name:customers}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo2")
    public void consumer(Event<?> event) {

        if (event.getClass().isAssignableFrom(UserCreatedEvent.class)) {

            //DEBO VERIFICAR EL EVENTO Y DEVOLVER EL RESULTADO
            UserCreatedEvent customerCreatedEvent = (UserCreatedEvent) event;
            log.info("Received User created event .... with Id={}, data={}",
                    customerCreatedEvent.getId(),
                    customerCreatedEvent.getData().toString());
        }

    }


}

