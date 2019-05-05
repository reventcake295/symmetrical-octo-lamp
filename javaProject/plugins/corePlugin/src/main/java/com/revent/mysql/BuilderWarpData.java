package com.revent.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.revent.CoreMain;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.revent.returnstate.ReturnSucces;
import com.revent.types.SuccesType;

public class BuilderWarpData {
	//TODO: up for restructure after database rewrite has been completed so this will remain old code until then
	public static void warpPlayer(Player player, String section, String warpName) {

		Location location = new Location(player.getWorld(), 0, 0, 0);

		try {
			Connection connection = CoreMain.getConnection();
			PreparedStatement getX = connection
					.prepareStatement("SELECT locX FROM MCServer.BUI_warpStore WHERE name = ? AND sectie = ?;");
			getX.setString(1, warpName);
			getX.setString(2, section);
			ResultSet resultX = getX.executeQuery();
			resultX.next();
			
			location.add(resultX.getDouble(1), 0, 0);
			
			connection.close();
			
		} catch (SQLException ex) {
			CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.BuilderWarpData.warpPlayer: getX");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}
		try {
			Connection connection = CoreMain.getConnection();
			PreparedStatement getY = connection
					.prepareStatement("SELECT locY FROM MCServer.BUI_warpStore WHERE name = ? AND sectie = ?;");
			getY.setString(1, warpName);
			getY.setString(2, section);
			ResultSet resultY = getY.executeQuery();
			resultY.next();
			
			location.add(0, resultY.getDouble(1), 0);
			
			connection.close();
			
		} catch (SQLException ex) {
			CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.BuilderWarpData.warpPlayer: getY");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}
		try {
			Connection connection = CoreMain.getConnection();
			PreparedStatement getZ = connection
					.prepareStatement("SELECT locZ FROM MCServer.BUI_warpStore WHERE name = ? AND sectie = ?;");
			getZ.setString(1, warpName);
			getZ.setString(2, section);
			ResultSet resultZ = getZ.executeQuery();
			resultZ.next();

			location.add(0, 0, resultZ.getDouble(1));
			
			connection.close();

		} catch (SQLException ex) {
			CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.BuilderWarpData.warpPlayer: getZ");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}

		player.teleport(location);
		ReturnSucces.SuccesMessage(SuccesType.WarpTP, player.getLocale());
	}

	public static void sendWarpList(Player player, String sectie) {

		try {
			Connection connection = CoreMain.getConnection();
			PreparedStatement stat = connection
					.prepareStatement("SELECT name FROM MCServer.BUI_warpStore WHERE sectie = ?;");
			stat.setString(1, sectie);
			ResultSet warps = stat.executeQuery();

			ReturnSucces.sendSuccesToPlayer(true, SuccesType.warpList, player);

			while (warps.next()) {
				player.sendMessage(ChatColor.DARK_GREEN + " -" + warps.getString(1));
			}

			connection.close();
		} catch (SQLException ex) {
			CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.BuilderWarpData.sendWarpList");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}
	}

	public static void sendSectieList(Player player) {

		try {
			Connection connection = CoreMain.getConnection();
			ResultSet sections = connection.prepareStatement("SELECT name FROM MCServer.BUI_sectieNames;")
					.executeQuery();

			ReturnSucces.sendSuccesToPlayer(true, SuccesType.sectieList, player);
			while (sections.next()) {
				player.sendMessage(ChatColor.DARK_GREEN + " -" + sections.getString(1));
			}

			connection.close();
		} catch (SQLException ex) {
			CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.BuilderWarpData.sendSectieList");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}
	}

	public static String[] SectieList() {

		try {
			Connection connection = CoreMain.getConnection();
			ResultSet sections = connection.prepareStatement("SELECT name FROM MCServer.BUI_sectieNames;")
					.executeQuery();
			ArrayList<String> sectieList = new ArrayList<>();
			while (sections.next()) {
				sectieList.add(sections.getString(1));
			}

			connection.close();
			return sectieList.toArray(new String[sectieList.size()]);
		} catch (SQLException ex) {
			CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.BuilderWarpData.sendSectieList");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}
		return null;
	}

	public static void createWarpPlayer(Player player, String WarpName, String Sectie){

		Location warpLoc = player.getLocation();
		try {
			Connection connection = CoreMain.getConnection();
			PreparedStatement getSectie = connection
					.prepareStatement("SELECT name FROM MCServer.BUI_sectieNames WHERE name = ?;");
			getSectie.setString(1, Sectie);
			ResultSet resultName = getSectie.executeQuery();
			resultName.next();

			connection.close();

		} catch (SQLException ex) {
			CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.BuilderWarpData.createWarpPlayer: select Name");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}

		try {
			Connection connection = CoreMain.getConnection();
			PreparedStatement warpSet = connection.prepareStatement(
					"INSERT INTO MCServer.BUI_warpStore VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE locX=?, locY=?, locZ=?;");
			// normal insert statement
			warpSet.setString(1, Sectie + WarpName);
			warpSet.setDouble(2, warpLoc.getX());
			warpSet.setDouble(3, warpLoc.getY());
			warpSet.setDouble(4, warpLoc.getZ());
			warpSet.setString(5, WarpName);
			warpSet.setString(6, Sectie);

			// for if duplicate
			warpSet.setDouble(7, warpLoc.getX());
			warpSet.setDouble(8, warpLoc.getY());
			warpSet.setDouble(9, warpLoc.getZ());

			warpSet.execute();

			connection.close();

		} catch (SQLException ex) {
			CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.BuilderWarpData.createWarpPlayer: insert/update warp loc");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}

	}

	public static void createSectie(String sectieName, Player player) {

		try {
			Connection connection = CoreMain.getConnection();
			PreparedStatement statSectionList = connection
					.prepareStatement("INSERT INTO MCServer.BUI_sectieNames VALUES (?, ?);");
			statSectionList.setString(1, sectieName);
			statSectionList.setString(2, player.getUniqueId().toString());
			statSectionList.execute();

			connection.close();

		} catch (SQLException ex) {
			CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.BuilderWarpData.createSectie");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}
	}

	public static ArrayList<String> ReturnSectieList() {
		ArrayList<String> ReturnList = new ArrayList<>();
		try {
			Connection connection = CoreMain.getConnection();
			ResultSet sections = connection.prepareStatement("SELECT name FROM MCServer.BUI_sectieNames;")
					.executeQuery();

			while (sections.next()) {
				ReturnList.add(sections.getString(1));
			}
			connection.close();
			return ReturnList;
		} catch (SQLException ex) {
			CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.BuilderWarpData.ReturnSectieList");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}
		return ReturnList;
	}

	public static List<String> ReturnWarpList(String Sectie) {
		ArrayList<String> ReturnList = new ArrayList<>();

		try {
			Connection connection = CoreMain.getConnection();
			PreparedStatement getWarps = connection
					.prepareStatement("SELECT name FROM MCServer.BUI_warpStore WHERE sectie = ?;");
			getWarps.setString(1, Sectie);
			ResultSet Warps = getWarps.executeQuery();

			while (Warps.next()) {
				ReturnList.add(Warps.getString(1));

			}
			connection.close();

			return ReturnList;
		} catch (SQLException ex) {
			CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.BUIWarpData.ReturnWarpList");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}
		return ReturnList;
	}
}
