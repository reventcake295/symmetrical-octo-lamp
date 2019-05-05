package com.revent.villager.villagers;

import org.bukkit.Location;

public abstract class ReventVillager {

	private Location location;

	private int xp;

	private int level;

	public abstract void interact();

	public abstract void upgrade();

	public abstract void xpGain();

	public abstract void levelUp();

	public abstract void move();


//	TODO: villager crafting and working

//	smithing
//	    tools createn of iron and diamond
//	    armor createn of iron and diamond
//	fletching
//	    bows and arrows createn
//	crafting
//	    create basic materials needed by other proffesions
//	cooking
//	    make food for you and the other villagers
//	mining (create mines that give certain recourses at certain levels
//	    gather materials from specific locations

//	information broker
//	    trades in information allows you to see all relevant information at one location where new information can be bought and given for money
//	leatherworker:
//		create leather armor and other leather elated stuff
//
//	difrent functionality in difrent worlds:
//	roofwars: farming, smithing, crafting, cooking, information broker, trading
//	hub: information broker, trading
//	survival: information broker, trading, farming, figthing
//	skyblock: information broker, trading, farming, fighting
//
//	villager upgrading:
//	upgrade tree of villagers:
//	villager
//	    farmer
//		crafter
//		    fletcher
//			leatherworker
//			cooking
//			smithing
//		trading
//		    information broker
//		guard
//		    mage (am i certain i want to do this?)
//	        healer
}
