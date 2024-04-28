package com.highwayns.highwaymq.service;

import com.highwayns.highwaymq.dto.AdvertisementDto;
import com.highwayns.highwaymq.entity.Advertisement;

public interface MessageService {
    public void sendMessage(Advertisement advertisement);
    public void receiveMessage(AdvertisementDto advertisementDto);
}
