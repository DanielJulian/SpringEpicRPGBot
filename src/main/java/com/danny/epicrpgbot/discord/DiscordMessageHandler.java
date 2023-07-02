package com.danny.epicrpgbot.discord;

import discord4j.core.object.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DiscordMessageHandler {

    Logger logger = LoggerFactory.getLogger(DiscordListener.class);

    @EventListener
    public void handleDiscordMessage(Message msg) {
        logger.info("About to handle {}", msg);
    }


}
