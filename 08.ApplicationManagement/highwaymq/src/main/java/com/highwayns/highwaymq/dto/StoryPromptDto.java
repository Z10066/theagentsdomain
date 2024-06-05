package com.highwayns.highwaymq.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StoryPromptDto implements Serializable {

    private String userId;
    private String prompt;
}
