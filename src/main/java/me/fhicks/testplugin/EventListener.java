package me.fhicks.testplugin;

import io.github.cdimascio.dotenv.Dotenv;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.URL;


public class EventListener implements Listener { //Implements the Listener interface

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Bukkit.getLogger().info("Player " + event.getPlayer().getName() + " has joined the server!");
        event.getPlayer().sendMessage("Welcome to the server!");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws IOException {
        Dotenv dotenv = Dotenv.load();

        String lightEntityId = dotenv.get("HA_SCRIPT_NAME");
        URL url = new URL(dotenv.get("HA_URL")+"/api/services/script/turn_on");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Authorization", dotenv.get("HA_API_KEY"));
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);

        String jsonInputString = "{\"entity_id\": \"" + lightEntityId + "\"}";

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int status = con.getResponseCode();
        if (status == 200) {
            Bukkit.getLogger().info("Successfully turned on the lights.");
        } else {
            Bukkit.getLogger().info("Failed to turn on the lights. Response code: " + status);
        }
    }

}