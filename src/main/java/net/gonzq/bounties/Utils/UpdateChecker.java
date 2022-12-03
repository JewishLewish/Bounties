package net.gonzq.bounties.Utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class UpdateChecker {

    private final JavaPlugin plugin;

    public UpdateChecker(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public String getVersion() {
        final String[] v = {"0.0"};
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                HttpURLConnection con = (HttpURLConnection) new URL("https://raw.githubusercontent.com/asdjajalol/Bounties/master/src/main/resources/plugin.yml").openConnection();
                con.connect();
                for (String s : new BufferedReader(new InputStreamReader(con.getInputStream())).lines().toList()) {
                    if (s.contains("version:")) {
                        v[0] = s.replace("version:", "");
                        break;
                    }
                }
                con.disconnect();
            } catch (Exception ignored) {}
        });
        return v[0];
    }
}
