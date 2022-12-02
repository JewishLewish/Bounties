package net.gonzq.bounties.Enums;

import net.gonzq.bounties.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Materials {
    PLAYER_HEAD("SKULL_ITEM","PLAYER_HEAD", (short) 3),
    ENCHANTED_BOOK,
    LIGHT_GRAY_STAINED_GLASS_PANE("STAINED_GLASS_PANE", "LIGHT_GRAY_STAINED_GLASS_PANE", (short) 8),
    BOOK,
    DIAMOND;

    private final String name8, name13;
    private final short id8;

    private Material material;

    Materials(String name8, String name13, short id8) {
        this.name8 = name8;
        this.name13 = name13;
        this.id8 = id8;
    }
    Materials(String name8, String name13) {
        this.name8 = name8;
        this.name13 = name13;
        id8 = 0;
    }
    Materials() {
        this.name8 = name();
        this.name13 = name();
        id8 = 0;
    }
    public Material getType() {
        if (material == null) {
            try {
                material = Material.valueOf(name13);
            }catch (IllegalArgumentException ex) {
                try {
                    material = Material.valueOf(name8);
                }catch (IllegalArgumentException ex2) {
                    // 1.9+ item on 1.8 server.
                }
            }
        }

        return material;
    }

    public short getData(){
        return Main.plugin.getVersion() < 13 ? id8 : 0;
    }

    @SuppressWarnings("deprecation")
    public ItemStack getStack(int amount) {
        return new ItemStack(getType(), amount, getData());
    }

    public ItemStack getStack(){
        return getStack(1);
    }
}
