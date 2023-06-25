package com.danny.epicrpgbot.selenium;

import com.danny.epicrpgbot.config.AppConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {

    @Bean
    public WebDriver getWebDriver(AppConfiguration appConfiguration) {
        return switch (appConfiguration.getSeleniumDriver()) {
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            default -> throw new IllegalArgumentException("Valid Driver wasn't specified");
        };
    }

}
