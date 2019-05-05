package com.revent.command.builderWorld;

import com.revent.mysql.BuilderWarpData;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.CommandPermission;
import io.github.jorelali.commandapi.api.arguments.*;

import java.util.LinkedHashMap;

public class BuilderCommand {

    public static void builderComamnds(CommandAPI commandAPI) {
        LinkedHashMap<String, Argument> arguments = new LinkedHashMap<>();
        //------------------------------------------------buildwarp command
        //the sectie list
        commandAPI.register("buildwarp", CommandPermission.fromString("revent.builder.warps"), arguments, BuildWarpCommand.executorSectieList);
        arguments.clear();

        //Returns a String[]
        DynamicSuggestedStringArgument dynSSArgSecties = new DynamicSuggestedStringArgument(BuilderWarpData::SectieList);

        arguments.put("action", new LiteralArgument("warplist"));//the warps list whit in a sectie
        arguments.put("sectie", dynSSArgSecties);
        commandAPI.register("buildwarp", CommandPermission.fromString("revent.builder.warps"), arguments, BuildWarpCommand.executorWarpList);
        arguments.clear();

        arguments.put("action", new LiteralArgument("createwarp"));//create a warp whitin a certain sectie
        arguments.put("sectie", dynSSArgSecties);
        arguments.put("warp name", new StringArgument());
        commandAPI.register("buildwarp", CommandPermission.fromString("revent.builder.warps"), arguments, BuildWarpCommand.executorCreateWarp);
        arguments.clear();

        arguments.put("action", new LiteralArgument("createsectie"));//create a sectie
        arguments.put("sectie name", new StringArgument());
        commandAPI.register("buildwarp", CommandPermission.fromString("revent.builder.createsectie"), arguments, BuildWarpCommand.executorCreateSectie);
        arguments.clear();

        arguments.put("sectie", dynSSArgSecties);
        arguments.put("warp", new StringArgument());//warp to a warp location
        commandAPI.register("buildwarp", CommandPermission.fromString("revent.builder.warps"), arguments, BuildWarpCommand.executorTeleportToWarp);
        arguments.clear();
        //------------------------------------------------buildwarp command
    }
}
