package io.github.originalenhancementsmain.data.tags;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class OETags {

    public static class Blocks{

        public static final TagKey<Block> ENERGY_CRYSTAL = tag("energy_crystal");

        public static TagKey<Block> tag(String name){
            return BlockTags.create(OriginalEnhancementMain.getLocationResource(name));
        }
    }

    public static class Items{

    }
}
