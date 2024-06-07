package com.highwayns.highwaymq.service;

import com.highwayns.highwaymq.dto.StoryPromptDto;

public interface MessageService {
    public void sendMessage(StoryPromptDto storyPromptDto);
    public void receiveMessage(StoryPromptDto advertisementDto);
}
