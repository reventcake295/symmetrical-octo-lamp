package com.revent.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revent.CoreMain;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldPlayerData {
    //TODO: up for restructure after database rewrite has been completed so this will remain old code until then
    public static void locationStore(Player player) {

        // storing the location of the player if he was in these worlds
        String world = player.getWorld().getName();
        String uuid = player.getUniqueId().toString();
        Location playerLoc = player.getLocation();
        try {
            Connection connection = CoreMain.getConnection();
            PreparedStatement StoreStatement = connection.prepareStatement(
                    "INSERT INTO MCServer.WLD_locStorage VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE locX=?, locY=?, locZ=?, fallDistance=?;");
            // insert part
            StoreStatement.setString(1, uuid + world);
            StoreStatement.setDouble(2, playerLoc.getX());
            StoreStatement.setDouble(3, playerLoc.getY());
            StoreStatement.setDouble(4, playerLoc.getZ());
            StoreStatement.setFloat(5, player.getFallDistance());

            // if duplicate part
            StoreStatement.setDouble(6, playerLoc.getX());
            StoreStatement.setDouble(7, playerLoc.getY());
            StoreStatement.setDouble(8, playerLoc.getZ());
            StoreStatement.setFloat(9, player.getFallDistance());

            StoreStatement.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.WorldPlayerData.InsertORupdate");
            ex.printStackTrace(CoreMain.getErrorWriter());
        }
    }

    public static boolean LocationTest(Player player, World world) {
        Location location = new Location(world, 0, 0, 0);
        String worldSt = world.getName();
        String uuid = player.getUniqueId().toString();

        try {
            Connection connection = CoreMain.getConnection();
            PreparedStatement TestStatement = connection
                    .prepareStatement("SELECT locX FROM MCServer.WLD_locStorage WHERE player_uuid_world = ?;");
            TestStatement.setString(1, uuid + worldSt);
            ResultSet result = TestStatement.executeQuery();
            result.next();
            location.add(0, 0, result.getDouble(1));
            connection.close();
            return true;
        } catch (SQLException ex) {
            CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.WorldPlayerData.LocationTest");
            ex.printStackTrace(CoreMain.getErrorWriter());
            return false;
        } catch (NullPointerException ex) {
            return false;
        }

    }

    public static Location locationGet(Player Player, World world) {

        Location location = new Location(world, 0, 0, 0);
        String worldSt = world.getName();
        String uuid = Player.getUniqueId().toString();

        // getting the location of the player
        try {
            Connection connection = CoreMain.getConnection();
            PreparedStatement getStatementX = connection
                    .prepareStatement("SELECT locX FROM MCServer.WLD_locStorage WHERE player_uuid_world = ?;");
            getStatementX.setString(1, uuid + worldSt);
            ResultSet resultX = getStatementX.executeQuery();
            resultX.next();
            location.add(resultX.getDouble(1), 0, 0);
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.WorldPlayerData.getLocation_locX");
            ex.printStackTrace(CoreMain.getErrorWriter());
        }

        try {
            Connection connection = CoreMain.getConnection();
            PreparedStatement getStatementY = connection
                    .prepareStatement("SELECT locY FROM MCServer.WLD_locStorage WHERE player_uuid_world = ?;");
            getStatementY.setString(1, uuid + worldSt);
            ResultSet resultY = getStatementY.executeQuery();
            resultY.next();
            location.add(0, resultY.getDouble(1), 0);
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.WorldPlayerData.getLocation_locY");
            ex.printStackTrace(CoreMain.getErrorWriter());
        }

        try {
            Connection connection = CoreMain.getConnection();
            PreparedStatement getStatementZ = connection
                    .prepareStatement("SELECT locZ FROM MCServer.WLD_locStorage WHERE player_uuid_world = ?;");
            getStatementZ.setString(1, uuid + worldSt);
            ResultSet resultZ = getStatementZ.executeQuery();
            resultZ.next();
            location.add(0, 0, resultZ.getDouble(1));
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.WorldPlayerData.getLocation_locz");
            ex.printStackTrace(CoreMain.getErrorWriter());
        }
        return location;
    }

    public static float getFallDistance(Player player, String world) {
        float fallDistance;
        String uuid = player.getUniqueId().toString();
        try {
            Connection connection = CoreMain.getConnection();
            PreparedStatement TestStatement = connection
                    .prepareStatement("SELECT fallDistance FROM MCServer.WLD_locStorage WHERE player_uuid_world = ?;");
            TestStatement.setString(1, uuid + world);
            ResultSet result = TestStatement.executeQuery();
            result.next();
            fallDistance = result.getFloat(1);
            connection.close();
        } catch (SQLException ex) {
            CoreMain.getErrorWriter().println("--------------------------------------------------CRUD.WorldPlayerData.getFallDistance");
            ex.printStackTrace(CoreMain.getErrorWriter());
            return 0;
        }
        return fallDistance;
    }
}
