package net.gonzq.bounties.Versions;

import net.gonzq.bounties.Enums.Materials;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class Version_1_8 extends Version{
    @Override
    public ItemStack createPlayerSkull(String name, UUID uid) {
        ItemStack item = Materials.PLAYER_HEAD.getStack();
        SkullMeta im = (SkullMeta) item.getItemMeta();
        im.setOwner(name);
        item.setItemMeta(im);
        return item;
    }

    @Override
    public void setSkullOwner(Skull skull, Player p) {
        skull.setOwner(p.getName());
    }
}
