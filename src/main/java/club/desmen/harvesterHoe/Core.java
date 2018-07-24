package club.desmen.harvesterHoe;

import club.desmen.harvesterHoe.commands.ComHarvesterHoe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public class Core extends JavaPlugin
{

    private Reference reference;

    public Reference getReference()
    {
        return reference;
    }

    @Override
    public void onDisable()
    {

    }

    @Override
    public void onEnable()
    {
        reference = new Reference();

        Configuration config = getConfig();
        reference.setMaterial(Material.valueOf(config.getString("item.material")));
        reference.setName(chatColor(config.getString("item.name")));
        reference.setLore(config.getStringList("item.lore"));

        reference.setPermission(config.getString("permission"));

        getCommand("HarvesterHoe").setExecutor(new ComHarvesterHoe(this));
        getServer().getPluginManager().registerEvents(new Events(this), this);

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
    }

    public static String chatColor(String message) {
        return message = ChatColor.translateAlternateColorCodes('&', message);
    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
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
