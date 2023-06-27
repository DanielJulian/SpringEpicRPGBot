package com.danny.epicrpgbot;

import com.danny.epicrpgbot.config.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({AppConfiguration.class})
@SpringBootApplication
public class EpicRpgBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpicRpgBotApplication.class, args);
	}

}
