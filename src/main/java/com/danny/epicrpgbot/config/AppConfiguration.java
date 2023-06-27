package com.danny.epicrpgbot.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.List;


@Validated
@ConfigurationProperties(prefix = "app")
public class AppConfiguration {

    @NotBlank
    private String seleniumDriver;

    private List<String> workCommands;
    private long workDelay;
    private List<String> huntCommands;
    private long huntDelay;

    public String getSeleniumDriver() {
        return seleniumDriver;
    }

    public void setSeleniumDriver(String seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
    }

    public List<String> getWorkCommands() {
        return workCommands;
    }

    public void setWorkCommands(List<String> workCommands) {
        this.workCommands = workCommands;
    }

    public long getWorkDelay() {
        return workDelay;
    }

    public void setWorkDelay(String workDelay) {
        this.workDelay = Duration.parse(workDelay).toMillis();
    }

    public List<String> getHuntCommands() {
        return huntCommands;
    }

    public void setHuntCommands(List<String> huntCommands) {
        this.huntCommands = huntCommands;
    }

    public long getHuntDelay() {
        return huntDelay;
    }

    public void setHuntDelay(String huntDelay) {
        this.huntDelay = Duration.parse(huntDelay).toMillis();
    }
}
