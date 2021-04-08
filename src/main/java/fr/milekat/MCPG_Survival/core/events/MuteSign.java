package fr.milekat.MCPG_Survival.core.events;

import fr.milekat.MCPG_Survival.MainSurvival;
import fr.milekat.MCPG_Survival.core.classes.Profile;
import fr.milekat.MCPG_Survival.core.classes.ProfileManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.sql.SQLException;

public class MuteSign implements Listener {
    @EventHandler
    public void onSignEditMuted(SignChangeEvent event) {
        try {
            Profile profile = ProfileManager.getProfile(event.getPlayer().getUniqueId());
            if (profile.isMute()) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(MainSurvival.PREFIX + "§cVous êtes mute.");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
