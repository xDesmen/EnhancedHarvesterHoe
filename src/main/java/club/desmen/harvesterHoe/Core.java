package club.desmen.harvesterHoe;

import club.desmen.harvesterHoe.commands.HarvesterHoe;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;


public class Core extends JavaPlugin {

    private Reference reference;
    private static Economy econ = null;
    private static final Logger log = Logger.getLogger("Minecraft");

    public Reference getReference() {
        return reference;
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        reference = new Reference();

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
            createConfig();
            saveDefaultConfig();
        }
        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Configuration config = getConfig();
        reference.setMaterial(Material.valueOf(config.getString("item.material")));
        reference.setName(chatColor(config.getString("item.name")));
        reference.setLore(config.getStringList("item.lore"));
        reference.setPrice(config.getInt("sugarcane-price"));

        reference.setPermission(config.getString("permission"));

        getCommand("HarvesterHoe").setExecutor(new HarvesterHoe(this));
        getServer().getPluginManager().registerEvents(new Events(this), this);

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Economy getEcon() {
        return econ;
    }

    public static String chatColor(String message) {
        return message = ChatColor.translateAlternateColorCodes('&', message);
    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
                saveDefaultConfig();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
