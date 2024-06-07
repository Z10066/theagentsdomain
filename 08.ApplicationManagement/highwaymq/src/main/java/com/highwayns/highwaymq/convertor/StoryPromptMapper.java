package com.highwayns.highwaymq.convertor;

import org.springframework.stereotype.Component;

import com.highwayns.highwaymq.dto.StoryPromptDto;
import com.highwayns.highwaymq.entity.StoryPromptEntity;
import com.highwayns.highwaymq.entity.StoryType;

@Component
public class StoryPromptMapper {

    public static StoryPromptEntity storyPromptDtoToStoryPrompt(StoryPromptDto storyPromptDto){
        StoryPromptEntity storyPrompt = new StoryPromptEntity();
        storyPrompt.setPrompt(storyPromptDto.getPrompt());
        storyPrompt.setUserId(storyPromptDto.getUserId());
        storyPrompt.setStoryType(StoryType.UNKNOWN);
        return storyPrompt;
    }
}
