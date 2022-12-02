package net.gonzq.bounties.Listeners;

import net.gonzq.bounties.Enums.GuiType;
import net.gonzq.bounties.Enums.Materials;
import net.gonzq.bounties.Invs.Gui;
import net.gonzq.bounties.Invs.GuiHolder;
import net.gonzq.bounties.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryEvent implements Listener {
    private Main plugin = Main.plugin;

    public InventoryEvent() {
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        ItemStack item = e.getCurrentItem();
        if (item == null) return;
        if (inv.getHolder() instanceof GuiHolder holder) {
            e.setCancelled(true);
            if (holder.getType() == GuiType.BOUNTY) {
                if (item.getType() == Materials.ENCHANTED_BOOK.getType()) {
                    new Gui(p, GuiType.YOUR_BOUNTIES).open();
                }
            } else if (holder.getType() == GuiType.YOUR_BOUNTIES) {
                if (item.getType() == Materials.BOOK.getType()) {
                    new Gui(p, GuiType.BOUNTY).open();
                }
            }
        }
    }
}
