package fr.milekat.MCPG_Survival.core.commands;

import fr.milekat.MCPG_Survival.MainSurvival;
import fr.milekat.MCPG_Survival.core.events.MegaEmerald;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MegaEmeraldCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length==1 && sender instanceof Player) {
            try {
                ((Player) sender).getInventory().addItem(MegaEmerald.generateMegaEmerald(args[0]));
            } catch (NumberFormatException ignore) {
                sender.sendMessage(MainSurvival.PREFIX + "§cMerci de mettre un nombre entier valide.");
            }
        }
        return true;
    }
}
