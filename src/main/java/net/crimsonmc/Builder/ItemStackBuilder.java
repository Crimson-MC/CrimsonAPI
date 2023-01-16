package net.crimsonmc.Builder;

import com.google.gson.Gson;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
    /**
     * ItemStackBuilder
     * @author max
     */
public class ItemStackBuilder {
    /*
    TODO: Add descriptive variable names
    TODO: organise into correlative blocks
     */

        private ItemStack item;
        private ItemMeta meta;
        private Material material = Material.STONE;
        private int amount = 1;
        private MaterialData data;
        private short damage = 0;
        private Map<Enchantment, Integer> enchantments = new HashMap<>();
        private String displayname;
        private List<String> lore = new ArrayList<>();
        private List<ItemFlag> flags = new ArrayList<>();

        private int customModelData;


        private boolean andSymbol = true;
        private boolean unsafeStackSize = false;

        /**
         * Constructs a new ItemStackBuilder with the specified material.
         *
         * If the material is null, it will default to Material.AIR.
         *
         * @param material the material for the ItemStack
         */
        public ItemStackBuilder(Material material) {
            if(material == null) material = Material.AIR;
            this.item = new ItemStack(material);
            this.material = material;
        }

        /**
         * Constructs a new ItemStackBuilder with the specified material and amount.
         *
         * If the material is null, it will default to Material.AIR. If the amount is greater
         * than the material's maximum stack size or less than or equal to zero, the amount
         * will default to 1 unless `unsafeStackSize` is set to true.
         *
         * @param material the material for the ItemStack
         * @param amount the amount of the material for the ItemStack
         */
        public ItemStackBuilder(Material material, int amount) {
            if(material == null) material = Material.AIR;
            if(((amount > material.getMaxStackSize()) || (amount <= 0)) && (!unsafeStackSize)) amount = 1;
            this.amount = amount;
            this.item = new ItemStack(material, amount);
            this.material = material;
        }

        /**
         * Constructs a new ItemStackBuilder with the specified material, amount, and display name.
         *
         * If the material is null, it will default to Material.AIR. If the amount is greater
         * than the material's maximum stack size or less than or equal to zero, the amount
         * will default to 1 unless `unsafeStackSize` is set to true. The display name must
         * not be null.
         *
         * @param material the material for the ItemStack
         * @param amount the amount of the material for the ItemStack
         * @param displayname the display name for the ItemStack
         * @throws NullPointerException if the display name is null
         */
        public ItemStackBuilder(Material material, int amount, String displayname) {
            if(material == null) material = Material.AIR;
            Validate.notNull(displayname, "The Displayname is null.");
            this.item = new ItemStack(material, amount);
            this.material = material;
            if(((amount > material.getMaxStackSize()) || (amount <= 0)) && (!unsafeStackSize)) amount = 1;
            this.amount = amount;
            this.displayname = displayname;
        }

        /**
         * Constructs a new ItemStackBuilder with the specified material and display name.
         *
         * If the material is null, it will default to Material.AIR. The display name must
         * not be null.
         *
         * @param material the material for the ItemStack
         * @param displayname the display name for the ItemStack
         * @throws NullPointerException if the display name is null
         */
        public ItemStackBuilder(Material material, String displayname) {
            if(material == null) material = Material.AIR;
            Validate.notNull(displayname, "The Displayname is null.");
            this.item = new ItemStack(material);
            this.material = material;
            this.displayname = displayname;
        }

        /**
         * Constructs a new ItemStackBuilder with the specified ItemStack.
         *
         * The ItemStack must not be null. The new ItemStackBuilder will have the same
         * material, amount, data, damage, enchantments, display name, lore, and flags
         * as the original ItemStack.
         *
         * @param item the ItemStack to create a new ItemStackBuilder from
         * @throws NullPointerException if the ItemStack is null
         */
        public ItemStackBuilder(ItemStack item) {
            Validate.notNull(item, "The Item is null.");
            this.item = item;
            if(item.hasItemMeta())
                this.meta = item.getItemMeta();
            this.material = item.getType();
            this.amount = item.getAmount();
            this.data = item.getData();
            this.damage = item.getDurability();
            this.enchantments = item.getEnchantments();
            if(item.hasItemMeta())
                this.displayname = item.getItemMeta().getDisplayName();
            if(item.hasItemMeta())
                this.lore = item.getItemMeta().getLore();
            if(item.hasItemMeta())
                for (ItemFlag f : item.getItemMeta().getItemFlags()) {
                    flags.add(f);
                }
            if (item.hasItemMeta()) {
                this.customModelData = item.getItemMeta().getCustomModelData();
            }
        }

        /**
         * Constructs a new ItemStackBuilder with the ItemStack from the specified path in the
         * given FileConfiguration.
         *
         * The FileConfiguration must not be null. If the path is invalid or the ItemStack
         * at the specified path is null, the ItemStackBuilder will default to a new
         * ItemStack with Material.AIR.
         *
         * @param cfg the FileConfiguration to retrieve the ItemStack from
         * @param path the path in the FileConfiguration to the ItemStack
         * @throws NullPointerException if the FileConfiguration is null
         */
        public ItemStackBuilder(FileConfiguration cfg, String path) {
            this(cfg.getItemStack(path));
        }

        /**
         * Constructs a new ItemStackBuilder with the properties of the specified ItemStackBuilder.
         *
         * The ItemStackBuilder must not be null.
         *
         * @param builder the ItemStackBuilder to copy the properties from
         * @throws NullPointerException if the ItemStackBuilder is null
         * @deprecated This constructor is deprecated and will be removed in a future version.
         *             Use the copy constructor instead.
         */
        @Deprecated
        public ItemStackBuilder(ItemStackBuilder builder) {
            Validate.notNull(builder, "The ItemBuilder is null.");
            this.item = builder.item;
            this.meta = builder.meta;
            this.material = builder.material;
            this.amount = builder.amount;
            this.damage = builder.damage;
            this.data = builder.data;
            this.damage = builder.damage;
            this.enchantments = builder.enchantments;
            this.displayname = builder.displayname;
            this.lore = builder.lore;
            this.flags = builder.flags;
            this.customModelData = builder.customModelData;
        }

        /**
         * Sets the amount of the material for the ItemStack.
         *
         * If the amount is greater than the material's maximum stack size or less than or
         * equal to zero, the amount will default to 1 unless `unsafeStackSize` is set to true.
         *
         * @param amount the amount of the material for the ItemStack
         * @return the current ItemStackBuilder
         */
        public ItemStackBuilder amount(int amount) {
            if(((amount > material.getMaxStackSize()) || (amount <= 0)) && (!unsafeStackSize)) amount = 1;
            this.amount = amount;
            return this;
        }

        /**
         * Sets the data for the ItemStack.
         *
         * The data must not be null.
         *
         * @param data the data for the ItemStack
         * @return the current ItemStackBuilder
         * @throws NullPointerException if the data is null
         */
        public ItemStackBuilder data(MaterialData data) {
            Validate.notNull(data, "The Data is null.");
            this.data = data;
            return this;
        }

        /**
         * Sets the damage (durability) for the ItemStack.
         *
         * @param damage the damage for the ItemStack
         * @return the current ItemStackBuilder
         * @deprecated This method is deprecated and will be removed in a future version.
         *             Use {@link #durability(short)} instead.
         */
        @Deprecated
        public ItemStackBuilder damage(short damage) {
            this.damage = damage;
            return this;
        }

        /**
         * Sets the durability (damage) for the ItemStack.
         *
         * @param damage the durability for the ItemStack
         * @return the current ItemStackBuilder
         */
        public ItemStackBuilder durability(short damage) {
            this.damage = damage;
            return this;
        }

        /**
         * Sets the material for the ItemStack.
         *
         * The material must not be null.
         *
         * @param material the material for the ItemStack
         * @return the current ItemStackBuilder
         * @throws NullPointerException if the material is null
         */
        public ItemStackBuilder material(Material material) {
            Validate.notNull(material, "The Material is null.");
            this.material = material;
            return this;
        }

        /**
         * Sets the meta data for the ItemStack.
         *
         * The meta data must not be null.
         *
         * @param meta the meta data for the ItemStack
         * @return the current ItemStackBuilder
         * @throws NullPointerException if the meta data is null
         */
        public ItemStackBuilder meta(ItemMeta meta) {
            Validate.notNull(meta, "The Meta is null.");
            this.meta = meta;
            return this;
        }

        /**
         * Adds an Enchantment to the ItemStack.
         *
         * @param enchant the Enchantment to add to the ItemStack
         * @param level the level of the Enchantment
         * @return the current ItemStackBuilder
         * @throws NullPointerException if the Enchantment is null
         */
        public ItemStackBuilder enchant(Enchantment enchant, int level) {
            Validate.notNull(enchant, "The Enchantment is null.");
            enchantments.put(enchant, level);
            return this;
        }

        /**
         * Sets the {@link org.bukkit.enchantments.Enchantment}s for the ItemStack.
         *
         * The enchantments must not be null.
         *
         * @param enchantments the enchantments for the ItemStack
         * @return the current ItemStackBuilder
         * @throws NullPointerException if the enchantments are null
         */
        public ItemStackBuilder enchant(Map<Enchantment, Integer> enchantments) {
            Validate.notNull(enchantments, "enchantments cant be null");
            this.enchantments = enchantments;
            return this;
        }

        /**
         * Sets the display name for the ItemStack.
         *
         * The display name must not be null. If `andSymbol` is set to true, the display name
         * will be formatted
         *
         * @param displayname the display name for the ItemStack
         * @return the current ItemStackBuilder
         * @throws NullPointerException if the display name is null
         */
        public ItemStackBuilder displayname(String displayname) {
            Validate.notNull(displayname, "The Displayname is null.");
            this.displayname = andSymbol ? ChatColor.translateAlternateColorCodes('&', displayname) : displayname;
            return this;
        }

        /**
         * Adds a line of lore to the ItemStack.
         *
         * The line of lore must not be null. If `andSymbol` is set to true, the line of lore
         * will be formatted.
         *
         * @param line the line of lore to add to the ItemStack
         * @return the current ItemStackBuilder
         * @throws NullPointerException if the line of lore is null
         */
        public ItemStackBuilder lore(String line) {
            Validate.notNull(line, "The Line is null.");
            lore.add(andSymbol ? ChatColor.translateAlternateColorCodes('&', line) : line);
            return this;
        }

        /**
         * Sets the lore for the ItemStack.
         *
         * The lore must not be null. If `andSymbol` is set to true, the lines of lore
         * will be formatted.
         *
         * @param lore the lore for the ItemStack
         * @return the current ItemStackBuilder
         * @throws NullPointerException if the lore is null
         */
        public ItemStackBuilder lore(List<String> lore) {
            Validate.notNull(lore, "The Lores are null.");
            this.lore = lore;
            return this;
        }

        /**
         * Adds one or more Lines to the Lore of the ItemStack
         * @param lines One or more Strings for the ItemStack Lore
         * @deprecated Use {@code ItemBuilder#lore}
         * @return the current ItemStackBuilder
         */
        @Deprecated
        public ItemStackBuilder lores(String... lines) {
            Validate.notNull(lines, "The Lines are null.");
            for (String line : lines) {
                lore(andSymbol ? ChatColor.translateAlternateColorCodes('&', line) : line);
            }
            return this;
        }

        /**
         * Adds lines of lore to the ItemStack.
         *
         * The lines of lore must not be null. If `andSymbol` is set to true, the lines of lore
         * will be formatted.
         *
         * @param lines the lines of lore to add to the ItemStack
         * @return the current ItemStackBuilder
         * @throws NullPointerException if the lines of lore are null
         * @deprecated use {@link #lore(String)} instead
         */
        public ItemStackBuilder lore(String... lines) {
            Validate.notNull(lines, "The Lines are null.");
            for (String line : lines) {
                lore(andSymbol ? ChatColor.translateAlternateColorCodes('&', line) : line);
            }
            return this;
        }

        /**
         * Sets a line of lore at a specific index in the ItemStack.
         *
         * The line of lore must not be null. If `andSymbol` is set to true, the line of lore
         * will be formatted.
         *
         * @param line the line of lore to set in the ItemStack
         * @param index the index in the lore list to set the line of lore
         * @return the current ItemStackBuilder
         * @throws NullPointerException if the line of lore is null
         * @throws IndexOutOfBoundsException if the index is out of range
         */
        public ItemStackBuilder lore(String line, int index) {
            Validate.notNull(line, "The Line is null.");
            lore.set(index, andSymbol ? ChatColor.translateAlternateColorCodes('&', line) : line);
            return this;
        }

        /**
         * Sets the Custom Model Data of the ItemStack. The param must be not null.
         * @param customModelData the CustomModelData the Item should have
         * @return the current itemStackBuilder
         * @throws NullPointerException is the param is null
         */
        public ItemStackBuilder customModelData(int customModelData) {
            Validate.notNull(customModelData, "The CustomModelData is null");
            this.customModelData = customModelData;
            return this;
        }


        /**
         * Adds an {@link org.bukkit.inventory.ItemFlag} to the ItemStack.
         *
         * The item flag must not be null.
         *
         * @param flag the item flag to add to the ItemStack
         * @return the current ItemStackBuilder
         * @throws NullPointerException if the item flag is null
         */
        public ItemStackBuilder flag(ItemFlag flag) {
            Validate.notNull(flag, "The Flag is null.");
            flags.add(flag);
            return this;
        }

        /**
         * Sets the flags for the item stack.
         *
         * @param flags the flags to set for the item stack
         * @return the current {@link ItemStackBuilder} instance
         * @throws NullPointerException if the flags list is null
         */
        public ItemStackBuilder flag(List<ItemFlag> flags) {
            Validate.notNull(flags, "The Flags are null.");
            this.flags = flags;
            return this;
        }

        /**
         * Sets the unbreakable status for the item stack.
         *
         * @param unbreakable whether the item stack should be unbreakable or not
         * @return the current {@link ItemStackBuilder} instance
         */
        public ItemStackBuilder unbreakable(boolean unbreakable) {
            meta.setUnbreakable(unbreakable);
            return this;
        }

        /**
         * Returns an Unsafe instance for the item stack builder, which contains the nbt methods.
         *
         * @return an Unsafe instance for the item stack builder
         */
        public Unsafe unsafe() {
            return new Unsafe(this);
        }

        /**
         * Deprecated. Use {@link #replaceAndSymbol(boolean)} instead.
         *
         * @return the current {@link ItemStackBuilder} instance
         */
        @Deprecated
        public ItemStackBuilder replaceAndSymbol() {
            replaceAndSymbol(!andSymbol);
            return this;
        }

        /**
         * Sets whether to replace the and symbol with the section symbol (§).
         *
         * @param replace whether to replace the and symbol or not
         * @return the current {@link ItemStackBuilder} instance
         */
        public ItemStackBuilder replaceAndSymbol(boolean replace) {
            andSymbol = replace;
            return this;
        }

        /**
         * Toggles the replacement of the and symbol with the section symbol (§).
         *
         * @return the current {@link ItemStackBuilder} instance
         */
        public ItemStackBuilder toggleReplaceAndSymbol() {
            replaceAndSymbol(!andSymbol);
            return this;
        }

        /**
         * Enables or disables the use of stack sizes larger than the maximum stack size for the item.
         *
         * @param allow whether to allow unsafe stack sizes or not
         * @return the current {@link ItemStackBuilder} instance
         */
        public ItemStackBuilder unsafeStackSize(boolean allow) {
            this.unsafeStackSize = allow;
            return this;
        }

        /**
         * Toggles the use of stack sizes larger than the maximum stack size for the item.
         *
         * @return the current {@link ItemStackBuilder} instance
         */
        public ItemStackBuilder toggleUnsafeStackSize() {
            unsafeStackSize(!unsafeStackSize);
            return this;
        }

        /**
         * Returns the display name of the item stack.
         *
         * @return the display name of the item stack
         */
        public String getDisplayname() {
            return displayname;
        }

        /**
         * Returns the stack size of the item stack.
         *
         * @return the stack size of the item stack
         */
        public int getAmount() {
            return amount;
        }

        /**
         * Returns the enchantments and their levels for the item stack.
         *
         * @return the enchantments and their levels for the item stack
         */
        public Map<Enchantment, Integer> getEnchantments() {
            return enchantments;
        }

        /**
         * Deprecated. Use {@link #getDurability()} instead.
         *
         * @return the durability of the item stack
         */
        @Deprecated
        public short getDamage() {
            return damage;
        }

        /**
         * Returns the durability of the item stack.
         *
         * @return the durability of the item stack
         */
        public short getDurability() {
            return damage;
        }

        /**
         * Returns the lore lines for the item stack.
         *
         * @return the lore lines for the item stack
         */
        public List<String> getLores() {
            return lore;
        }

        /**
         * Returns whether the and symbol is replaced with the section symbol (§).
         *
         * @return true if the and symbol is replaced, false otherwise
         */
        public boolean getAndSymbol() {
            return andSymbol;
        }

        /**
         * Returns the flags for the item stack.
         *
         * @return the flags for the item stack
         */
        public List<ItemFlag> getFlags() {
            return flags;
        }

        /**
         * Returns the material of the item stack.
         *
         * @return the material of the item stack
         */
        public Material getMaterial() {
            return material;
        }

        /**
         * Returns the item meta of the item stack.
         *
         * @return the item meta of the item stack
         */
        public ItemMeta getMeta() {
            return meta;
        }

        /**
         * Deprecated. MaterialData is subject to removal instead.
         *
         * @return the material data of the item stack
         */
        @Deprecated
        public MaterialData getData() {
            return data;
        }

        /**
         * Deprecated. Use {@link #getLores()} instead.
         *
         * @return the lore lines for the item stack
         */
        @Deprecated
        public List<String> getLore() {
            return lore;
        }

        /**
         * Saves the item stack to a configuration file at the specified path.
         *
         * @param cfg the configuration file to save to
         * @param path the path to save the item stack at
         * @return the current {@link ItemStackBuilder} instance
         */
        public ItemStackBuilder toConfig(FileConfiguration cfg, String path) {
            cfg.set(path, build());
            return this;
        }

        /**
         * Loads the item stack from a configuration file at the specified path.
         *
         * @param cfg the configuration file to load from
         * @param path the path to load the item stack from
         * @return a new {@link ItemStackBuilder} instance with the loaded item stack
         */
        public ItemStackBuilder fromConfig(FileConfiguration cfg, String path) {
            return new ItemStackBuilder(cfg, path);
        }

        /**
         * Saves the item stack to a configuration file at the specified path.
         *
         * @param cfg the configuration file to save to
         * @param path the path to save the item stack at
         * @param builder the item stack builder to save
         */
        public static void toConfig(FileConfiguration cfg, String path, ItemStackBuilder builder) {
            cfg.set(path, builder.build());
        }

        /**
         * Returns the item stack as a JSON string.
         *
         * @return the item stack as a JSON string
         */
        public String toJson() {
            return new Gson().toJson(this);
        }

        /**
         * Returns the item stack as a JSON string.
         *
         * @param builder the item stack builder to convert to a JSON string
         * @return the item stack as a JSON string
         */
        public static String toJson(ItemStackBuilder builder) {
            return new Gson().toJson(builder);
        }

        /**
         * Returns an item stack builder from a JSON string.
         *
         * @param json the JSON string to convert to an item stack builder
         * @return an item stack builder from the JSON string
         */
        public static ItemStackBuilder fromJson(String json) {
            return new Gson().fromJson(json, ItemStackBuilder.class);
        }

        /**
         * Applies the properties of an item stack builder from a JSON string to the current builder.
         *
         * @param json the JSON string to apply to the current builder
         * @param overwrite whether to overwrite the current builder's properties or not
         * @return the current {@link ItemStackBuilder} instance
         */
        public ItemStackBuilder applyJson(String json, boolean overwrite) {
            ItemStackBuilder b = new Gson().fromJson(json, ItemStackBuilder.class);
            if(overwrite)
                return b;
            if(b.displayname != null)
                displayname = b.displayname;
            if(b.data != null)
                data = b.data;
            if(b.material != null)
                material = b.material;
            if(b.lore != null)
                lore = b.lore;
            if(b.enchantments != null)
                enchantments = b.enchantments;
            if(b.item != null)
                item = b.item;
            if(b.flags != null)
                flags = b.flags;
            damage = b.damage;
            amount = b.amount;
            return this;
        }

        /**
         * Builds the item stack using the current builder's properties.
         *
         * @return the built item stack
         */
        public ItemStack build() {
            item.setType(material);
            item.setAmount(amount);
            item.setDurability(damage);
            meta = item.getItemMeta();
            if(data != null) {
                item.setData(data);
            }
            if(enchantments.size() > 0) {
                item.addUnsafeEnchantments(enchantments);
            }
            if(displayname != null) {
                meta.setDisplayName(displayname);
            }
            if(lore.size() > 0) {
                meta.setLore(lore);
            }
            if(flags.size() > 0) {
                for (ItemFlag f : flags) {
                    meta.addItemFlags(f);
                }
            }
            item.setItemMeta(meta);
            return item;
        }

        /**
         * A class containing sensitive NMS code for manipulating NBT data on an item stack.
         * Use with caution as this code is highly sensitive and can break the ItemStackBuilder if used improperly.
         */
        public class Unsafe {

            /** Do not access using this Field*/
            protected final ReflectionUtils utils = new ReflectionUtils();

            /** Do not access using this Field */
            protected final ItemStackBuilder builder;

            /**
             * Initializes the Unsafe class with an ItemStackBuilder.
             *
             * @param builder the ItemStackBuilder to initialize the Unsafe class with
             */
            public Unsafe(ItemStackBuilder builder) {
                this.builder = builder;
            }

            /**
             * Sets a NBT tag string into the NBT tag compound of the item.
             *
             * @param key the name to save the NBT tag under
             * @param value the value to save
             * @return the current Unsafe instance
             */
            public Unsafe setString(String key, String value) {
                builder.item = utils.setString(builder.item, key, value);
                return this;
            }

            /**
             * Returns the string saved under the specified key.
             *
             * @param key the key to retrieve the string from
             * @return the string saved under the key, or null if not found
             */
            public String getString(String key) {
                return utils.getString(builder.item, key);
            }

            /**
             * Sets a NBT tag integer into the NBT tag compound of the item.
             *
             * @param key the name to save the NBT tag under
             * @param value the value to save
             * @return the current Unsafe instance
             */
            public Unsafe setInt(String key, int value) {
                builder.item = utils.setInt(builder.item, key, value);
                return this;
            }

            /**
             * returns the Integer under the current Key
             * @param key the nbt tag name
             * @return int
             */
            public int getInt(String key) {
                return utils.getInt(builder.item, key);
            }

            /**
             * Sets a NBT tag double into the NBT tag compound of the item.
             *
             * @param key the name to save the NBT tag under
             * @param value the value to save
             * @return the current Unsafe instance
             */
            public Unsafe setDouble(String key, double value) {
                builder.item = utils.setDouble(builder.item, key, value);
                return this;
            }

            /**
             * Returns the double saved under the specified key.
             *
             * @param key the key to retrieve the double from
             * @return the double saved under the key, or 0 if not found
             */
            public double getDouble(String key) {
                return utils.getDouble(builder.item, key);
            }

            /**
             * Sets a NBT tag boolean into the NBT tag compound of the item.
             *
             * @param key the name to save the NBT tag under
             * @param value the value to save
             * @return the current Unsafe instance
             */
            public Unsafe setBoolean(String key, boolean value) {
                builder.item = utils.setBoolean(builder.item, key, value);
                return this;
            }

            /**
             * Returns the boolean saved under the specified key.
             *
             * @param key the key to retrieve the boolean from
             * @return the boolean saved under the key, or false if not found
             */
            public boolean getBoolean(String key) {
                return utils.getBoolean(builder.item, key);
            }

            /**
             * Returns whether the item has an NBT tag saved under the specified key.
             *
             * @param key the key to check
             * @return true if the item has an NBT tag saved under the key, false otherwise
             */
            public boolean containsKey(String key) {
                return utils.hasKey(builder.item, key);
            }

            /**
             * Returns the ItemStackBuilder instance associated with this Unsafe instance.
             *
             * @return the ItemStackBuilder instance
             */
            public ItemStackBuilder builder() {
                return builder;
            }

            /** This Class contains highly sensitive NMS Code that should not be touched unless you want to break the ItemBuilder */
            public class ReflectionUtils {

                /**
                 * Returns a String saved in the nbt data of the item at the given key
                 * If the key does not exist, it will return null
                 *
                 * @param item item
                 * @param key key
                 * @return the String if successfull, null if not
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public String getString(ItemStack item, String key) {
                    Object compound = getNBTTagCompound(getItemAsNMSStack(item));
                    if(compound == null) {
                        compound = getNewNBTTagCompound();
                    }
                    try {
                        return (String) compound.getClass().getMethod("getString", String.class).invoke(compound, key);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }

                /**
                 * Sets a String at the nbt key of the nbt data of item
                 *
                 * @param item item
                 * @param key key
                 * @param value value
                 * @return the ItemStack
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public ItemStack setString(ItemStack item, String key, String value) {
                    Object nmsItem = getItemAsNMSStack(item);
                    Object compound = getNBTTagCompound(nmsItem);
                    if(compound == null) {
                        compound = getNewNBTTagCompound();
                    }
                    try {
                        compound.getClass().getMethod("setString", String.class, String.class).invoke(compound, key, value);
                        nmsItem = setNBTTag(compound, nmsItem);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                        ex.printStackTrace();
                    }
                    return getItemAsBukkitStack(nmsItem);
                }

                /**
                 * Returns an int saved in the nbt data of the item at the given key
                 * If the key does not exist, it will return -1
                 * @param item item
                 * @param key key
                 * @return int if success, -1 if key does not exist
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public int getInt(ItemStack item, String key) {
                    Object compound = getNBTTagCompound(getItemAsNMSStack(item));
                    if(compound == null) {
                        compound = getNewNBTTagCompound();
                    }
                    try {
                        return (Integer) compound.getClass().getMethod("getInt", String.class).invoke(compound, key);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                        ex.printStackTrace();
                    }
                    return -1;
                }

                /**
                 * Sets an int at the nbt key of the nbt data of the ItemStack
                 * @param item item
                 * @param key key
                 * @param value value
                 * @return the ItemStack
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public ItemStack setInt(ItemStack item, String key, int value) {
                    Object nmsItem = getItemAsNMSStack(item);
                    Object compound = getNBTTagCompound(nmsItem);
                    if(compound == null) {
                        compound = getNewNBTTagCompound();
                    }
                    try {
                        compound.getClass().getMethod("setInt", String.class, Integer.class).invoke(compound, key, value);
                        nmsItem = setNBTTag(compound, nmsItem);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                        ex.printStackTrace();
                    }
                    return getItemAsBukkitStack(nmsItem);
                }

                /**
                 * Returns a Double saved in the nbt data of item at given nbt key
                 * Returns NaN if key does not exist
                 * @param item item
                 * @param key key
                 * @return Double at Success, NaN if key does not exist
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public double getDouble(ItemStack item, String key) {
                    Object compound = getNBTTagCompound(getItemAsNMSStack(item));
                    if(compound == null) {
                        compound = getNewNBTTagCompound();
                    }
                    try {
                        return (Double) compound.getClass().getMethod("getDouble", String.class).invoke(compound, key);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                        ex.printStackTrace();
                    }
                    return Double.NaN;
                }

                /**
                 * sets a double at the nbt key of the nbt data of the item
                 * @param item item
                 * @param key key
                 * @param value value
                 * @return the ItemStack
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public ItemStack setDouble(ItemStack item, String key, double value) {
                    Object nmsItem = getItemAsNMSStack(item);
                    Object compound = getNBTTagCompound(nmsItem);
                    if(compound == null) {
                        compound = getNewNBTTagCompound();
                    }
                    try {
                        compound.getClass().getMethod("setDouble", String.class, Double.class).invoke(compound, key, value);
                        nmsItem = setNBTTag(compound, nmsItem);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                        ex.printStackTrace();
                    }
                    return getItemAsBukkitStack(nmsItem);
                }

                /**
                 * Gets a boolean at given key of nbt data of given item.
                 * returns false if key is not existing
                 * @param item item
                 * @param key key
                 * @return boolean at success, false if key does not exist
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public boolean getBoolean(ItemStack item, String key) {
                    Object compound = getNBTTagCompound(getItemAsNMSStack(item));
                    if(compound == null) {
                        compound = getNewNBTTagCompound();
                    }
                    try {
                        return (Boolean) compound.getClass().getMethod("getBoolean", String.class).invoke(compound, key);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                }

                /**
                 * sets a boolean at nbt key of nbt data of item
                 * @param item item
                 * @param key key
                 * @param value value
                 * @return the ItemStack
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public ItemStack setBoolean(ItemStack item, String key, boolean value) {
                    Object nmsItem = getItemAsNMSStack(item);
                    Object compound = getNBTTagCompound(nmsItem);
                    if(compound == null) {
                        compound = getNewNBTTagCompound();
                    }
                    try {
                        compound.getClass().getMethod("setBoolean", String.class, Boolean.class).invoke(compound, key, value);
                        nmsItem = setNBTTag(compound, nmsItem);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                        ex.printStackTrace();
                    }
                    return getItemAsBukkitStack(nmsItem);
                }

                /**
                 * checks if items nbt data contains a key
                 * @param item item
                 * @param key key
                 * @return true if key does exist, false if not
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public boolean hasKey(ItemStack item, String key) {
                    Object compound = getNBTTagCompound(getItemAsNMSStack(item));
                    if(compound == null) {
                        compound = getNewNBTTagCompound();
                    }
                    try {
                        return (Boolean) compound.getClass().getMethod("hasKey", String.class).invoke(compound, key);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    return false;
                }

                /**
                 * get New NBT Tag Compound
                 * @return Object
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public Object getNewNBTTagCompound() {
                    String ver = Bukkit.getServer().getClass().getPackage().getName().split(".")[3];
                    try {
                        return Class.forName("net.minecraft.server." + ver + ".NBTTagCompound").newInstance();
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }

                /**
                 * set NBT Tag of Item
                 * @param tag tag
                 * @param item item
                 * @return item at success, null if not
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public Object setNBTTag(Object tag, Object item) {
                    try {
                        item.getClass().getMethod("setTag", item.getClass()).invoke(item, tag);
                        return item;
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }

                /**
                 * get NBT Tag Compound
                 * @param nmsStack nmsStack
                 * @return Object
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public Object getNBTTagCompound(Object nmsStack) {
                    try {
                        return nmsStack.getClass().getMethod("getTag").invoke(nmsStack);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }


                /**
                 * get ItemStack as nmsStack object
                 * @param item item
                 * @return object
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public Object getItemAsNMSStack(ItemStack item) {
                    try {
                        Method m = getCraftItemStackClass().getMethod("asNMSCopy", ItemStack.class);
                        return m.invoke(getCraftItemStackClass(), item);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }

                /**
                 * get nmsStack object as Bukkit Stack
                 * @param nmsStack nmsStack
                 * @return ItemStack
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public ItemStack getItemAsBukkitStack(Object nmsStack) {
                    try {
                        Method m = getCraftItemStackClass().getMethod("asCraftMirror", nmsStack.getClass());
                        return (ItemStack) m.invoke(getCraftItemStackClass(), nmsStack);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }

                /**
                 * get Craft Item Stack Class
                 * @return Class
                 */
                @SuppressWarnings("Highly sensitive NMS Code, only use this if you want to break the ItemBuilder")
                public Class<?> getCraftItemStackClass() {
                    String ver = Bukkit.getServer().getClass().getPackage().getName().split(".")[3];
                    try {
                        return Class.forName("org.bukkit.craftbukkit." + ver + ".inventory.CraftItemStack");
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }

            }
        }
    }
