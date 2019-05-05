package com.revent.managers;

import com.revent.CoreMain;
import com.revent.serverData.StaticData;
import com.revent.util.InventoryUtils;
import com.revent.util.MaterialGroups;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import static java.lang.Boolean.FALSE;

@SuppressWarnings("ConstantConditions")
public class composterManager {

	public static void cauldron(ItemStack item, Inventory invChest, Inventory playerInv) {

		try {
			//get the type of item used by the player
			String itemString = MaterialGroups.toolsGroup(item.getType());

			//get the xml file whit the required data from the itemData folder
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(StaticData.itemData + "composter.xml");
			XPath xpath = XPathFactory.newInstance().newXPath();

			//compile the correct group corresponding to the item used
			NodeList ItemNodes = (NodeList) xpath.compile("composter/ItemClass[@item=" + itemString + "]").evaluate(doc, XPathConstants.NODESET);

			//get hte material and the amount required in the chest
			Material invItem = Material.getMaterial(ItemNodes.item(0).getChildNodes().item(0).getNodeValue());
			int invItemNumber = Integer.parseInt(ItemNodes.item(0).getChildNodes().item(1).getNodeValue());

			//check wheter or not it is present in the chest
			if (!(invChest.containsAtLeast(new ItemStack(invItem), invItemNumber)))
				return;

			//remove the items from the chest
			InventoryUtils.removeItem(invChest, new ItemStack(invItem, invItemNumber), FALSE);

			//place the items that are returned to the player in the chest
			InventoryUtils.addItem(invChest, new ItemStack(Material.getMaterial(ItemNodes.item(1).getNodeValue()), 1));
			InventoryUtils.addItem(invChest, new ItemStack(Material.getMaterial(ItemNodes.item(2).getNodeValue()),1));

			//check wether or not the item is supposed to be dameged or that you lose one of it
			if (Boolean.getBoolean(ItemNodes.item(3).getNodeValue())) {
				Damageable itemMeta = (Damageable) item.getItemMeta();
				itemMeta.setDamage(itemMeta.getDamage() + 1);
				item.setItemMeta((ItemMeta)itemMeta);
			} else
				InventoryUtils.removeItem(playerInv, item, FALSE);

		} catch (Exception exception) {
			CoreMain.getErrorWriter().println("--------------------------------------------------CorePlugin.composterManager: xml reading");
			exception.printStackTrace(CoreMain.getErrorWriter());
		}
	}
}
