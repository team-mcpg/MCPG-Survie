package fr.milekat.MCPG_Survival.rules;

import fr.milekat.MCPG_Survival.rules.events.BarteringCancel;
import fr.milekat.MCPG_Survival.rules.events.MobEmeraldDrop;
import fr.milekat.MCPG_Survival.rules.events.VillagersCustom;
import fr.milekat.MCPG_Survival.rules.events.WaitEnd;
import org.bukkit.plugin.Plugin;

public class RulesManager {
    public RulesManager(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new BarteringCancel(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new VillagersCustom(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new MobEmeraldDrop(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new WaitEnd(), plugin);
    }
}
