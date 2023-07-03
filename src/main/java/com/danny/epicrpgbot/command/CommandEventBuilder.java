package com.danny.epicrpgbot.command;

import java.util.LinkedList;
import java.util.List;

public class CommandEventBuilder {

    private final String RPG_COMMAND_PREFIX = "rpg ";
    private final Object source;
    private final List<String> commands = new LinkedList<>();

    public CommandEventBuilder(Object source) {
        this.source = source;
    }

    public CommandEventBuilder chainCommand(String command) {
        this.commands.add(RPG_COMMAND_PREFIX + command);
        return this;
    }

    public CommandEvent build() {
        return new CommandEvent(source, commands);
    }

}
