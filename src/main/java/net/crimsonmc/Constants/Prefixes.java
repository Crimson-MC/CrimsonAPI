package net.crimsonmc.Constants;


    /**
    * Prefixes used by all Plugins
    * There should be no reason to not use these Prefixes
    * @author max
    */

public class Prefixes {

    /**
     * Main Prefix
     * @return String
     */
    public static String MAIN() {return "§4CrimsonMC §0» §r";}

    /**
     * Error Prefix
     * @return String
     */
    public static String ERROR(String name) {return "§4CrimsonMC §4ERROR §0» §r";}

    /**
     * Warning Prefix
     * @return String
     */
    public static String WARNING(String name) {return "§4CrimsonMC §eWARNING §0» §r";}

    /**
     * Debug Prefix
     * Should never be seen by any user
     * @return String
     */
    public static String DEBUG(String name) {return "§4CrimsonMC §rDEBUG §0» §r";}
}
