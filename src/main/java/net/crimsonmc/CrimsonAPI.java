package net.crimsonmc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CrimsonAPI extends JavaPlugin {

    private static CrimsonAPI instance;

    private static final String VERSION = "0.0.1";

    /**
     * onEnable
     */
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("[CrimsonAPI] API loaded");
    }

    /**
     * onDisable
     */
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[CrimsonAPI] API unloaded");
    }

    /**
     * returns the Main Class instance
     * @return the instance of the main class
     */
    public static CrimsonAPI getInstance() {
        return instance;
    }

    /**
     * returns the API Version
     * @return the API Version
     */
    public static String VERSION() {
        return VERSION;
    }

}
