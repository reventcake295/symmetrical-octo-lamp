package com.revent.command.allWorld;

import com.revent.CoreMain;
import io.github.jorelali.commandapi.api.*;
import io.github.jorelali.commandapi.api.arguments.*;

import java.util.LinkedHashMap;

public class CommandsAll {

    public static void commandsAll(CommandAPI commandAPI) {
        LinkedHashMap<String, Argument> arguments = new LinkedHashMap<>();

        //------------------------------------------------server command
        // server command init
        DynamicSuggestedStringArgument dynSSArgWorlds = new DynamicSuggestedStringArgument(() -> {
            //Returns a String[]
            return CoreMain.worldData.WorldNames.toArray(new String[CoreMain.worldData.WorldNames.size()]);
        });

        arguments.put("worlds", dynSSArgWorlds);//world teleport from dynamic list created in the serverdata class
        commandAPI.register("server", arguments, serversCommand.executorNormal);

        arguments.put("force", new BooleanArgument());//for builders so there can be build behind the scenes TODO: mayby add an list for builders (/server builder [world]) instead of this
        commandAPI.register("server", CommandPermission.fromString("revent.builder.worldTeleport"), arguments, serversCommand.executorForce);
        arguments.clear();
        //------------------------------------------------server command

        //------------------------------------------------tp command
        arguments.put("player", new PlayerArgument());//teleport player to another player
        commandAPI.register("tp", CommandPermission.fromString("minecraft.command.tp"), arguments, TpCommand.executorToPlayer);
        arguments.clear();

        arguments.put("location", new LocationArgument());//teleport player to a location
        commandAPI.register("tp", CommandPermission.fromString("minecraft.command.tp"), arguments, TpCommand.executorToLocation);
        arguments.clear();

        arguments.put("player", new PlayerArgument());
        arguments.put("location", new LocationArgument());// teleport another player to a location
        commandAPI.register("tp", CommandPermission.fromString("revent.mod.teleport.PlayerToCords"), arguments, TpCommand.executorPlayerToLocation);
        arguments.clear();

        arguments.put("player", new PlayerArgument());
        arguments.put("target player", new PlayerArgument());//teleport another player to a player
        commandAPI.register("tp", CommandPermission.fromString("revent.mod.teleport.PlayerToPlayer"), arguments, TpCommand.executorPlayerToPlayer);
        arguments.clear();
        //------------------------------------------------tp command

        //------------------------------------------------effect command
        arguments.put("effect", new PotionEffectArgument());
        arguments.put("time", new IntegerArgument());
        arguments.put("strength", new IntegerArgument());
        arguments.put("visable", new BooleanArgument());// put effect on yourself
        commandAPI.register("effect", CommandPermission.fromString("revent.builder.effect"), arguments, EffectCommand.executorSelf);

        arguments.put("player", new PlayerArgument());// put effect on someone else
        commandAPI.register("effect", CommandPermission.fromString("revent.mod.effect"), arguments, EffectCommand.executorOtherPlayer);
        arguments.clear();
        //------------------------------------------------effect command

        //------------------------------------------------nickname command
        //reset nickname
        commandAPI.register("nickname", CommandPermission.fromString("revent.ranked.nickname"), arguments, NicknameCommand.executorResetName);

        arguments.put("new name", new StringArgument());// set your nickname to something else
        commandAPI.register("nickname", CommandPermission.fromString("revent.ranked.nickname"), arguments, NicknameCommand.executorSetName);
        arguments.clear();
        //------------------------------------------------nickname command

        //------------------------------------------------msg command
        arguments.put("target", new PlayerArgument());
        arguments.put("msg", new GreedyStringArgument());// send a message to someone
        commandAPI.register("msg", arguments, messageCommand.executor);

        arguments.remove("target");// send a message to the person that last talked to you
        commandAPI.register("r", arguments, replyCommand.executor);
        //------------------------------------------------msg command

        commandAPI.register("boadcast", CommandPermission.fromString("revent.mod.broadcast"), arguments, Broadcast.executor);
        arguments.clear();

        //------------------------------------------------ban-menu command
        arguments.put("type", new LiteralArgument("ip"));
        arguments.put("target", new PlayerArgument());//ban someone based on ip
        commandAPI.register("ban-menu", CommandPermission.fromString("revent.ban.ban_menu"), arguments, Ban_Menu.executorIp);
        arguments.clear();

        arguments.put("type", new LiteralArgument("player"));
        arguments.put("target", new PlayerArgument());//ban someone based on name/UUID
        commandAPI.register("ban-menu", CommandPermission.fromString("revent.ban.ban_menu"), arguments, Ban_Menu.executorPlayer);
        arguments.clear();
        //------------------------------------------------ban-menu command

        //------------------------------------------------antiswear command
        arguments.put("type", new LiteralArgument("add"));
        arguments.put("target", new StringArgument());// add a word to the forbidden list
        commandAPI.register("antischeld", CommandPermission.fromString("revent.mod.antisheld"), arguments, commAntiswear.executorAdd);
        arguments.clear();

        // banned words list init
        DynamicSuggestedStringArgument dynSSArgAntiScheld = new DynamicSuggestedStringArgument(() -> {
            //Returns a String[]
            return CoreMain.getConfigFile().getStringList("Blocked-Words").toArray(new String[CoreMain.getConfigFile().getStringList("Blocked-Words").size()]);
        });

        arguments.put("type", new LiteralArgument("remove"));
        arguments.put("target", dynSSArgAntiScheld);// remove a word from the forbidden list
        commandAPI.register("antischeld", CommandPermission.fromString("revent.mod.antisheld"), arguments, commAntiswear.executorRemove);
        arguments.clear();
        //------------------------------------------------antiswear command

        //------------------------------------------------ban-menu command
        arguments.put("target", new PlayerArgument());
        arguments.put("type", new LiteralArgument("c:"));
        arguments.put("command", new GreedyStringArgument());// force someone to excecute a command
        commandAPI.register("ban-menu", CommandPermission.fromString("revent.mod.excecutor"), arguments, Excecutor.executorCommand);
        arguments.clear();

        arguments.put("target", new PlayerArgument());
        arguments.put("type", new LiteralArgument("t:"));
        arguments.put("message", new GreedyStringArgument());// force someone to say something in chat
        commandAPI.register("ban-menu", CommandPermission.fromString("revent.mod.excecutor"), arguments, Excecutor.executorMessage);
        arguments.clear();
        //------------------------------------------------ban-menu command

        arguments.put("player", new StringArgument());//get the actual name from someone
        commandAPI.register("name", CommandPermission.fromString("revent.mod.nameuncover"), arguments, NameCommand.executor);

        //open the server menu
        commandAPI.register("Menu", arguments, Menu.executor);

        //display the rules in chat TODO: create menu whit the rules
        commandAPI.register("regels", arguments, Rulescomm.executor);

        //teleport to the spawn location of the world you are in
        commandAPI.register("spawn", arguments, spawnCommand.executor);

        arguments.put("player", new PlayerArgument());// locate someone on the server
        commandAPI.register("find", arguments, FindCommand.executor);
        arguments.clear();

        //hide from everyone except the ones in vanish
        commandAPI.register("vanish", CommandPermission.fromString("revent.ranked.vanish"), arguments, AdminVanishCommand.executor);
    }
}
