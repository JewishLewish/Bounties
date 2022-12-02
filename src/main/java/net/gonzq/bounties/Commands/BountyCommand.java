package net.gonzq.bounties.Commands;

import net.gonzq.bounties.Enums.GuiType;
import net.gonzq.bounties.Enums.Materials;
import net.gonzq.bounties.Invs.Gui;
import net.gonzq.bounties.Main;
import net.gonzq.bounties.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BountyCommand implements CommandExecutor {
    private Main plugin = Main.plugin;

    public BountyCommand() {
        plugin.getCommand("bounties").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (args.length == 0) {
                sender.sendMessage(Utils.chat(plugin.prefix + "Commands help:"));
                sender.sendMessage(Utils.chat("&7- &c/bounties set <player> <amount>"));
                sender.sendMessage(Utils.chat("&7- &c/bounties remove <playerName>"));
                sender.sendMessage(Utils.chat("&7- &c/bounties open"));
                return true;
            }
        if (sender instanceof Player p) {
            if (args[0].equalsIgnoreCase("remove")) {
                if (args.length == 1) {
                    p.sendMessage(Utils.chat(plugin.prefix + "Usage: &c/bounties remove <playerName>"));
                    return true;
                }
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[1]);
                if (!Utils.getBounties().contains(t.getName())) {
                    p.sendMessage(Utils.chat(plugin.prefix + "&cThere is no bounty for this player"));
                    return true;
                }
                if (!plugin.bounty.getConfig().getString("bounties." + t.getName() + ".setted-by").equalsIgnoreCase(p.getName())) {
                    p.sendMessage(Utils.chat(plugin.prefix + "&cYou did not set this bounty."));
                    return true;
                }
                p.sendMessage(Utils.chat(plugin.prefix + "&aYou have removed the bounty for &c" + t.getName() + "."));
                int amount = plugin.bounty.getConfig().getInt("bounties." + t.getName() + ".bounty");
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
            }
            if (args[0].equalsIgnoreCase("set")) {
                if (args.length < 3) {
                    p.sendMessage(Utils.chat(plugin.prefix + "Usage: &c/bounties set <player> <amount>"));
                    return true;
                }
                if (Utils.getAmout(p, Materials.DIAMOND.getStack()) == 0) {
                    p.sendMessage(Utils.chat(plugin.prefix + "&cYou don't have diamonds."));
                    return true;
                }
                Player t = Bukkit.getPlayer(args[1]);
                if (t == null) {
                    p.sendMessage(Utils.chat(plugin.prefix + "&cThis player isn't online"));
                    return true;
                }
                int i;
                try {
                    i = Integer.parseInt(args[2]);
                }  catch (Exception e) {
                    return true;
                }
                int amount = Utils.getAmout(p, Materials.DIAMOND.getStack());
                if (i > amount) {
                    p.sendMessage(Utils.chat(plugin.prefix + "&cYou cannot put more diamonds than you have."));
                    return true;
                }
                    for (ItemStack item : p.getInventory().getContents()) {
                        if (item != null && item.getType() == Materials.DIAMOND.getType()) {
                            if (item.getAmount() > i) {
                                item.setAmount(item.getAmount() - i);
                            } else {
                                p.getInventory().remove(item);
                            }
                        }
                    }
                    Bukkit.broadcastMessage(Utils.chat(plugin.prefix + "&a" + p.getName() + " has created a bounty for &c" + t.getName() + " &aof &b35 diamond/s"));
                    plugin.bounty.getConfig().set("bounties." + t.getName() + ".bounty", i);
                    plugin.bounty.getConfig().set("bounties." + t.getName() + ".setted-by", p.getName());
                    plugin.bounty.saveConfig();
            }
            if (args[0].equalsIgnoreCase("open")) {
                new Gui(p, GuiType.BOUNTY).open();
                p.sendMessage(Utils.chat(plugin.prefix + "Opening gui..."));
            }
        }
        return false;
    }
}
