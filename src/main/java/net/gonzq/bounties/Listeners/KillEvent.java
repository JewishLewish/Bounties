package net.gonzq.bounties.Listeners;

import net.gonzq.bounties.Enums.Materials;
import net.gonzq.bounties.Main;
import net.gonzq.bounties.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class KillEvent implements Listener {
    private Main plugin = Main.plugin;

    public KillEvent() {
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player k = p.getKiller();
        if (k == null) return;
        if (k == p) return;
        if (Utils.getBounties().contains(p.getName())) {
            k.sendMessage(Utils.chat(plugin.prefix + "&a¡You got the bounty of &c" + p.getName() + "&a!. &b(" +
                    plugin.bounty.getConfig().getInt("bounties." + p.getName() + ".bounty") + " diamonds)."));

            int amount = plugin.bounty.getConfig().getInt("bounties." + p.getName() + ".bounty");
            int added = 0;
            for (ItemStack item : p.getInventory().getContents()) {
                if (item == null) {
                    if (amount > 64) {
                        p.getInventory().addItem(Materials.DIAMOND.getStack(64));
                        amount -= 64;
                        added += 64;
                    } else {
                        p.getInventory().addItem(Materials.DIAMOND.getStack(amount));
                        added += amount;
                    }
                }
            }
            if (amount <= 0) {
                p.sendMessage(Utils.chat(plugin.prefix + "&a" + amount + " diamonds have been added to your inventory"));
            } else {
                p.sendMessage(Utils.chat(plugin.prefix + "&a" + added + " diamonds have been added to your inventory. &c¡But " + amount + " were left on the ground!."));
            }

            plugin.bounty.getConfig().set("bounties." + p.getName(), null);
            plugin.bounty.saveConfig();
        }
    }
}
