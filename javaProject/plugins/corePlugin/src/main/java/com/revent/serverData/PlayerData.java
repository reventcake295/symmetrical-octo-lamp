package com.revent.serverData;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerData {

    private HashMap<org.bukkit.entity.Player, org.bukkit.entity.Player> conversations = new HashMap<>();

    public ArrayList<org.bukkit.entity.Player> playerFight = new ArrayList<>();

    public ArrayList<org.bukkit.entity.Player> vanished = new ArrayList<>();

    public HashMap<String, org.bukkit.entity.Player> playerNicknamed = new HashMap<>();

    public HashMap<org.bukkit.entity.Player, String> nicknamedPlayer = new HashMap<>();

    public ArrayList<org.bukkit.entity.Player> waitList = new ArrayList<>();

    public HashMap<Player,Long> combatLog = new HashMap<>();

    public void setReplyTarget(org.bukkit.entity.Player messenger, org.bukkit.entity.Player reciever) {
        this.conversations.put(messenger, reciever);
        this.conversations.put(reciever, messenger);
    }

    public org.bukkit.entity.Player getReplyTarget(org.bukkit.entity.Player messenger){
        return this.conversations.get(messenger);
    }

    public Boolean playerInFight(org.bukkit.entity.Player player) {
        return playerFight.contains(player);
    }

    public void playerLeave(org.bukkit.entity.Player player) {//this function is made because of data storage size reduction
        //remove the player data from this class because it isn't needed anymore
        //TODO: implement the removal of player data in conversations, playerFight, vanished, watList, playerNicknamed and nicknamedPlayer
        //quest data too when implemented after storing it on the database or store it there already when changed?

    }
}
