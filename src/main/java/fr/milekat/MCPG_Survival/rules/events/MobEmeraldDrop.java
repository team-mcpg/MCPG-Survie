package fr.milekat.MCPG_Survival.rules.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class MobEmeraldDrop implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onMobDeath(EntityDeathEvent event) {
        if (event.getDrops().isEmpty()) return;
        ArrayList<ItemStack> drops = new ArrayList<>(event.getDrops());
        for (ItemStack item : drops) {
            if (item.getType().equals(Material.EMERALD)) event.getDrops().remove(item);
        }
    }
}
