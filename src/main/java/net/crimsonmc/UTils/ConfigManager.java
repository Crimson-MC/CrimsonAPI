package net.crimsonmc.UTils;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Config Manager
 * @author max
 */
public class ConfigManager {

    private final FileConfiguration config;

    private final File file;

    /**
     * Create a configuration object,
     * with the yaml configuration file.
     * @param plugin The plugin for the configuration.
     * @param path The path to the configuration.
     */
    public ConfigManager(JavaPlugin plugin, String path) {
        file = new File(plugin.getDataFolder() + File.separator + path);
        config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Create file
     * @return boolean
     * @throws IOException execption
     */
    public boolean createFile() throws IOException {
        return file.createNewFile();
    }

    /**
     * get Object
     * @param path - path
     * @return Object
     */
    public Object get(String path) {
        return config.get(path);
    }

    /**
     * set object at path
     * @param path - path
     * @param o - object
     * @param save - save
     */
    public void set(String path, Object o, boolean save) {
        try {
            config.set(path, o);
            if(save) config.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * save file
     * @throws IOException exeption
     */
    public void save() throws IOException {
        config.save(file);
    }

    /**
     * check if file exists
     * @param path - path
     * @return true if yes
     */
    public boolean exists(String path) {
        if (config.contains(path)) return true; else return false;
    }

    /**
     * get String from location
     * @param path - path
     * @return String
     */
    public String getString(String path) {
        return (String) get(path);
    }

    /**
     * get boolean from location
     * @param path - path
     * @return boolean
     */
    public boolean getBoolean(String path) {
        return (boolean) get(path);
    }

    /**
     * get char from location
     * @param path - path
     * @return char
     */
    public char getChar(String path) {
        return (char) get(path);
    }

    /**
     * get byte from location
     * @param path - path
     * @return byte
     */
    public byte getByte(String path) {
        return (byte) get(path);
    }

    /**
     * get SHort from Location
     * @param path - path
     * @return short
     */
    public short getShort(String path) {
        return (short) get(path);
    }

    /**
     * get int from Location
     * @param path - path
     * @return int
     */
    public int getInt(String path) {
        return (int) get(path);
    }

    /**
     * get Long from location
     * @param path - path
     * @return long
     */
    public long getLong(String path) {
        return (long) get(path);
    }

    /**
     * get Float from Location
     * @param path - path
     * @return float
     */
    public float getFloat(String path) {
        return (float) get(path);
    }

    /**
     * get Double from Location
     * @param path - path
     * @return Double
     */
    public double getDouble(String path) {
        return (double) get(path);
    }

    /**
     * get List of String from Location
     * @param path - path
     * @return List
     */
    public List<String> getStringList(String path){
        return (List<String>) get(path);
    }

    /**
     * get List of Boolean from location
     * @param path - path
     * @return List
     */
    public List<Boolean> getBooleanList(String path) {
        return (List<Boolean>) get(path);
    }

    /**
     * get List of Characters from location
     * @param path - path
     * @return List
     */
    public List<Character> getCharList(String path) {
        return (List<Character>) get(path);
    }

    /**
     * get List of Byte from location
     * @param path - path
     * @return List
     */
    public List<Byte> getByteList(String path) {
        return (List<Byte>) get(path);
    }

    /**
     * get List of Shorts from location
     * @param path - path
     * @return List
     */
    public List<Short> getShortList(String path) {
        return (List<Short>) get(path);
    }

    /**
     * get List of Integers from location
     * @param path - path
     * @return List
     */
    public List<Integer> getIntList(String path) {
        return (List<Integer>) get(path);
    }

    /**
     * get List of Longs from location
     * @param path - path
     * @return List
     */
    public List<Long> getLongList(String path) {
        return (List<Long>) get(path);
    }

    /**
     * get List of Floats from Location
     * @param path - path
     * @return List
     */
    public List<Float> getFloatList(String path) {
        return (List<Float>) get(path);
    }

    /**
     * get List of Doubles from location
     * @param path - path
     * @return List
     */
    public List<Double> getDoubleList(String path) {
        return (List<Double>) get(path);
    }

    /**
     * get Array  of Strings from Location
     * @param path - path
     * @return String[]
     */
    public String[] getStringArray(String path) {
        return (String[]) get(path);
    }

    /**
     * get Array of booleans from location
     * @param path - path
     * @return boolean[]
     */
    public boolean[] getBooleanArray(String path) {
        return (boolean[]) get(path);
    }

    /**
     * get Array of Chars from location
     * @param path - path
     * @return char[]
     */
    public char[] getCharArray(String path) {
        return (char[]) get(path);
    }

    /**
     * get Array of Bytes from Loaction
     * @param path - path
     * @return byte[]
     */
    public byte[] getByteArray(String path) {
        return (byte[]) get(path);
    }

    /**
     * get Array of Shorts from location
     * @param path - path
     * @return short[]
     */
    public short[] getShortArray(String path) {
        return (short[]) get(path);
    }

    /**
     * get Array of ints form location
     * @param path - path
     * @return int[]
     */
    public int[] getIntArray(String path) {
        return (int[]) get(path);
    }

    /**
     * get Array of longs from Location
     * @param path - path
     * @return long[]
     */
    public long[] getLongArray(String path) {
        return (long[]) get(path);
    }

    /**
     * get Array of floats from location
     * @param path - path
     * @return float[]
     */
    public float[] getFloatArray(String path) {
        return (float[]) get(path);
    }

    /**
     * get Array of Doubles from location
     * @param path - path
     * @return double[]
     */
    public double[] getDoubleArray(String path) {
        return (double[]) get(path);
    }

    /**
     * get Location from location
     * @param path - path
     * @return Location
     */
    public Location getLocation(String path) {
        return (Location) get(path);
    }

    /**
     * get Inventory from Location
     * @param path - path
     * @return Inventory
     */
    public Inventory getInventory(String path) {
        return (Inventory) get(path);
    }

    /**
     * get ItemStack from location
     * @param path - path
     * @return ItemStack
     */
    public ItemStack getItemStack(String path) {
        return (ItemStack) get(path);
    }

    /**
     * get List of Locations from location
     * @param path - path
     * @return Location
     */
    public List<Location> getLocationList(String path) {
        return (List<Location>) get(path);
    }

    /**
     * get List of Inventorys from Location
     * @param path - path
     * @return Inventory
     */
    public List<Inventory> getInventoryList(String path) {
        return (List<Inventory>) get(path);
    }

    /**
     * get List of ItemStacks from location
     * @param path - path
     * @return ItemStack
     */
    public List<ItemStack> getItemStackList(String path) {
        return (List<ItemStack>) get(path);
    }

    /**
     * get Location Array from location
     * @param path - path
     * @return Location
     */
    public Location[] getLocationArray(String path) {
        return (Location[]) get(path);
    }

    /**
     * get Inventory Array from Location
     * @param path - path
     * @return Inventory
     */
    public Inventory[] getInventoryArray(String path) {
        return (Inventory[]) get(path);
    }

    /**
     * get ItemStack Array from location
     * @param path - path
     * @return ItemStack
     */
    public ItemStack[] getItemStackArray(String path) {
        return (ItemStack[]) get(path);
    }
}
