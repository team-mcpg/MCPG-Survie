package fr.milekat.MCPG_Survival.survival.events;

import fr.milekat.MCPG_Survival.MainSurvival;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class Spawn implements Listener {
    private final int AREA_RED = 75;
    private final int AREA_GREEN = 50;
    private final World WORLD = Bukkit.getWorld("world");

    @EventHandler(ignoreCancelled = true)
    public void onSpawnDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && MainSurvival.SAFE_PLAYERS.contains((Player) event.getEntity())) {
            event.setCancelled(true);
        }
    }

    @EventHandler (ignoreCancelled = true)
    public void onSpawnHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && MainSurvival.SAFE_PLAYERS.contains((Player) event.getDamager())) {
            event.setCancelled(true);
            denyInteract(((Player) event.getDamager()));
        }
    }

    @EventHandler (ignoreCancelled = true)
    public void onSpawnShoot(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player && MainSurvival.SAFE_PLAYERS.contains((Player) event.getEntity())) {
            event.setCancelled(true);
            denyInteract(((Player) event.getEntity()));
        }
    }

    @EventHandler
    public void onSpawnSet(PlayerMoveEvent event) {
        Location pLoc = event.getPlayer().getLocation();
        if (MainSurvival.SAFE_PLAYERS.contains(event.getPlayer())) {
            /* Leaving AREA_RED */
            if ((pLoc.getBlockX() > AREA_RED || pLoc.getBlockX() < -AREA_RED || pLoc.getBlockZ() > AREA_RED || pLoc.getBlockZ() < -AREA_RED
                    || (pLoc.getWorld()!=null && !pLoc.getWorld().equals(WORLD)))) {
                MainSurvival.SAFE_PLAYERS.remove(event.getPlayer());
                event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cVous n'êtes plus protégé."));
            }
        } else {
            /* Enter in AREA_GREEN */
            if ((pLoc.getBlockX() <= AREA_GREEN && pLoc.getBlockX() >= -AREA_GREEN && pLoc.getBlockZ() <= AREA_GREEN && pLoc.getBlockZ() >= -AREA_GREEN)
                    && pLoc.getWorld()!=null && pLoc.getWorld().equals(WORLD)) {
                MainSurvival.SAFE_PLAYERS.add(event.getPlayer());
                event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§aVous êtes désormais safe."));
            }
        }
    }

    @EventHandler
    public void onJoinProtect(PlayerJoinEvent event) {
        MainSurvival.SAFE_PLAYERS.add(event.getPlayer());
    }

    @EventHandler (ignoreCancelled = true)
    public void onSpawnPlace(BlockPlaceEvent event) {
        if (hasToBeCancelled(event.getPlayer(), event.getBlock().getLocation())) event.setCancelled(true);
    }

    @EventHandler (ignoreCancelled = true)
    public void onSpawnBucketPlace(PlayerBucketEmptyEvent event) {
        if (hasToBeCancelled(event.getPlayer(), event.getBlock().getLocation())) event.setCancelled(true);
    }

    @EventHandler (ignoreCancelled = true)
    public void onSpawnBucketFill(PlayerBucketFillEvent event) {
        if (hasToBeCancelled(event.getPlayer(), event.getBlock().getLocation())) event.setCancelled(true);
    }

    @EventHandler (ignoreCancelled = true)
    public void onSpawnBreak(BlockBreakEvent event) {
        if (hasToBeCancelled(event.getPlayer(), event.getBlock().getLocation())) event.setCancelled(true);
    }

    /**
     * Check if the player can do the action at location, if no return true
     */
    private boolean hasToBeCancelled(Player player, Location location) {
        if ((location.getX() <= AREA_RED && location.getX() >= -AREA_RED && location.getZ() <= AREA_RED && location.getZ() >= -AREA_RED)
                && location.getWorld()!=null && location.getWorld().equals(WORLD)) {
            if (!player.getGameMode().equals(GameMode.CREATIVE)) {
                denyAction(player);
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onForm(BlockFormEvent event) {
        Location location = event.getBlock().getLocation();
        if ((location.getBlockX() <= AREA_RED && location.getBlockX() >= -AREA_RED && location.getBlockZ() <= AREA_RED && location.getBlockZ() >= -AREA_RED)
                && location.getWorld()!=null && location.getWorld().equals(WORLD)) {
            event.setCancelled(true);
        }
    }

    @EventHandler (ignoreCancelled = true)
    public void onMobSpawn(CreatureSpawnEvent event) {
        Location location = event.getLocation();
        if ((location.getBlockX() <= AREA_GREEN && location.getBlockX() >= -AREA_GREEN && location.getBlockZ() <= AREA_GREEN && location.getBlockZ() >= -AREA_GREEN)
                && location.getWorld()!=null && location.getWorld().equals(WORLD)) {
            event.setCancelled(true);
        }
    }

    @EventHandler (ignoreCancelled = true)
    public void onSpawnBreakMob(EntityChangeBlockEvent event) {
        if (event.getEntityType() == EntityType.FALLING_BLOCK) return;
        Location location = event.getBlock().getLocation();
        if ((location.getX() <= AREA_RED && location.getX() >= -AREA_RED && location.getZ() <= AREA_RED && location.getZ() >= -AREA_RED)
                && location.getWorld()!=null && location.getWorld().equals(WORLD)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {
        ArrayList<Block> blockArrayList = new ArrayList<>(event.blockList());
        for (Block block : blockArrayList) {
            Location location = block.getLocation();
            if ((location.getX() <= AREA_RED && location.getX() >= -AREA_RED && location.getZ() <= AREA_RED && location.getZ() >= -AREA_RED)
                    && location.getWorld()!=null && location.getWorld().equals(WORLD)) {
                event.blockList().remove(block);
            }
        }
    }

    private void denyAction(Player player) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cAction impossible ici."));
    }

    private void denyInteract(Player player) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cVous êtes actuellement protegé."));
    }
}
