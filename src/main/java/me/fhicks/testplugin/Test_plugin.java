package me.fhicks.testplugin;

import org.bukkit.Color;
import org.bukkit.plugin.java.JavaPlugin;

public final class Test_plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info(Color.GREEN + "Enabled " + this.getName());
    }

    @Override
    public void onDisable() {
        getLogger().info(Color.RED + "Disabled " + this.getName());
    }
}
