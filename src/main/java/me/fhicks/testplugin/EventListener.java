package me.fhicks.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener { //Implements the Listener interface

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Bukkit.getLogger().info("Player " + event.getPlayer().getName() + " has joined the server!");
        event.getPlayer().sendMessage("Welcome to the server!");
    }

}