package com.danny.epicrpgbot;

import com.danny.epicrpgbot.config.AppConfiguration;
import com.danny.epicrpgbot.config.DiscordConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({AppConfiguration.class, DiscordConfiguration.class})
@SpringBootApplication
public class EpicRpgBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpicRpgBotApplication.class, args);
	}

}
