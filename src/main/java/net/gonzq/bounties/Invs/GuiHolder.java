package net.gonzq.bounties.Invs;

import net.gonzq.bounties.Enums.GuiType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class GuiHolder implements InventoryHolder {
    private GuiType type;

    public GuiHolder(GuiType type) {
        this.type = type;
    }

    public GuiType getType() {
        return type;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
