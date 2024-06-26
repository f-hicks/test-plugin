package me.fhicks.testplugin;

import io.github.cdimascio.dotenv.Dotenv;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;


import java.io.IOException;


public class EventListener implements Listener { //Implements the Listener interface

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Bukkit.getLogger().info("Player " + event.getPlayer().getName() + " has joined the server!");
        event.getPlayer().sendMessage("Welcome to the server!");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws IOException {
        HomeAssistantAPI.sendRequest("/api/services/script/turn_on", "script.mc_death");
    }

}