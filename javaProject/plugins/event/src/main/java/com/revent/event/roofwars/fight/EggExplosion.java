package com.revent.event.roofwars.fight;

import java.util.Random;

import com.revent.DBMain;
import com.revent.util.ItemStackUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class EggExplosion implements Listener {
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {

		//make sure its a egg thrown by player and make sure it is in the correct world
		Projectile projectile = event.getEntity();
		if (!(projectile.getType() == EntityType.EGG && projectile.getShooter() instanceof Player) &&
				projectile.getWorld().getName().equals("roofwarsFight"))
			return;

		// get the needed data
		Location location = projectile.getLocation();

		if (projectile.getCustomName().equals("Exploding egg")) {
			// spawn a explosion by chance
			if (new Random().nextInt(100) > 70) {
				projectile.getWorld().createExplosion(location.getX(), location.getY(), location.getZ(), 3, false, false);
			}
			projectile.remove();
		} else if (projectile.getCustomName().equals("Chicken egg 0"))
			explodingChicken(projectile.getWorld(), location);
		//TODO: get that other option for four chickens at once
	}

	private void explodingChicken(World world, Location location) {
		Entity chicken = world.spawnEntity(location, EntityType.CHICKEN);
		chicken.setInvulnerable(true);
		//make the chicken lay seven usable eggs
		for (int i = 0; i < 7; i++) {
			try {
				Thread.sleep(25000);
			} catch (InterruptedException e) {
				e.printStackTrace(DBMain.getErrorWriter());
			}
			chicken.getWorld().dropItemNaturally(chicken.getLocation(), new ItemStack(ItemStackUtil.nameItem(Material.EGG, "Exploding egg", "chance of exploding when it hits the ground")));
		}
		//make the chicken explode
		Location chickenLastLocation = chicken.getLocation();
		chicken.getWorld().createExplosion(chickenLastLocation.getX(), chickenLastLocation.getY(), chickenLastLocation.getZ(), 6, false, false);
		chicken.remove();
	}
}
