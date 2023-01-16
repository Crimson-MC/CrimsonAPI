package net.crimsonmc.UTils;

import net.crimsonmc.CrimsonAPI;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WorldUTils {

    /**
     * deletes specified world
     * @param name
     * @param file - The Directory of the World
     * @return true at Success, false at failure
     */
    public static boolean deleteWord(String name, File file) {
        try {
            Bukkit.getServer().unloadWorld(name, false);
            FileUtils.deleteDirectory(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes specified Worlds
     * @param worlds - Map of Name and Directory
     * @return true at Success, false at failure
     */
    public static boolean deleteWorlds(Map<String, File> worlds) {
        try {
            for (Map.Entry<String, File> set : worlds.entrySet()) {
                deleteWord(set.getKey(), set.getValue());
            }
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    /**
     * Deletes Minecraft Standard Worlds
     * @return true at Success, false at failure
     */
    public static boolean deleteStandardWorlds() {
        try {
            Map<String, File> worlds = new HashMap<>();

            worlds.put("world", new File("world"));
            worlds.put("world_nether", new File("world_nether"));
            worlds.put("world_the_end", new File("world_the_ent"));

            deleteWorlds(worlds);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Backups world to Folder
     * Currently there is no way to automatically Copy the world back
     * @param folder folder
     * @param worldName worldName
     * @return
     */
    public static boolean saveTo(String folder, String worldName) {
        File worldsFolder = Bukkit.getServer().getWorldContainer();
        String[] wfList = worldsFolder.list();
        if (wfList == null || !Arrays.asList(wfList).contains(worldName)) return false;
        World world = Bukkit.getServer().getWorld(worldName);
        if (world == null) return false;
        else world.save();
        return copyDirectory(new File(worldsFolder, worldName), new File(folder));
    }


    /**
     * Recursive copy utility for Directorys
     * @param path path
     * @param target target
     * @return true at Success, false at failure
     */
    private static boolean copyDirectory(File path, File target) {
        boolean success = true;
        target.mkdir();
        String[] entries = path.list();
        if (entries == null) return false;
        File currentFile;
        FileInputStream inputStream;
        FileChannel inChannel;
        FileOutputStream outputStream;
        FileChannel outChannel;
        for (String s : entries) {
            if (s.equals("uid.dat")) continue; // This identifier will prevent us from creating duplicate worlds.
            currentFile = new File(path.getPath(), s);
            if (currentFile.isDirectory()) {
                File newDirectory = new File(target, s);
                newDirectory.mkdir();
                copyDirectory(currentFile, newDirectory);
            } else {
                try {
                    // Apparently this more long-winded method is faster.
                    inputStream = new FileInputStream(currentFile);
                    inChannel = inputStream.getChannel();
                    outputStream = new FileOutputStream(new File(target, s));
                    outChannel = outputStream.getChannel();
                    inChannel.transferTo(0, currentFile.length(), outChannel);
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    if (success) {
                        // We probably only want to print this message once to avoid spamming
                        e.printStackTrace();
                        success = false;
                    }
                }
            }
        }
        return success;
    }

}
