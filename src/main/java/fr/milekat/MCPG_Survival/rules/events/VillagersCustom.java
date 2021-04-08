package fr.milekat.MCPG_Survival.rules.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;

public class VillagersCustom implements Listener {
    private final Merchant VILLAGER = Bukkit.createMerchant("Villageois");
    public VillagersCustom() {
        ArrayList<MerchantRecipe> recipes = new ArrayList<>();
        MerchantRecipe recipe = new MerchantRecipe(new ItemStack(Material.COOKED_BEEF,2),99999);
        recipe.addIngredient(new ItemStack(Material.COBBLESTONE,20));
        recipes.add(recipe);
        recipe = new MerchantRecipe(new ItemStack(Material.COOKED_PORKCHOP,3),99999);
        recipe.addIngredient(new ItemStack(Material.NETHERRACK,50));
        recipes.add(recipe);
        VILLAGER.setRecipes(recipes);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onVillagerOpen(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager) {
            event.setCancelled(true);
            event.getPlayer().openMerchant(VILLAGER, true);
        }
    }
}
