package net.gonzq.bounties.Utils;

import net.gonzq.bounties.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> getBounties() {
        ConfigurationSection s = Main.plugin.getConfig().getConfigurationSection("bounties");
        return s != null ? new ArrayList<>(s.getKeys(false)) : new ArrayList<>();
    }
    public static int getAmout(Player p, ItemStack item) {
        if (p == null) return 0;
        int amount = 0;
        for (int i = 0; i < 36; i++) {
            ItemStack slot = p.getInventory().getItem(i);
            if (slot == null || !slot.isSimilar(item)) continue;
            amount += slot.getAmount();
        }
        return amount;
    }
}
