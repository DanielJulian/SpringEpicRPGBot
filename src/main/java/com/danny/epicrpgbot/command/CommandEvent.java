package com.danny.epicrpgbot.command;

import org.springframework.context.ApplicationEvent;

import java.util.List;


public class CommandEvent extends ApplicationEvent {

    private final List<String> commands;

    public CommandEvent(Object source, List<String> commands) {
        super(source);
        this.commands = commands;
    }

    public List<String> getCommands() {
        return commands;
    }

    @Override
    public String toString() {
        return "CommandEvent{" +
                "commands='" + commands + '\'' +
                '}';
    }
}
