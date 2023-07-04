package com.danny.epicrpgbot.discord.feedback.handler;

import com.danny.epicrpgbot.config.AppConfiguration;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HealHandler extends FeedbackHandler {

    private final int hpThreshold;

    public HealHandler(AppConfiguration configuration) {
        this.hpThreshold= configuration.getHpThreshold();
    }

    @Override
    public void handle(Message message) {
        String messageContent = message.getContent();
        if (shouldHeal(messageContent)) {
            publishEvent("heal");
        }
    }

    private boolean shouldHeal(String message) {
        int remaining = getLifeRemainingFromMessage(message);
        return remaining <= hpThreshold;
    }

    private int getLifeRemainingFromMessage(String message) {
        String regex = "[a-zA-Z0-9]*remaining HP is (.+?)/(.+?)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);

        int remaining = 0, total = 0;
        while (matcher.find()) {
            remaining = Integer.parseInt(matcher.group(1));
            total = Integer.parseInt(matcher.group(2));
        }

        return remaining;
    }

}
