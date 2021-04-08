package fr.milekat.MCPG_Survival.core;

import fr.milekat.MCPG_Survival.core.commands.MegaEmeraldCmd;
import fr.milekat.MCPG_Survival.core.events.*;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreManager {
    public CoreManager(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new DamageModifiers(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new MegaEmerald(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new JoinMessage(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new MuteSign(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new FrozenTools(), plugin);
        plugin.getCommand("mega").setExecutor(new MegaEmeraldCmd());
    }
}
