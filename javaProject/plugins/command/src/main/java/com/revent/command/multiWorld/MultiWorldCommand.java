package com.revent.command.multiWorld;

import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.CommandPermission;
import io.github.jorelali.commandapi.api.arguments.Argument;
import io.github.jorelali.commandapi.api.arguments.StringArgument;

import java.util.LinkedHashMap;

public class MultiWorldCommand {

    public static void multiWorldCommands(CommandAPI commandAPI) {
        LinkedHashMap<String, Argument> arguments = new LinkedHashMap<>();

        //------------------------------------------------kit command
        // kit menu
        commandAPI.register("kits", CommandPermission.fromString("revent.ranked.nickname"), arguments, KitCommand.executorMenu);

        // give kit
        arguments.put("new name", new StringArgument());
        commandAPI.register("kits", arguments, KitCommand.executorKit);
        arguments.clear();
        //------------------------------------------------kit command

        //spawn command
    }
}
