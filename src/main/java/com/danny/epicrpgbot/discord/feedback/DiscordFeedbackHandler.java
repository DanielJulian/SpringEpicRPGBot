package com.danny.epicrpgbot.discord.feedback;

import com.danny.epicrpgbot.command.CommandEventBuilder;
import com.danny.epicrpgbot.discord.feedback.handler.HealHandler;
import discord4j.core.object.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DiscordFeedbackHandler {

    Logger logger = LoggerFactory.getLogger(DiscordFeedbackHandler.class);

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private HealHandler healHandler;


    @EventListener
    public void handleDiscordMessage(Message msg) {
        String message = msg.getContent();
        logger.info("About to handle {}", message);

        if (message.contains("remaining HP is")) {
            healHandler.handle(msg);
        } else if (message.contains("you don't have a life potion to do this")) {
            applicationEventPublisher.publishEvent(new CommandEventBuilder(this).chainCommand("buy life potion 30"));
        } else if (message.contains("We have to check you are actually playing")) {
            // TODO
        }

    }



     /*
     if 'remaining HP is' in message.content:
                life_remaining = getLifeRemaining(message.content)
                if life_remaining and int(life_remaining) <= int(os.getenv('hp_threshold')):
                    print("Sending Drink a potion message")
                    self.feedback_queue.put("Drink a potion bro!")

            elif "you don't have a life potion to do this" in message.content:
                print("Sending buy a potion message")
                self.feedback_queue.put("Buy some potions bro!")
                self.feedback_queue.put("Drink a potion bro!")

            elif "We have to check you are actually playing" in message.content:
                self.windows_beep(500, 400)

            elif "you need a" in message.content and "seed to farm" in message.content: # Out of seeds
                print("Out of seeds, sending message to buy...")
                self.feedback_queue.put("Buy seed")
     * */

}
