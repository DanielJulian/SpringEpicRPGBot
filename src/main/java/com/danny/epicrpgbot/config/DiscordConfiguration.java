package com.danny.epicrpgbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationPropertiesScan
@PropertySource("discord.properties")
public class DiscordConfiguration {

    @Value("${bot.channel.url}")
    private String channelUrl;

    @Value("${user.email}")
    private String userEmail;

    @Value("${user.password}")
    private String userPassword;

    @Bean
    public DiscordCfg modelBean() {
        return new DiscordCfg(channelUrl, userEmail, userPassword);
    }

    public record DiscordCfg(String channelUrl, String email, String password) {
    }
}
