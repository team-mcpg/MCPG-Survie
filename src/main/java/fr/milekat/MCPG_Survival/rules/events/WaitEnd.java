package fr.milekat.MCPG_Survival.rules.events;

import fr.milekat.MCPG_Survival.utils.DateMilekat;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class WaitEnd implements Listener {
    private final Date OPEN = DateMilekat.getDate("17/04/2021 14:00:00");  //dd/MM/yyyy HH:mm:ss

    @EventHandler (priority = EventPriority.LOWEST)
    public void onEndEnter(PlayerPortalEvent event) {
        if (event.getTo()==null || event.getTo().getWorld()==null ||
                !event.getTo().getWorld().getEnvironment().equals(World.Environment.THE_END)) return;
        if (OPEN==null || OPEN.getTime() < new Date().getTime()) return;
        event.setCancelled(true);
        event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new TextComponent("§cAttente: §b" + DateMilekat.reamingToString(OPEN)));
    }

    @EventHandler (ignoreCancelled = true)
    public void onPlaceEye(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getClickedBlock()==null) return;
        if (!event.getClickedBlock().getType().equals(Material.END_PORTAL_FRAME)) return;
        if (OPEN==null || OPEN.getTime() < new Date().getTime()) return;    //  Passed, we don't need this event anymore
        for (BlockFace face : new ArrayList<>(Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST))) {
            if (event.getClickedBlock().getRelative(face).getType().equals(Material.END_PORTAL_FRAME)) {
                EndPortalFrame frame = (EndPortalFrame) event.getClickedBlock().getRelative(face).getBlockData();
                if (frame.hasEye()) {
                    event.setCancelled(true);
                    event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            new TextComponent("§cAttente: §b" + DateMilekat.reamingToString(OPEN)));
                    return;
                }
            }
        }
    }
}
