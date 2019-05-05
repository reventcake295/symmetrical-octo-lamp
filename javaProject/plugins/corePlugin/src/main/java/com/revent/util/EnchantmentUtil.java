package com.revent.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class EnchantmentUtil {

	/**
	 * create a book whit a enchantment
	 * @param enchantment the enchantment to be added
	 * @param strength the strenght of the enchantment
	 * @param restriction wheter or not to force the enchantment upon it
	 * @return enchanted book whit the selected enchantment
	 */
	public static ItemStack bookEnchant(Enchantment enchantment, int strength, Boolean restriction) {

		ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);
		EnchantmentStorageMeta enchantmentMeta = (EnchantmentStorageMeta) book.getItemMeta();
		enchantmentMeta.addStoredEnchant(enchantment, strength, restriction);
		book.setItemMeta(enchantmentMeta);
		return book;
	}

	/**
	 * add a enchantment to a enchated book
	 * @param book the book the enchantment must be added upon
	 * @param enchantment the enchantment that must be added
	 * @param strength the strenght of the enchantment
	 * @param restriction wheter or not to force the enchantment upon the book
	 * @return the book with the enchantment added to it
	 */
	public static ItemStack addBookEnchant(ItemStack book, Enchantment enchantment, int strength, Boolean restriction) {

		EnchantmentStorageMeta enchantmentMeta = (EnchantmentStorageMeta) book.getItemMeta();
		enchantmentMeta.addStoredEnchant(enchantment, strength, restriction);
		book.setItemMeta(enchantmentMeta);
		return book;
	}

	//TODO: create the functions to add enchantments to all kinds of items
}
