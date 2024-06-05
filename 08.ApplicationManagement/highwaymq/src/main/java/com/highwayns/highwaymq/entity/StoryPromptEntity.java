package com.highwayns.highwaymq.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "StoryPrompt")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoryPromptEntity extends IdBasedEntity implements Serializable {
    private String userId;
    private String prompt;
    @Enumerated(EnumType.STRING)
    private StoryType storyType;

}
