package me.swipez.eggsop;

import me.swipez.eggsop.bstats.Metrics;
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
        new Metrics(this, 10447);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
