package com.highwayns.highwaymq.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StoryPromptRequest implements Serializable {

    private String prompt;
}
