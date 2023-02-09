package io.github.originalenhancementsmain.data.tags;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class OETags {

    public static class Blocks {

        public static final TagKey<Block> ENERGY_CRYSTAL = tag("energy_crystal");

        public static TagKey<Block> tag(String name) {
            return BlockTags.create(OriginalEnhancementMain.getLocationResource(name));
        }
    }

    public static class Items {

        public static final TagKey<Item> UNBREAKABLE_ITEMS = tag("unbreakable_items");
        public static final TagKey<Item> INVENTORY_ITEMS = tag("inventory_items");
        public static TagKey<Item> tag(String name) {
            return ItemTags.create(OriginalEnhancementMain.getLocationResource(name));
        }
    }
}
