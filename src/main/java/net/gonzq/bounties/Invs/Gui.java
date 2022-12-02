package net.gonzq.bounties.Invs;

import net.gonzq.bounties.Enums.GuiType;
import net.gonzq.bounties.Enums.Materials;
import net.gonzq.bounties.Main;
import net.gonzq.bounties.Utils.ItemBuilder;
import net.gonzq.bounties.Utils.Utils;
import net.gonzq.bounties.Versions.Version;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.IntStream;

public class Gui {

    private Inventory inv;
    private Player p;
    List<String> list = Utils.getBounties();

    public Gui(Player p, GuiType type) {
        switch (type) {
            case BOUNTY -> {
                Main plugin = Main.plugin;
                Inventory inv = Bukkit.createInventory(new GuiHolder(type), 54, Utils.chat("&a&lBounties"));
                if (list != null && !list.isEmpty()) {
                    for (int i = 0; i < list.toArray().length; i++) {
                        String s = list.get(i);
                        OfflinePlayer player = Bukkit.getOfflinePlayer(s);
                        inv.addItem(new ItemBuilder(Version.get().createPlayerSkull(s, player.getUniqueId())).setDisplayName(Utils.chat("&c&l" + s))
                                .setLore(List.of(Utils.chat("&7Bounty:  &a" + plugin.bounty.getConfig().getInt("bounties." + s + ".bounty")),
                                        Utils.chat("&7Setted by: &c" + plugin.bounty.getConfig().getString("bounties." + s + ".setted-by")))).build());
                    }
                }
                inv.setItem(49, new ItemBuilder(Materials.ENCHANTED_BOOK.getStack())
                        .setDisplayName(Utils.chat("&a&lYour Bounties")).setLore(List.of(Utils.chat("&7Click to open."))).build());
                setItems(inv, new ItemBuilder(Materials.LIGHT_GRAY_STAINED_GLASS_PANE.getStack()).setDisplayName(" ")
                        .addItemFlag(ItemFlag.HIDE_ENCHANTS).build(), 45,46,47,48,50,51,52,53);
                this.inv = inv;
                this.p = p;
            }
            case YOUR_BOUNTIES -> {
                Main plugin = Main.plugin;
                Inventory inv = Bukkit.createInventory(new GuiHolder(type), 27, Utils.chat("&a&lYour Bounties"));
                if (list != null && !list.isEmpty()) {
                for (String s : list) {
                    if (plugin.bounty.getConfig().getString("bounties." + s + ".setted-by").equals(p.getName())) {
                        OfflinePlayer player = Bukkit.getOfflinePlayer(s);
                        inv.addItem(new ItemBuilder(Version.get().createPlayerSkull(s, player.getUniqueId()))
                                .setDisplayName(Utils.chat("&c&l" + s)).setLore(List.of("", Utils.chat("&7Bounty: &a" +
                                        plugin.bounty.getConfig().getInt("bounties." + s + ".bounty")))).build());
                    }
                }
                    inv.setItem(22, new ItemBuilder(Materials.BOOK.getStack()).setDisplayName(Utils.chat("&c&lBounties"))
                            .setLore(List.of(Utils.chat("&7Click to open."))).build());

                    setItems(inv, new ItemBuilder(Materials.LIGHT_GRAY_STAINED_GLASS_PANE.getStack()).setDisplayName(" ")
                            .addItemFlag(ItemFlag.HIDE_ENCHANTS).build(),18,19,20,21,23,24,25,26);

                    this.inv = inv;
                    this.p = p;
                }
            }
        }
    }

    private void setItems(Inventory inv, ItemStack item, int... slots) {
        for (int i : slots) {
            inv.setItem(i, item);
        }
    }

    public void open() {
        p.openInventory(inv);
    }
    public Inventory getInv() {
        return inv;
    }
}
