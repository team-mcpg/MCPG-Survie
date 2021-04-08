package fr.milekat.MCPG_Survival.rules.events;

import org.bukkit.entity.Piglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class BarteringCancel implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onPigPick(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Piglin) {
            event.setCancelled(true);
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onPigDrop(EntityDropItemEvent event) {
        if (event.getEntity() instanceof Piglin) {
            event.setCancelled(true);
        }
    }
}
