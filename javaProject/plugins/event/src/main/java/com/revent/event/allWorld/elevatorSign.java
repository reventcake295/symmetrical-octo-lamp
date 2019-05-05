package com.revent.event.allWorld;

import com.revent.returnstate.ReturnError;
import com.revent.types.ErrorType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class elevatorSign implements Listener {

	@EventHandler
	public void ElevatorSign(PlayerInteractEvent event) {
		//make sure that it was a right click and that it was a sign on the wall
		if (!(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.WALL_SIGN)))
			return;

		//get the needed data
		Block signBlock = event.getClickedBlock();
		Player player = event.getPlayer();
		Sign sign = (Sign) signBlock.getState();

		//make sure that the line is correctly writen
		if (!(sign.getLine(1).equals("[elevator up]") || sign.getLine(1).equals("[elevator down]")))
			return;

		//setting the required vars
		Location loc;
		boolean found = false;
		int x = signBlock.getLocation().getBlockX();
		int z = signBlock.getLocation().getBlockZ();
		double y1 = 0;

		//look upwards for another elevator sign
		if (sign.getLine(1).equals("[elevator up]")) {
			//looking for the required sign
			for (int i = 3; i < 12; i++) {
				int y = signBlock.getLocation().getBlockY() + i;
				loc = new Location(signBlock.getWorld(), x, y, z);
				if (((Sign) loc.getBlock().getState()).getLine(1).equals("[elevator up]")
						|| ((Sign) loc.getBlock().getState()).getLine(1).equals("[elevator down]")) {
					//check if the block on wich the player will land is solid or not
					if (!(player.getLocation().add(0, y1 - 1, 0).getBlock().getType().isSolid()))
						break;
					found = true;
					y1 = i - 0.9;
					break;
				}
			}
			//make sure that the sign is found
			if (!found) {
				ReturnError.sendErrorToPlayer(false, ErrorType.noDestination, player);
				return;
			}
			//teleport the player to the destination
			player.teleport(player.getLocation().add(0, y1, 0));

		} else {//look downwards

			//looking for the required sign
			for (int i = 3; i < 12; i++) {
				int y = signBlock.getLocation().getBlockY() - i;
				loc = new Location(signBlock.getWorld(), x, y, z);
				if (((Sign) loc.getBlock().getState()).getLine(1).equals("[elevator up]")
						|| ((Sign) loc.getBlock().getState()).getLine(1).equals("[elevator down]")) {
					//check if the block on wich the player will land is solid or not
					if (!(player.getLocation().subtract(0, y1 + 1, 0).getBlock().getType().isSolid()))
						return;
					found = true;
					y1 = i + 0.9;
					break;
				}
			}
			//make sure that the sign is found
			if (!found) {
				ReturnError.sendErrorToPlayer(true, ErrorType.noDestination, player);
				return;
			}
			//teleport the player to the destination
			player.teleport(player.getLocation().subtract(0, y1, 0));
		}
	}
}
/* elevator setup
sign[elevator up]
player pos
block
air
air
air
air
air
air
air
air
block
sign[elevator down]
player pos
block
 */