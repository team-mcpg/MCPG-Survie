package fr.milekat.MCPG_Survival.survival.events;

import fr.milekat.MCPG_Survival.MainSurvival;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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
    public void onSignServerClick(PlayerInteractEvent event) {
        if (!MainSurvival.SAFE_PLAYERS.contains(event.getPlayer())) return;
        if (event.getClickedBlock()==null) return;
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (!(event.getClickedBlock().getBlockData() instanceof WallSign)) return;
        Sign sign = (Sign) event.getClickedBlock().getState();
        if (!sign.getLine(0).equalsIgnoreCase("§b[Retour à la cite]")) return;
        try {
            sendPlayerToServer(event.getPlayer(), "cite");
        } catch (IOException throwable) {
            event.getPlayer().sendMessage(MainSurvival.PREFIX + "§cErreur interne, contact le staff");
            throwable.printStackTrace();
        }
    }
}
