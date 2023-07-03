package com.danny.epicrpgbot.discord.feedback.handler;

import com.danny.epicrpgbot.command.CommandEventBuilder;
import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public abstract class FeedbackHandler {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    protected void publishEvent(String... commands) {
        CommandEventBuilder commandEventBuilder = new CommandEventBuilder(this);
        Arrays.stream(commands).forEach(commandEventBuilder::chainCommand);
        applicationEventPublisher.publishEvent(commandEventBuilder.build());
    }

    public abstract void handle(Message message) throws InterruptedException;
}
