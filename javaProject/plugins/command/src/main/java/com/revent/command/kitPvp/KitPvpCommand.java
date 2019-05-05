package com.revent.command.kitPvp;

import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.CommandPermission;
import io.github.jorelali.commandapi.api.arguments.Argument;

import java.util.LinkedHashMap;

public class KitPvpCommand {

    public static void kitPvpCommands(CommandAPI commandAPI) {
        LinkedHashMap<String, Argument> arguments = new LinkedHashMap<>();

        //------------------------------------------------fight command
        commandAPI.register("fight", arguments, FightTpCommand.executorRandom);//teleport to a random start location

        arguments.put("position name", FightTpCommand.startNames);//teleport to one of the start locations by name
        commandAPI.register("fight", CommandPermission.fromString("revent.builder.worldTeleport"), arguments, FightTpCommand.executorName);
        arguments.clear();
        //------------------------------------------------fight command
    }
}
