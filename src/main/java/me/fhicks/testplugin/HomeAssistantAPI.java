package me.fhicks.testplugin;

import io.github.cdimascio.dotenv.Dotenv;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeAssistantAPI {
    public static int sendRequest(String APIPath, String entityID) throws IOException {
        Dotenv dotenv = Dotenv.load();

        URL url = new URL(dotenv.get("HA_URL")+APIPath);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Authorization", dotenv.get("HA_API_KEY"));
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);

        String jsonInputString = "{\"entity_id\": \"" + entityID + "\"}";

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int status = con.getResponseCode();
        if (status == 200) {
            return 0;
        } else {
            return status;
        }

    }
}
