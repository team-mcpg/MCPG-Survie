package fr.milekat.MCPG_Survival.survival;

import fr.milekat.MCPG_Survival.survival.events.ServerSwitch;
import fr.milekat.MCPG_Survival.survival.events.Spawn;
import org.bukkit.plugin.Plugin;

public class SurvivalManager {
    public SurvivalManager(Plugin plugin) {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        plugin.getServer().getPluginManager().registerEvents(new ServerSwitch(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new Spawn(), plugin);
    }
}
