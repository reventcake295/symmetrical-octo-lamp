package com.revent.util;

import com.revent.CoreMain;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerVisibility {

    @SuppressWarnings({"null", "deprecation"})
    public static void playerAppearWorld(Player player, World world) {

        try {
            List<Player> onlinePlayers = null;
            //noinspection ConstantConditions
            onlinePlayers.addAll(Bukkit.getOnlinePlayers());

            for (Player onlinePlayer : onlinePlayers) {
                player.hidePlayer(onlinePlayer);
                onlinePlayer.hidePlayer(player);
                if (onlinePlayer.getWorld().equals(world)) {
                    player.showPlayer(onlinePlayer);
                    onlinePlayer.showPlayer(player);
                }

            }

            for (Player vanished : CoreMain.playerData.vanished) {
                if (vanished.getWorld().equals(world)) {
                    player.hidePlayer(vanished);
                }
            }

        } catch (NullPointerException ignored) {
        }
    }

    @SuppressWarnings({"null", "deprecation"})
    public static void playerAppearList(Player player, List<Player> playerList) {
        try {


            for (Player targetPlayer : playerList) {
                player.hidePlayer(targetPlayer);
                targetPlayer.hidePlayer(player);
                if (targetPlayer.getWorld().equals(player.getWorld())) {
                    player.showPlayer(targetPlayer);
                    targetPlayer.showPlayer(player);
                }

            }

            for (Player vanished : CoreMain.playerData.vanished) {
                if (vanished.getWorld().equals(player.getWorld())) {
                    player.hidePlayer(vanished);
                }
            }

        } catch (NullPointerException ignored) {
        }
    }
}
