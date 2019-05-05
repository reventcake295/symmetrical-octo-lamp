package com.revent.managers;

import com.revent.CoreMain;
import com.revent.serverData.StaticData;
import com.revent.util.EnchantmentUtil;
import com.revent.util.InventoryUtils;
import com.revent.util.MaterialGroups;
import com.revent.util.skullUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class sieveManager {

	//the listing with the items that may be used in the sieve
	public static final List<String> ItemsWood = Arrays.asList("COBBLESTONE", "GRAVEL", "DIRT" );

	public static final List<String> ItemsIron = Arrays.asList("COBBLESTONE", "GRAVEL", "DIRT", "SAND", "GRASS" );

	@SuppressWarnings("ConstantConditions")
	public static void sieve(ItemStack item, Inventory resultChest, Block sieve) {
		try {
			//get what type of trapdoor it is
			String sieveType = MaterialGroups.WOODTRAPDOOR.contains(sieve.getType().toString()) ? "WoodenSieve" : "IronSieve";

			//get the xml file from the itemData folder
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(StaticData.itemData + "sieve.xml");
			XPath xpath = XPathFactory.newInstance().newXPath();

			//compile the chances list from the correct group
			NodeList chancesNodes = (NodeList) xpath.compile(sieveType + "/ItemClass[@item=" + item.toString() + "]/chances").evaluate(doc, XPathConstants.NODESET);

			//get a random chance out of 100
			int chance = 100;
			Random ran = new Random();
			int choice = ran.nextInt(100) + 1;

			//get the correct
			for(int i = 0; i < chancesNodes.getLength(); i++)
				chance = Integer.parseInt(chancesNodes.item(i).getNodeValue()) > choice ? Integer.parseInt(chancesNodes.item(i).getNodeValue()): chance;

			//compile the correct result item data using the chance variable
			NodeList resultItemNodes = (NodeList) xpath.compile(sieveType + "/ItemClass[@item=" + item.toString() + "]/result[@chance=" + chance + "]").evaluate(doc, XPathConstants.NODESET);

			//get the material and set the output amount to standard 1
			Material outputMaterial = Material.getMaterial(resultItemNodes.item(0).getNodeValue());
			int outputNumber = 1;

			//making sure the sieve is a iron sieve and has a trapped chest whit a diamond block next to it
			if (sieveType.equals("IronSieve") && sieve.getLocation().add(0, 1, 0).getBlock().getType() == Material.TRAPPED_CHEST
				&& (sieve.getLocation().add(1, 1, 0).getBlock().getType() == Material.DIAMOND_BLOCK
					|| sieve.getLocation().add(0, 1, 1).getBlock().getType() == Material.DIAMOND_BLOCK
					|| sieve.getLocation().add(0, 1, -1).getBlock().getType() == Material.DIAMOND_BLOCK
					|| sieve.getLocation().add(-1, 1, 0).getBlock().getType() == Material.DIAMOND_BLOCK)) {

				//then we know for sure that there are extra nodes present so get them!
				double multiplier = Double.parseDouble(resultItemNodes.item(2).getNodeValue());
				int minMaxDifference = Integer.parseInt(resultItemNodes.item(3).getNodeValue());
				boolean silkTouch = Boolean.getBoolean(resultItemNodes.item(4).getNodeValue());

				//getting the inventory of the trapped chest to see which books are present
				BlockState enchantChestState = sieve.getLocation().add(0, 1, 0).getBlock().getState();

				if (!(enchantChestState instanceof Chest))
					return;

				Inventory enchantChest = ((Chest) enchantChestState).getBlockInventory();

				//getting the result material for silk touch or for loot bonus the output amount
				if (enchantChest.contains(EnchantmentUtil.bookEnchant(Enchantment.SILK_TOUCH, 1, false)))
					outputMaterial = Material.getMaterial(resultItemNodes.item(5).getNodeValue());
				else {
					if (enchantChest.contains(EnchantmentUtil.bookEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, false)))
						multiplier = multiplier * 3;
					else if (enchantChest.contains(EnchantmentUtil.bookEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2, false)))
						multiplier = multiplier * 2;
					else if (enchantChest.contains(EnchantmentUtil.bookEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, false)))
						multiplier = multiplier * 1;
					outputNumber = ((int) Math.round(1 * multiplier)) + ran.nextInt(minMaxDifference);
				}
			}

			//get the head of the player if it is needed or just build the itemstack based on the output materail and the output amount
			ItemStack resultItemStack = outputMaterial.equals(Material.PLAYER_HEAD) ? skullUtil.getSkull(resultItemNodes.item(5).getNodeValue()) : new ItemStack(outputMaterial, outputNumber);

			//add the result items to the chest
			InventoryUtils.addItem(resultChest, resultItemStack);

		} catch (Exception exception) {
			CoreMain.getErrorWriter().println("--------------------------------------------------skyblock.sievemanager: xml reading");
			exception.printStackTrace(CoreMain.getErrorWriter());
		}
	}
}
