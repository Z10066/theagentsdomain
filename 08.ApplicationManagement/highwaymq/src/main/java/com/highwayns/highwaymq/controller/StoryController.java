package com.highwayns.highwaymq.controller;

import com.highwayns.highwaymq.dto.StoryPromptDto;
import com.highwayns.highwaymq.dto.StoryPromptRequest;
import com.highwayns.highwaymq.service.MessageService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/story")
@RequiredArgsConstructor
public class StoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final MessageService messageService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createStoryPrompt(@RequestBody StoryPromptRequest storyPromptRequest, @PathVariable String userId){

        LOGGER.info("StoryController | createStoryPrompt is started");
        StoryPromptDto storyPromptDto = new StoryPromptDto();
        storyPromptDto.setUserId(userId);
        storyPromptDto.setPrompt(storyPromptRequest.getPrompt());
        messageService.sendMessage(storyPromptDto);
        LOGGER.info("StoryController | createStoryPrompt | storyPrompt Created");
        return ResponseEntity.status(HttpStatus.CREATED).body("StoryPrompt Created");
    }
}
