package net.gonzq.bounties.Versions;

import net.gonzq.bounties.Main;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public abstract class Version {

    private static Version v = null;

    public static Version get() {
        if (v == null) {
            int i = Main.plugin.getVersion();
            if (i < 12) {
                v = new Version_1_8();
            } else if (i == 12) {
                v = new Version_1_12();
            } else {
                v = new Version_1_13();
            }
        }
        return v;
    }

    public abstract ItemStack createPlayerSkull(String name, UUID uid);
    public abstract void setSkullOwner(Skull skull, Player p);
}
