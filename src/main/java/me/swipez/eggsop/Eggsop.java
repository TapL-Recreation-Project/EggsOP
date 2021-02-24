package me.swipez.eggsop;

import org.bukkit.plugin.java.JavaPlugin;

public final class Eggsop extends JavaPlugin {

    boolean gamestarted = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new EggListener(this), this);
        getCommand("eggsop").setExecutor(new EggCommand(this));
        getCommand("eggsop").setTabCompleter(new CommandComplete());
        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
