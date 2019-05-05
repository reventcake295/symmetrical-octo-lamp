package com.revent.serverData;

import com.revent.util.ItemStackUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class WorldData {

    // world data
    public HashMap<String, org.bukkit.World> worlds = new HashMap<>();

    public HashMap<String, Location> worldSpawnLocations = new HashMap<>();

    public List<String> WorldNames = new ArrayList<>();

    public List<String> serverWorlds = new ArrayList<>();

    public List<String> locWorld = new ArrayList<>();

    public List<String> combatWorld = new ArrayList<>();

    public List<String> kitWorld = new ArrayList<>();

    public HashMap<String, Boolean> worldClosed = new HashMap<>();

    public HashMap<String, String> worldName = new HashMap<>();

    public HashMap<String, ItemStack> worldItem = new HashMap<>();

    public HashMap<ItemStack, String> itemWorld = new HashMap<>();

    //kits
    public HashMap<String, ArrayList<ItemStack>> kitOptions = new HashMap<>();

    public HashMap<String, HashMap<ItemStack, String>> kitsPermission = new HashMap<>();

    public HashMap<String, HashMap<String, String>> kitsPermissionString = new HashMap<>();

    public HashMap<String, HashMap<String, ArrayList<ItemStack>>> kits = new HashMap<>();

    public HashMap<String, HashMap<ItemStack, String>> kitsName = new HashMap<>();

    public WorldData() {
        registerWorld("hub", "world", ItemStackUtil.nameItem(Material.COMPASS, ChatColor.GREEN + "Hub", ""), new Location(Bukkit.getWorld("world"), 33.5, 74, 264.5), FALSE, FALSE, FALSE, TRUE, FALSE);

        //-------------------------------------------------------------skyblock group
        registerWorld("skyblock", "BSkyBlock_world", ItemStackUtil.nameItem(Material.BEDROCK, ChatColor.DARK_PURPLE + "Skyblock", "SkyBlock"), new Location(Bukkit.getWorld("BSkyBlock_world"), -0.5, 122, 2.5), FALSE, TRUE, FALSE, TRUE, TRUE);
        registerWorld("netherSkyblock", "BSkyBlock_world_nether", ItemStackUtil.nameItem(Material.BEDROCK, ChatColor.DARK_PURPLE + "nether Skyblock", "SkyBlock"), new Location(Bukkit.getWorld("BSkyBlock_world_nether"), -0.5, 122, 2.5), FALSE, TRUE, TRUE, FALSE, FALSE);
        registerWorld("endSkyblock", "BSkyBlock_world_the_end", ItemStackUtil.nameItem(Material.BEDROCK, ChatColor.DARK_PURPLE + "Skyblock", "end SkyBlock"), new Location(Bukkit.getWorld("BSkyBlock_world_the_end"), -0.5, 122, 2.5), FALSE, TRUE, TRUE, FALSE, FALSE);
        //-------------------------------------------------------------skyblock group

        registerWorld("survival", "survival", ItemStackUtil.nameItem(Material.GRASS, ChatColor.GREEN + "Survival", "Survival"), new Location(Bukkit.getWorld("survival"), 263.5, 75.5, 10.5), FALSE, TRUE, FALSE, TRUE, TRUE);

        registerWorld("builderworld", "Builder_World", ItemStackUtil.nameItem(Material.STONE, ChatColor.BOLD + "Builder world", ""), new Location(Bukkit.getWorld("Builder_World"), -553.5, 61, -281.5), FALSE, FALSE, FALSE, TRUE, FALSE);

        registerWorld("minetopia", "minetopia", ItemStackUtil.nameItem(Material.WHITE_BED, ChatColor.GREEN + "minetopia", ""), new Location(Bukkit.getWorld("minetopia"), -553.5, 61, -281.5), FALSE, TRUE, FALSE, TRUE, FALSE);

        registerWorld("prison", "prison", ItemStackUtil.nameItem(Material.IRON_BARS, ChatColor.GREEN + "Prison", ""), new Location(Bukkit.getWorld("prison"), -553.5, 61, -281.5), TRUE, TRUE, FALSE, TRUE, TRUE);

        registerWorld("kitpvp", "kitPvp", ItemStackUtil.nameItem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GREEN + "Kit Pvp", ""), new Location(Bukkit.getWorld("kitPvp"), -553.5, 61, -281.5), TRUE, FALSE, FALSE, TRUE, TRUE);

        registerWorld("factions", "factions", ItemStackUtil.nameItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Factions", ""), new Location(Bukkit.getWorld("factions"), -553.5, 61, -281.5), TRUE, TRUE, FALSE, TRUE, TRUE);

        registerWorld("dungeoncrawler", "dungeonCrawler", ItemStackUtil.nameItem(Material.IRON_SWORD, ChatColor.GREEN + "Dungeon Crawler", ""), new Location(Bukkit.getWorld("dungeonCrawler"), -553.5, 61, -281.5), TRUE, FALSE, FALSE, TRUE, FALSE);

        registerWorld("creative", "creativePlots", ItemStackUtil.nameItem(Material.DIAMOND_BLOCK, ChatColor.GREEN + "Creative", ""), new Location(Bukkit.getWorld("creativePlots"), -553.5, 61, -281.5), FALSE, TRUE, FALSE, TRUE, FALSE);

        //-------------------------------------------------------------island survival group
        registerWorld("islandsurvival", "AcidIsland_world", ItemStackUtil.nameItem(Material.WATER_BUCKET, ChatColor.GREEN + "Island survival", ""), new Location(Bukkit.getWorld("AcidIsland_world"), -0.5, 122, 2.5), FALSE, TRUE, FALSE, TRUE, TRUE);
        registerWorld("netherIslandsurvival", "AcidIsland_world_nether", ItemStackUtil.nameItem(Material.WATER_BUCKET, ChatColor.GREEN + "nether Island survival", ""), new Location(Bukkit.getWorld("AcidIsland_world_nether"), -0.5, 122, 2.5), FALSE, TRUE, TRUE, FALSE, FALSE);
        registerWorld("EndIslandsurvival", "AcidIsland_world_the_end", ItemStackUtil.nameItem(Material.WATER_BUCKET, ChatColor.GREEN + "end Island survival", ""), new Location(Bukkit.getWorld("AcidIsland_world_the_end"), -0.5, 122, 2.5), FALSE, TRUE, TRUE, FALSE, FALSE);
        //-------------------------------------------------------------island survival group

        //-------------------------------------------------------------old/deprecated worlds
        // TODO: remove but copy fisrt spawn build from the world: ASkyBlock to the new world: BSkyBlock_world
        registerWorld("AcidIsland", "AcidIsland", ItemStackUtil.nameItem(Material.WATER_BUCKET, ChatColor.RED + "old Island survival", "deprecated"), new Location(Bukkit.getWorld("AcidIsland"), -0.5, 122, 2.5), FALSE, FALSE, TRUE, FALSE, FALSE);
        registerWorld("ASkyBlock", "ASkyBlock", ItemStackUtil.nameItem(Material.WATER_BUCKET, ChatColor.RED + "old nether Island survival", "deprecated"), new Location(Bukkit.getWorld("ASkyBlock"), -0.5, 122, 2.5), FALSE, FALSE, TRUE, FALSE, FALSE);
        registerWorld("ASkyBlock_nether", "ASkyBlock_nether", ItemStackUtil.nameItem(Material.WATER_BUCKET, ChatColor.RED + "old end Island survival", "deprecated"), new Location(Bukkit.getWorld("ASkyBlock_nether"), -0.5, 122, 2.5), FALSE, FALSE, TRUE, FALSE, FALSE);

        // worlds that are just there no idea what to do whit them
        registerWorld("world_end", "world_end", ItemStackUtil.nameItem(Material.BEDROCK, ChatColor.GREEN + "world_end", "unknown"), new Location(Bukkit.getWorld("world_end"), -0.5, 122, 2.5), FALSE, FALSE, TRUE, FALSE, FALSE);
        registerWorld("world_nether", "world_nether", ItemStackUtil.nameItem(Material.BEDROCK, ChatColor.GREEN + "world_nether", "unknown"), new Location(Bukkit.getWorld("world_nether"), -0.5, 122, 2.5), FALSE, FALSE, TRUE, FALSE, FALSE);
        registerWorld("world_the_end", "world_the_end", ItemStackUtil.nameItem(Material.BEDROCK, ChatColor.GREEN + "world_the_end", "unknown"), new Location(Bukkit.getWorld("world_the_end"), -0.5, 122, 2.5), FALSE, FALSE, TRUE, FALSE, FALSE);
        //-------------------------------------------------------------old/deprecated worlds

        //-------------------------------------------------------------kits init
        //hashmaps used throughout the complete init of the kits
        HashMap<String, ArrayList<ItemStack>> kit = new HashMap<>();
        HashMap<ItemStack, String> kitPermission = new HashMap<>();
        HashMap<String, String> kitPermissionString = new HashMap<>();
        HashMap<ItemStack, String> kitName = new HashMap<>();
        //-------------------------------------------------------------survival init
        kitOptions.put("survival", new ArrayList<>(Arrays.asList(ItemStackUtil.nameItem(Material.STONE_SWORD, "normal", "normal kit"),
                ItemStackUtil.nameItem(Material.DIAMOND_SWORD, "diamond", "diamond kit"))));

        //init of the kit themselfs
        kitPermission.put(ItemStackUtil.nameItem(Material.STONE_SWORD, "normal", "normal kit"), "");
        kitName.put(ItemStackUtil.nameItem(Material.STONE_SWORD, "normal", "normal kit"), "normal");
        kitPermissionString.put("normal", "");
        kit.put("normal", new ArrayList<>(Arrays.asList(
                new ItemStack(Material.STONE_SWORD),
                new ItemStack(Material.STONE_HOE),
                new ItemStack(Material.STONE_PICKAXE),
                new ItemStack(Material.GOLDEN_SHOVEL),
                new ItemStack(Material.CHAINMAIL_BOOTS),
                new ItemStack(Material.CHAINMAIL_LEGGINGS),
                new ItemStack(Material.CHAINMAIL_CHESTPLATE),
                new ItemStack(Material.CHAINMAIL_HELMET))));

        kitPermission.put(ItemStackUtil.nameItem(Material.DIAMOND_SWORD, "diamond", "diamond kit"), "group.diamond");
        kitName.put(ItemStackUtil.nameItem(Material.DIAMOND_SWORD, "diamond", "diamond kit"), "diamond");
        kitPermissionString.put("diamond", "group.diamond");
        kit.put("diamond", new ArrayList<>(Arrays.asList(
                new ItemStack(Material.DIAMOND_SWORD),
                new ItemStack(Material.DIAMOND_HOE),
                new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.DIAMOND_SHOVEL),
                new ItemStack(Material.DIAMOND_BOOTS),
                new ItemStack(Material.DIAMOND_LEGGINGS),
                new ItemStack(Material.DIAMOND_CHESTPLATE),
                new ItemStack(Material.DIAMOND_HELMET))));

        //putting them into the global hashmaps and then clearing the local hashmaps
        kits.put("survival", kit);
        kitsPermission.put("survival", kitPermission);
        kitsPermissionString.put("survival", kitPermissionString);
        kitsName.put("survival", kitName);
        kitName.clear();
        kitPermissionString.clear();
        kit.clear();
        kitPermission.clear();
        //-------------------------------------------------------------end of survival init

        //-------------------------------------------------------------skyblock init
        kitOptions.put("BSkyBlock_world", new ArrayList<>(Arrays.asList()));

        //init of the kit themselfs
        kitPermission.put(ItemStackUtil.nameItem(Material.STONE_SWORD, "", ""), "");
        kitPermissionString.put("", "");
        kitPermission.put(ItemStackUtil.nameItem(Material.STONE_SWORD, "", ""), "");
        kit.put("", new ArrayList<>(Arrays.asList(
                new ItemStack(Material.STONE_SWORD),
                new ItemStack(Material.CHAINMAIL_HELMET))));

        //putting them into the global hashmaps and then clearing the local hashmaps
        kits.put("BSkyBlock_world", kit);
        kitsPermission.put("BSkyBlock_world", kitPermission);
        kitsPermissionString.put("BSkyBlock_world", kitPermissionString);
        kitsName.put("BSkyBlock_world", kitName);
        kitName.clear();
        kitPermissionString.clear();
        kit.clear();
        kitPermission.clear();
        // end skyblock init

        // prison init
        kitOptions.put("prison", new ArrayList<>(Arrays.asList()));

        //init of the kit themselfs
        kitPermission.put(ItemStackUtil.nameItem(Material.STONE_SWORD, "", ""), "");
        kitPermission.put(ItemStackUtil.nameItem(Material.STONE_SWORD, "", ""), "");
        kitPermissionString.put("", "");
        kit.put("", new ArrayList<>(Arrays.asList(
                new ItemStack(Material.STONE_SWORD),
                new ItemStack(Material.CHAINMAIL_HELMET))));

        //putting them into the global hashmaps and then clearing the local hashmaps
        kits.put("prison", kit);
        kitsPermission.put("prison", kitPermission);
        kitsPermissionString.put("prison", kitPermissionString);
        kitsName.put("prison", kitName);
        kitName.clear();
        kitPermissionString.clear();
        kit.clear();
        kitPermission.clear();
        //-------------------------------------------------------------end prison init

        //-------------------------------------------------------------kitPvp init
        kitOptions.put("kitPvp", new ArrayList<>(Arrays.asList()));

        //init of the kit themselfs
        kitPermission.put(ItemStackUtil.nameItem(Material.STONE_SWORD, "", ""), "");
        kitPermission.put(ItemStackUtil.nameItem(Material.STONE_SWORD, "", ""), "");
        kitPermissionString.put("", "");
        kit.put("", new ArrayList<>(Arrays.asList(
                new ItemStack(Material.STONE_SWORD),
                new ItemStack(Material.CHAINMAIL_HELMET))));

        //putting them into the global hashmaps and then clearing the local hashmaps
        kits.put("kitPvp", kit);
        kitsPermission.put("kitPvp", kitPermission);
        kitsPermissionString.put("kitPvp", kitPermissionString);
        kitsName.put("kitPvp", kitName);
        kitName.clear();
        kitPermissionString.clear();
        kit.clear();
        kitPermission.clear();
        //-------------------------------------------------------------end kitPvp init

        //-------------------------------------------------------------factions init
        kitOptions.put("factions", new ArrayList<>(Arrays.asList()));

        //init of the kit themselfs
        kitPermission.put(ItemStackUtil.nameItem(Material.STONE_SWORD, "", ""), "");
        kitPermission.put(ItemStackUtil.nameItem(Material.STONE_SWORD, "", ""), "");
        kitPermissionString.put("", "");
        kit.put("", new ArrayList<>(Arrays.asList(
                new ItemStack(Material.STONE_SWORD),
                new ItemStack(Material.CHAINMAIL_HELMET))));

        //putting them into the global hashmaps and then clearing the local hashmaps
        kits.put("factions", kit);
        kitsPermission.put("factions", kitPermission);
        kitsPermissionString.put("factions", kitPermissionString);
        kitsName.put("factions", kitName);
        kitName.clear();
        kitPermissionString.clear();
        kit.clear();
        kitPermission.clear();
        //-------------------------------------------------------------end factions init

        //-------------------------------------------------------------AcidIsland_world init
        kitOptions.put("AcidIsland_world", new ArrayList<>(Arrays.asList()));

        //init of the kit themselfs
        kitPermission.put(ItemStackUtil.nameItem(Material.STONE_SWORD, "", ""), "");
        kitPermission.put(ItemStackUtil.nameItem(Material.STONE_SWORD, "", ""), "");
        kitPermissionString.put("", "");
        kit.put("", new ArrayList<>(Arrays.asList(
                new ItemStack(Material.STONE_SWORD),
                new ItemStack(Material.CHAINMAIL_HELMET))));

        //putting them into the global hashmaps and then clearing the local hashmaps
        kits.put("AcidIsland_world", kit);
        kitsPermission.put("AcidIsland_world", kitPermission);
        kitsPermissionString.put("AcidIsland_world", kitPermissionString);
        kitsName.put("AcidIsland_world", kitName);
        kitName.clear();
        kitPermissionString.clear();
        kit.clear();
        kitPermission.clear();
        //-------------------------------------------------------------end AcidIsland_world init

        //-------------------------------------------------------------end kits init
    }

    private void registerWorld(String worldName, String world, ItemStack item, Location spawnLocation, Boolean combatWorld, Boolean locWorld, Boolean closed, Boolean serverWorld, Boolean kitWorld) {
        WorldNames.add(worldName); //world name for user
        if (locWorld) {
            this.locWorld.add(world); // actual name
        }
        if (combatWorld) {
            this.combatWorld.add(worldName);//actual name
        }
        worlds.put(worldName, Bukkit.getWorld(world)); // name for user / actual name
        worldSpawnLocations.put(worldName, spawnLocation); // name for user / spawn location
        worldClosed.put(worldName, closed); // name for user / wheter or not world is closed at startup of server
        this.worldName.put(world, worldName); // actual name / name for user
        if (serverWorld) {
            serverWorlds.add(worldName); // name for user
        }
        if (kitWorld) {
            this.kitWorld.add(world);
        }
        //put the name and the itemstack in both ways so we can get it if we have either of them
        worldItem.put(worldName, item);
        itemWorld.put(item, worldName);
    }

    public void closeWorld(String worldName, Boolean removeFromServerList) {
        if (removeFromServerList)
            serverWorlds.remove(worldName);
        worldClosed.put(worldName, Boolean.TRUE);
    }

    public void openWorld(String worldName, Boolean addToSeverList) {
        if (addToSeverList)
            serverWorlds.add(worldName);
        worldClosed.put(worldName, Boolean.FALSE);
    }
}
