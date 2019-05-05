package com.revent.command;

import com.revent.command.builderWorld.BuilderCommand;
import io.github.jorelali.commandapi.api.*;
import com.revent.command.allWorld.CommandsAll;
import com.revent.command.kitPvp.*;
import com.revent.command.multiWorld.*;
import org.bukkit.plugin.java.JavaPlugin;


public class CommandsMain extends JavaPlugin {

	private CommandAPI comamndAPI = CommandAPI.getInstance();

	public void onLoad() {
		CommandsAll.commandsAll(CommandAPI.getInstance());
		BuilderCommand.builderComamnds(CommandAPI.getInstance());
		KitPvpCommand.kitPvpCommands(CommandAPI.getInstance());
		MultiWorldCommand.multiWorldCommands(CommandAPI.getInstance());
	}

	public void onEnable() { }
}