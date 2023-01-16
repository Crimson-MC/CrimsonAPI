package net.crimsonmc.Builder;


import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.List;

public class BannerBuilder {

    private final BannerMeta bannerMeta;

    private final ItemStack itemStack;


    /**
     * This class does not need a material specification.
     * It uses the legacy banner for simplification of developing
     */
    public BannerBuilder() {
        itemStack = new ItemStack(Material.LEGACY_BANNER);
        bannerMeta = (BannerMeta) itemStack.getItemMeta();
    }

    /**
     * Sets base color of the banner
     *
     * @param color color
     * @return the bannerBuilder
     */
    public BannerBuilder setBaseColor(DyeColor color) {
        bannerMeta.setBaseColor(color);
        return this;
    }

    /**
     * Sets pattern at index
     *
     * @param index   index
     * @param pattern pattern
     * @return the bannerBuilder
     */
    public BannerBuilder setPattern(int index, Pattern pattern) {
        bannerMeta.setPattern(index, pattern);
        return this;
    }

    /**
     * sets new colored pattern at index
     *
     * @param index index
     * @param color color
     * @param type  pattern
     * @return the bannerbuilder
     */
    public BannerBuilder setPattern(int index, DyeColor color, PatternType type) {
        bannerMeta.setPattern(index, new Pattern(color, type));
        return this;
    }

    /**
     * sets patterns
     *
     * @param patterns patterns
     * @return the bannerbuilder
     */
    public BannerBuilder setPatterns(List<Pattern> patterns) {
        bannerMeta.setPatterns(patterns);
        return this;
    }

    /**
     * adds pattern on top
     *
     * @param pattern pattern
     * @return the bannerBuilder
     */
    public BannerBuilder addPattern(Pattern pattern) {
        bannerMeta.addPattern(pattern);
        return this;
    }


    /**
     * adds colored pattern on top
     *
     * @param color color
     * @param type  pattern
     * @return the bannerBuilder
     */
    public BannerBuilder addPattern(DyeColor color, PatternType type) {
        bannerMeta.addPattern(new Pattern(color, type));
        return this;
    }


    /**
     * removes Patttern at index
     *
     * @param index index
     * @return the bannerBuilder
     */
    public BannerBuilder removePattern(int index) {
        bannerMeta.removePattern(index);
        return this;
    }

    /**
     * Removes all patterns
     *
     * @return the banerBuilder
     */
    public BannerBuilder removePattern() {
        for (Pattern pattern : bannerMeta.getPatterns()) {
            bannerMeta.removePattern(bannerMeta.getPatterns().indexOf(pattern));
        }
        return this;
    }

    /**
     * creates the banner
     * Must be the last method
     *
     * @return itemStack Banner
     */
    public ItemStack build() {
        itemStack.setItemMeta(bannerMeta);
        return itemStack;
    }
}
