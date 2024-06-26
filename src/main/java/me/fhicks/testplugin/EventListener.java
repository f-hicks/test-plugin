package me.fhicks.testplugin;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;


import java.io.IOException;


public class EventListener implements Listener { //Implements the Listener interface

    // TODO: check which player each event is for, and potentially send a api request to a different server for each player.

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Bukkit.getLogger().info("Player " + event.getPlayer().getName() + " has joined the server!");
        event.getPlayer().sendMessage("Welcome to the server!");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws IOException {
        HomeAssistantAPI.sendRequest("/api/services/script/turn_on", "script.mc_death");
    }

    @EventHandler
    public void onPlayerXP(PlayerPickupExperienceEvent event) throws IOException {
        HomeAssistantAPI.sendRequest("/api/services/script/turn_on", "script.mc_xp");
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) throws IOException {
        if (event.getEntity() instanceof Player){ // check if it is a player being damaged
            //currently still shows this if the damage is blocked e.g. by a shield
            HomeAssistantAPI.sendRequest("/api/services/script/turn_on", "script.mc_damage");

        }
    }
}