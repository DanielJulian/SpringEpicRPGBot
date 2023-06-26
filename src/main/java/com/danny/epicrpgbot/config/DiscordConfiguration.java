package com.danny.epicrpgbot.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;


@Validated
@ConfigurationProperties(prefix = "bot")
@PropertySource("classpath:discord.properties")
public class DiscordConfiguration {

    @NotBlank
    private String channelUrl;

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }
}
