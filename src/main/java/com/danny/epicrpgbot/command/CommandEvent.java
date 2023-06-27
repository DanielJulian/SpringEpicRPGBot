package com.danny.epicrpgbot.command;

import org.springframework.context.ApplicationEvent;


public class CommandEvent extends ApplicationEvent {

    private final String command;

    public CommandEvent(Object source, String command) {
        super(source);
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "CommandEvent{" +
                "command='" + command + '\'' +
                '}';
    }
}
