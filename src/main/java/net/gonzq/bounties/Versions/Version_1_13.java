package net.gonzq.bounties.Versions;

import net.gonzq.bounties.Enums.Materials;
import org.bukkit.Bukkit;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class Version_1_13 extends Version{
    @Override
    public ItemStack createPlayerSkull(String name, UUID uid) {
        ItemStack item = Materials.PLAYER_HEAD.getStack();
        SkullMeta im = (SkullMeta) item.getItemMeta();
        im.setOwningPlayer(Bukkit.getOfflinePlayer(uid));
        item.setItemMeta(im);
        return item;
    }

    @Override
    public void setSkullOwner(Skull skull, Player p) {
        skull.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
    }
}
