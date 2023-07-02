package com.danny.epicrpgbot.discord;

import com.danny.epicrpgbot.config.DiscordConfiguration;
import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DiscordListener {

    Logger logger = LoggerFactory.getLogger(DiscordListener.class);
    @Autowired
    DiscordConfiguration.DiscordCfg discordCfg;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void startListening() {
        logger.info("Starting Discord Listener");
        executor.execute( () ->  {
            DiscordClient
                    .create(discordCfg.botToken())
                    .withGateway(client ->
                        client.on(MessageCreateEvent.class, event -> {
                            Message message = event.getMessage();

                            if (isAuthorEpicRPG(message)) {
                                applicationEventPublisher.publishEvent(message);
                            }

                            /* if (message.getContent().equalsIgnoreCase("!ping")) {
                                return message.getChannel()
                                        .flatMap(channel -> channel.createMessage("Pong!"));
                            } */

                            return Mono.empty();
                        }))
                    .block();
        });
    }

    public boolean isAuthorEpicRPG(Message message) {
        return message.getAuthor().isPresent() && message.getAuthor().get().getUserData().username().equals("EPIC RPG");
    }

    @PreDestroy
    public void destroy() {
        executor.shutdownNow();
    }

}
