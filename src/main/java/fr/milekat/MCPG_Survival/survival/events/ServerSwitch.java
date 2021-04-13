package fr.milekat.MCPG_Survival.survival.events;

import fr.milekat.MCPG_Survival.MainSurvival;
import fr.milekat.MCPG_Survival.utils.McTools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerSwitch implements Listener {
    private final Plugin MAIN;
    public ServerSwitch(Plugin plugin) {
        this.MAIN = plugin;
    }

    /**
     * Send player to server
     */
    private void sendPlayerToServer(Player player, String server) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(MAIN, "BungeeCord", b.toByteArray());
    }

    @EventHandler
    public void onDoorWalk(PlayerMoveEvent event) {
        Location max = new Location(Bukkit.getWorld("world"), 2, 76, -32);
        Location min = new Location(Bukkit.getWorld("world"), -2, 70, -34);
        if (!McTools.inArea(event.getPlayer().getLocation(), max, min)) return;
        try {
            event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 0, 70.5, -38, 180, 0));
            sendPlayerToServer(event.getPlayer(), "cite");
        } catch (IOException throwable) {
            event.getPlayer().sendMessage(MainSurvival.PREFIX + "§cErreur interne, contact le staff");
            throwable.printStackTrace();
        }
    }
}
