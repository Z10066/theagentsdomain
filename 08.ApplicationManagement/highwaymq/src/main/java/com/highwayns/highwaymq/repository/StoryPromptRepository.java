package com.highwayns.highwaymq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highwayns.highwaymq.entity.StoryPromptEntity;

import java.util.Optional;

public interface StoryPromptRepository extends JpaRepository<StoryPromptEntity, Long> {

    Optional<StoryPromptEntity> findById(Long id);
}
