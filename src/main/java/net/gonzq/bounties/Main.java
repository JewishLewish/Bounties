package net.gonzq.bounties;

import net.gonzq.bounties.Commands.BountyCommand;
import net.gonzq.bounties.Files.BountyFile;
import net.gonzq.bounties.Listeners.InventoryEvent;
import net.gonzq.bounties.Listeners.KillEvent;
import net.gonzq.bounties.Utils.UpdateChecker;
import net.gonzq.bounties.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    public static Main plugin;
    private int version;

    public BountyFile bounty;
    public String prefix = Utils.chat("&c&lBounties&8» &7");
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        setupVersion();

        bounty = new BountyFile(this);

        new BountyCommand();
        new KillEvent();
        new InventoryEvent();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void setupVersion() {
        String versionString = Bukkit.getVersion();
        version = 0;


        getLogger().info(Bukkit.getBukkitVersion() + " Server detected!");
        UpdateChecker c = new UpdateChecker(this);
        int currentVer = Integer.parseInt(getDescription().getVersion().replace(".",""));
        int newVer = Integer.parseInt(c.getVersion().replace(".",""));
        List<String> msg = new ArrayList<>();
        msg.add("=============== Bounties ===============");
        msg.add("- Plugin made by: Gonzq#4451");
        msg.add("- Current Version: " + getDescription().getVersion());
        if (currentVer < newVer) {
            msg.add("- There is a new version available: " + c.getVersion());
            msg.add("- Download Link: https://www.spigotmc.org/resources/bounties-1-8-1-19.106551/");
        }
        msg.add("=============== Bounties ===============");
        msg.forEach(s -> getLogger().info(s));

    }

    public int getVersion() {
        return version;
    }

}
