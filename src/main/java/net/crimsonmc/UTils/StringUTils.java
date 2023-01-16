package net.crimsonmc.UTils;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StringUTils {

    /**
     * Translates ChatColor to vanilla Color Codes
     * @param string string
     * @return String
     */
    public static String format(String string) {
        return ChatColor.translateAlternateColorCodes('§', string);
    }

    /**
     * Translates ChatColor to vanilla Color Codes
     * @param strings strings
     * @return Array of formatted Strings
     */
    public static String[] format(String[] strings) {
        return format(Arrays.asList(strings)).toArray(new String[strings.length]);
    }

    /**
     * Translates ChatColor to vanilla Color Codes
     * @param strings strings
     * @return List of formatted Strings
     */
    public static List<String> format(List<String> strings) {
        List<String> collection = new ArrayList<>();
        for (String string : strings) {
            collection.add(format(string));
        }
        return collection;
    }

    /**
     * Links a list of Strings into one separated by a comma
     * @param strings strings
     * @return String
     */
    public static String link(List<String> strings) {
        return link(strings, ", ") ;
    }

    /**
     * Links a list of Strings into one separated by specified String
     * @param strings strings
     * @param separator separator
     * @return String
     */
    public static String link(List<String> strings, String separator) {
        String newString = "";
        for (String string : strings) {
            newString += string + separator;
        }
        newString = newString.substring(0, newString.length() - 2);
        return newString;
    }

    /**
     * Prefixes all Strings in a list of Strings with a String
     * @param strings strings
     * @param prefix prefix
     * @return List of Strings
     */
    public static List<String> prefix(List<String> strings, String prefix) {
        List<String> newStrings = new ArrayList<>();
        for (String string : strings) {
            newStrings.add(prefix + string);
        }
        return newStrings;
    }

    /**
     * Suffixes all Strings in a list of Strings with a String
     * @param strings strings
     * @param suffix suffix
     * @return List of Strings
     */
    public static List<String> suffix(List<String> strings, String suffix) {
        List<String> newStrings = new ArrayList<>();
        for (String string : strings) {
            newStrings.add(string + suffix);
        }
        return newStrings;
    }

    /**
     * Rainbowlizes a String
     * def -> add a color code between every character
     * @param string string
     * @return String
     */
    public static String rainbowlize(String string) {
        int lastColor = 0;
        int currColor;
        String newString = "";
        String colors = "123456789abcde";
        for (int i = 0; i < string.length(); i++) {
            do {
                currColor = new Random().nextInt(colors.length() - 1) + 1;
            }
            while (currColor == lastColor);
            newString += ChatColor.RESET.toString() + ChatColor.getByChar(colors.charAt(currColor)) + "" + string.charAt(i);
        }
        return newString;
    }

    /**
     * Rainbowlizes a String with given color codes
     * def -> add a color code between every character
     * @param string string
     * @param codes codes
     * @return String
     */
    public static String rainbowlize(String string, String codes) {
        int lastColor = 0;
        int currColor;
        String newString = "";
        String colors = codes;
        for (int i = 0; i < string.length(); i++) {
            do {
                currColor = new Random().nextInt(colors.length() - 1) + 1;
            }
            while (currColor == lastColor);
            newString += ChatColor.RESET.toString() + ChatColor.getByChar(colors.charAt(currColor)) + "" + string.charAt(i);
        }
        return newString;
    }

    /**
     * Rainbowlizes a List of Strings
     * @param strings strings
     * @return List of Strings
     */
    public static List<String> rainbowlize(List<String> strings) {
        List<String> newStrings = new ArrayList<>();
        for (String string : strings) {
            newStrings.add(rainbowlize(string));
        }
        return newStrings;
    }

    /**
     * Rainbowlizes Strings
     * @param strings strings
     * @return List of Strings
     */
    public static List<String> rainbowlize(String... strings) {
        List<String> newStrings = new ArrayList<>();
        for (String string : strings) {
            newStrings.add(rainbowlize(string));
        }
        return newStrings;
    }

    /**
     * Rainbowlizes a List of Strings with specified codes
     * @param strings strings
     * @param codes codes
     * @return List of Strings
     */
    public static List<String> rainbowlize(List<String> strings, String codes) {
        List<String> newStrings = new ArrayList<>();
        for (String string : strings) {
            newStrings.add(rainbowlize(string, codes));
        }
        return newStrings;
    }

    /**
     * Rainbowlizes Strings with specified codes
     * @param strings strings
     * @return List of Strings
     */
    public static List<String> rainbowlize(String codes, String... strings) {
        List<String> newStrings = new ArrayList<>();
        for (String string : strings) {
            newStrings.add(rainbowlize(string, codes));
        }
        return newStrings;
    }

    /**
     * Repeats a String by the specified amount
     * @param string string
     * @param count count
     * @return String
     */
    public static String repeat(String string, int count) {
        if (count == 0) return "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(string);
        }
        return builder.toString();
    }


    /**
     * Strips all ChatColors from a String
     * @param string string
     * @return
     */
    public static String stripColor(String string) {
        return ChatColor.stripColor(string);
    }

    /**
     * Strips all ChatColors from a List of Strings
     * @param strings strings
     * @return
     */
    public static List<String> stripColor(List<String> strings) {
        List<String> newStrings = new ArrayList<>();
        for (String string : strings) {
            newStrings.add(stripColor(string));
        }
        return newStrings;
    }

    /**
     * Strips all ChatColors from Strings
     * @param strings strings
     * @return
     */
    public static List<String> stripColor(String... strings) {
        List<String> newStrings = new ArrayList<>();
        for (String string : strings) {
            newStrings.add(stripColor(string));
        }
        return newStrings;
    }


    /**
     * Formats Seconds in MM:SS format
     * @param seconds seconds
     * @return String
     */
    public static String toMMSS(int seconds) {
        int rem = seconds % 3600;
        int mn = rem / 60;
        int sec = rem % 60;
        return (mn < 10 ? "0" : "") + mn + ":" + (sec < 10 ? "0" : "") + sec;
    }

    /**
     * Formats Seconds in HH:MM:SS Format
     * @param timeInSeconds time in seconds
     * @return String
     */
    public static String toHHMMSS (int timeInSeconds){
        int hours = timeInSeconds / 3600;
        int secondsLeft = timeInSeconds - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;
        String formattedTime = "";

        if (hours < 10)
            formattedTime += "0";
        formattedTime += hours + ":";

        if (minutes < 10)
            formattedTime += "0";
        formattedTime += minutes + ":";

        if (seconds < 10)
            formattedTime += "0";
        formattedTime += seconds;

        return formattedTime;
    }

    /**
     * Formats a ISO8601-formatted String in a Human-Readable Format with a Specified Separator
     * @param isoFormat isoformat string
     * @param separator separator
     * @return String
     */
    public static String fromISO8601(String isoFormat, String separator) {
        String[] list = isoFormat.split("-");
        return list[2] + separator + list[1] + separator + list[0];
    }

}