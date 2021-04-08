package fr.milekat.MCPG_Survival;

import fr.milekat.MCPG_Survival.core.CoreManager;
import fr.milekat.MCPG_Survival.survival.SurvivalManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainSurvival extends JavaPlugin {
    public static String PREFIX = "§7[§bLa Cité Givrée§7]§r ";
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0;");
    public static ArrayList<Player> SAFE_PLAYERS = new ArrayList<>();

    @Override
    public void onEnable() {
        new CoreManager(this);
        new SurvivalManager(this);
    }
}
