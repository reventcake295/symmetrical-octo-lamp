package com.revent.command.multiWorld;

import com.revent.CoreMain;
import com.revent.util.InventoryUtils;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.revent.returnstate.ReturnError;
import com.revent.types.ErrorType;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Boolean.TRUE;

class KitCommand {

    static ResultingCommandExecutor executorMenu = (sender, args) -> {// so the person wants the menu
        // check if sender is player
        if (!(sender instanceof Player)) {
            CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
            return 0;
        }
        //getting the data required
        Player player = (Player) sender;
        String lang = player.getLocale();
        String world = player.getWorld().getName();
        //make sure the world is a kit world
        if (!(CoreMain.worldData.kitWorld.contains(world))) {
            CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.wrongCMD, lang));
            return 0;
        }
        //create the correct selector menu
        Inventory kitMenu;
        switch (world) {
            case "survival":
                kitMenu = Bukkit.createInventory(player, 9, "survival kits");
                break;
            case "BSkyBlock_world":
                kitMenu = Bukkit.createInventory(player, 9, "skyblock kits");
                break;
            case "prison":
                kitMenu = Bukkit.createInventory(player, 9, "prison kits");
                break;
            case "kitPvp":
                kitMenu = Bukkit.createInventory(player, 9, "kitPvp kits");
                break;
            case "factions":
                kitMenu = Bukkit.createInventory(player, 9, "factions kits");
                break;
            case "AcidIsland_world":
                kitMenu = Bukkit.createInventory(player, 9, "survival island kits");
                break;
            default:
                CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.wrongCMD, lang));
                return 0;
        }
        //place the kits avaible to the player in the menu
        ArrayList<ItemStack> kits = CoreMain.worldData.kitOptions.get(world);
        HashMap<ItemStack, String> kitPermissions = CoreMain.worldData.kitsPermission.get(world);
        int p = 0;
        for (int i = 0; i < CoreMain.worldData.kitOptions.size(); i++) {
            if (player.hasPermission(kitPermissions.get(kits.get(i))))
                kitMenu.setItem(p++, kits.get(i));
        }
        player.openInventory(kitMenu);
        return 1;
    };

    static ResultingCommandExecutor executorKit = (sender, args) -> {
        // check if sender is player
        if (!(sender instanceof Player)) {
            CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
            return 0;
        }
        //get the required data
        Player player = (Player) sender;
        String kit = (String) args[0];
        //make sure the player has the permission for this kit
        if (!(player.hasPermission(CoreMain.worldData.kitsPermissionString.get(player.getWorld().getName()).get((kit))))) {
            CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPerm, player.getLocale()));
        }
        //give the player the kit
        InventoryUtils.addArrayItems(player, CoreMain.worldData.kits.get(player.getWorld().getName()).get(kit), TRUE);
        return 1;
    };

}
