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
    private final int resourceId;

    public UpdateChecker(JavaPlugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getVersion() {
        String cV = plugin.getDescription().getVersion();

        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://raw.githubusercontent.com/asdjajalol/Bounties/main/src/main/resources/plugin.yml").openConnection();
            con.connect();
            for (String s : new BufferedReader(new InputStreamReader(con.getInputStream())).lines().toList()) {
                if (s.contains("version:")) {
                    System.out.println(s.replace("version:", ""));
                    break;
                }
            }
            System.out.println(cV);
            con.disconnect();
        } catch (Exception ignored) {}
    }

    /*public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId).openStream();
                 Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (Exception ignored) {}
        });
    }*/
}
