package com.highwayns.highwaymq.controller;

import com.highwayns.highwaymq.dto.AdvertisementRequest;
import com.highwayns.highwaymq.entity.Advertisement;
import com.highwayns.highwaymq.service.MessageService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin_role")
@RequiredArgsConstructor
public class AdminAdvertisementController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final MessageService messageService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createAdvertisement(@RequestBody AdvertisementRequest advertisementRequest, @PathVariable String userId){

        LOGGER.info("AdminAdvertisementController | createAdvertisement is started");
        Advertisement advertisement = new Advertisement();
        advertisement.setUserId(null);
        advertisement.setTitle(advertisementRequest.getTitle());
        advertisement.setPrice(advertisementRequest.getPrice());
        messageService.sendMessage(advertisement);

        LOGGER.info("AdminAdvertisementController | createAdvertisement | Advertisement Created");

        return ResponseEntity.status(HttpStatus.CREATED).body("Advertisement Created");
    }
}
