package com.danny.epicrpgbot.selenium;

import com.danny.epicrpgbot.command.CommandEvent;
import com.danny.epicrpgbot.config.DiscordConfiguration;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SeleniumService {

    @Autowired
    WebDriver driver;

    @Autowired
    DiscordConfiguration.DiscordCfg discordCfg;

    @PostConstruct
    public void initialize() {
        driver.get(discordCfg.channelUrl());  // Enter the EPIC RPG Channel
        loginToDiscord();
    }


    public void loginToDiscord() {
        // Sometimes we get a message that discord is already installed in our system.
        // We want to bypass this by pressing the "Continue" Button
        WebElement div = driver.findElement(By.xpath("//div[text() = 'Continuar en navegador']"));

        // Select the parent (Which is a button) and click it to continue to the login screen
        if (div != null) div.findElement(By.xpath("./..")).sendKeys(Keys.RETURN);

        WebElement emailElement = driver.findElement(By.name("email"));
        emailElement.clear();
        emailElement.sendKeys(discordCfg.email());

        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.clear();
        passwordElement.sendKeys(discordCfg.password());
        passwordElement.sendKeys(Keys.RETURN);


    }



    @EventListener
    public void handleContextStart(CommandEvent ce) {
        System.out.println("Command Event received: " + ce);
    }



}
