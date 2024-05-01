package com.highwayns.highwaymq.service.impl;

import com.highwayns.highwaymq.dto.AdvertisementDto;
import com.highwayns.highwaymq.entity.Advertisement;
import com.highwayns.highwaymq.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;


    @Override
    public void sendMessage(Advertisement advertisement) {

        LOGGER.info("MessageServiceImpl | sendMessage is started");

        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setId(advertisement.getId());
        advertisementDto.setTitle(advertisement.getTitle());
        advertisementDto.setViewCount(advertisement.getViewCount());

        LOGGER.info("MessageServiceImpl | sendMessage | | queue name : " + queue.getName());
        LOGGER.info("MessageServiceImpl | sendMessage | Sending message through RabbitMq");

        try {
            rabbitTemplate.convertAndSend(queue.getName(),advertisementDto);
        }catch (Exception e){
            LOGGER.info("MessageServiceImpl | sendMessage | error : " + e.getMessage());
        }
    }

    @RabbitListener(queues = "${queue.name}")
    @Override
    public void receiveMessage(@Payload AdvertisementDto advertisementDto) {

        LOGGER.info("MessageServiceImpl | receiveMessage is started");

        LOGGER.info("MessageServiceImpl | receiveMessage | Report is creating");

    }

}
