package io.github.originalenhancementsmain.data.tags;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class OETags {
    public static void init(){
        Blocks.init();
    }

    public static class Blocks{
        private static void init(){}
        public static final TagKey<Block> FULL_STRUCTURE = tag("full_structure");

        public static TagKey<Block> tag(String name){
            return TagKey.create(Registry.BLOCK_REGISTRY, OriginalEnhancementsMain.getLocationResource(name));
        }
    }
}
