/*
 * the main class of the plugin
 */
package com.revent;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.revent.managers.ChatManager;
import com.revent.serverData.PlayerData;
import com.revent.serverData.StaticData;
import com.revent.serverData.WorldData;
import com.revent.types.ErrorType;
import com.revent.types.SuccesType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * The Class CoreMain.
 */
public class CoreMain extends JavaPlugin {

	private static PrintWriter ERROR;

	public static Logger logger;

	public static ChatManager cM;

	public static WorldData worldData = new WorldData();

	public static PlayerData playerData = new PlayerData();

	private static Plugin plugin;

	public void onEnable() {
		plugin = Bukkit.getPluginManager().getPlugin("corePlugin");
		PluginDescriptionFile pdfFile = getDescription();
		logger = getLogger();

		ErrorFile();
		permmissionRegister();
		messageEnumChecker();

		cM = new ChatManager(this);
	}

	public static FileConfiguration getConfigFile() {
		return plugin.getConfig();
	}

	public static void saveConfigFile() {
		plugin.saveConfig();
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {//TODO find the correct class of the driver
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName("83.87.28.180");
			dataSource.setPort(3306);
			dataSource.setDatabaseName("MCServer");
			dataSource.setUser("MCserver");
			dataSource.setPassword("12QWas!@12QWas!@");
			connection = dataSource.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace(getErrorWriter());
		}
		return connection;
	}

	/**
	 * Gets the error writer.
	 *
	 * @return the error writer
	 */
	public static PrintWriter getErrorWriter(){
		return ERROR;
	}

	private void ErrorFile() {//create the file for the error's at bootup
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

			Date today = Calendar.getInstance().getTime();

			String errorDate = df.format(today);
			File errorFile = new File(StaticData.errorData, errorDate + "_errors.log");
			if (!errorFile.exists()) //noinspection ResultOfMethodCallIgnored
                errorFile.createNewFile();
			ERROR = new PrintWriter(errorFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void permmissionRegister() {//registering all the permissions

		PluginManager pm = getServer().getPluginManager();

		pm.addPermission(new Permission("revent.ban.ban_menu"));

		pm.addPermission(new Permission("revent.builder.effect"));
		pm.addPermission(new Permission("revent.builder.BuilderWorldTeleport"));
		pm.addPermission(new Permission("revent.builder.warps"));
		pm.addPermission(new Permission("revent.builder.createsectie"));
		pm.addPermission(new Permission("revent.builder.worldTeleport"));

		pm.addPermission(new Permission("revent.ranked.vanish"));
		pm.addPermission(new Permission("revent.ranked.nickname"));

		pm.addPermission(new Permission("revent.mod.nameuncover"));
		pm.addPermission(new Permission("revent.mod.antisheld"));
		pm.addPermission(new Permission("revent.mod.excecutor"));
		pm.addPermission(new Permission("revent.mod.broadcast"));
		pm.addPermission(new Permission("revent.mod.effect"));
		pm.addPermission(new Permission("revent.mod.teleport.PlayerToPlayer"));
		pm.addPermission(new Permission("revent.mod.teleport.PlayerToCords"));
	}

	private void messageEnumChecker() {//at bootup check the locale files for completeness

		File langDirectory = new File(StaticData.langData);
		File[] directoryListing = langDirectory.listFiles();
		if (directoryListing != null) {//make sure it exist
			logger.info(ChatColor.YELLOW + "resolving all language files for completeness");
			//loop through all the lang files in the locale directory
			for (File langFile : directoryListing) {
				try (Scanner scanner = new Scanner(langFile)) {//opening the current file
					//checking the file for all the succestype messages
					for (SuccesType succesType : SuccesType.values()) {
						Boolean present = FALSE;//setting it first to false
						while (scanner.hasNextLine()) {//looping through the file for the current succestype message
							String langLine = scanner.nextLine();
							if (langLine.startsWith('"' + succesType.toString())) {
								present = TRUE;//hey we found the line at wich the message is standing so we do not need to contiune!
								break;
							}
						}
						if (!present) {//did we found it no? well give of that error!
							logger.warning(ChatColor.RED + succesType.toString() + " of the succes messages is not present in: " + langFile.getName());
						}
					}
					//do it again but then for the errortypes
					for (ErrorType errorType : ErrorType.values()) {
						Boolean present = FALSE;//setting it first to false
						while (scanner.hasNextLine()) {//looping through the file for the current succestype message
							String langLine = scanner.nextLine();
							if (langLine.startsWith('"' + errorType.toString())) {
								present = TRUE;//hey we found the line at wich the message is standing so we do not need to contiune!
								break;
							}
						}
						if (!present) {//did we found it no? well give of that error!
							logger.warning(ChatColor.RED + errorType.toString() + " of the error messages is not present in: " + langFile.getName());
						}
					}
				} catch (IOException e) {//what could happen?
					getErrorWriter().println("-----------------------------------------------ERROR.corePlugin:CoreMain messageEnumChecker");
					e.printStackTrace(getErrorWriter());
				}
			}
			logger.info(ChatColor.YELLOW + "check completed!");
		} else {
			logger.warning("the directory for the lang files does not exist without it there will be a lot of errors saying so!");
		}
	}
}
