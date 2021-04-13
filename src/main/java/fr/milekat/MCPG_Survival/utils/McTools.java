package fr.milekat.MCPG_Survival.utils;

import org.bukkit.Location;

public class McTools {
    /**
     * Check if loc is between 2 locations
     */
    public static boolean inArea(Location loc, Location max, Location min) {
        //  X
        if (loc.getX() > max.getX() || loc.getX() < min.getX()) return false;
        //  Y
        if (loc.getY() > max.getY() || loc.getY() < min.getY()) return false;
        //  Z
        return !(loc.getZ() > max.getZ() || loc.getZ() < min.getZ());
    }
}
