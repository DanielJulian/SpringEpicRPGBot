package com.danny.epicrpgbot.selenium;

import com.danny.epicrpgbot.config.DiscordConfiguration;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeleniumService {

    WebDriver driver;
    DiscordConfiguration discordCfg;


    public SeleniumService(WebDriver driver, DiscordConfiguration discordCfg) {
        this.driver = driver;
        this.discordCfg = discordCfg;
    }

    @PostConstruct
    public void initialize() {
        driver.get(discordCfg.getChannelUrl());  // Enter the EPIC RPG Channel
    }



}
