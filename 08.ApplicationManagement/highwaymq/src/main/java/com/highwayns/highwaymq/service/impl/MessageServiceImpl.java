package com.highwayns.highwaymq.service.impl;

import com.highwayns.highwaymq.convertor.StoryPromptMapper;
import com.highwayns.highwaymq.dto.StoryPromptDto;
import com.highwayns.highwaymq.entity.StoryPromptEntity;
import com.highwayns.highwaymq.repository.StoryPromptRepository;
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

    private final StoryPromptRepository storyPromptRepository;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;


    @Override
    public void sendMessage(StoryPromptDto storyPromptDto) {

        LOGGER.info("MessageServiceImpl | sendMessage is started");


        LOGGER.info("MessageServiceImpl | sendMessage | | queue name : " + queue.getName());
        LOGGER.info("MessageServiceImpl | sendMessage | Sending message through RabbitMq");
        StoryPromptEntity storyPrompt = StoryPromptMapper.storyPromptDtoToStoryPrompt(storyPromptDto);

        LOGGER.info("MessageServiceImpl | sendMessage | storyPrompt storyType : " + storyPrompt.getStoryType().toString());

        storyPromptRepository.save(storyPrompt);

        try {
            rabbitTemplate.convertAndSend(queue.getName(),storyPromptDto);
        }catch (Exception e){
            LOGGER.info("MessageServiceImpl | sendMessage | error : " + e.getMessage());
        }
    }

    @RabbitListener(queues = "${queue.name}")
    @Override
    public void receiveMessage(@Payload StoryPromptDto advertisementDto) {

        LOGGER.info("MessageServiceImpl | receiveMessage is started");

        LOGGER.info("MessageServiceImpl | receiveMessage | Report is creating");

    }

}
