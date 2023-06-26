package com.danny.epicrpgbot.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@Validated
@ConfigurationProperties(prefix = "app")
public class AppConfiguration {

    @NotBlank
    private String seleniumDriver;

    public String getSeleniumDriver() {
        return seleniumDriver;
    }

    public void setSeleniumDriver(String seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
    }
}
