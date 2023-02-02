package io.github.originalenhancementsmain.data.tags;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.oeblock.OEBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class OEBlockTagsProvider extends BlockTagsProvider {

    public OEBlockTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper){
        super(generator, OriginalEnhancementMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        addBlocksTag(BlockTags.MINEABLE_WITH_PICKAXE, OEBlocks.DEMON_ICE, OEBlocks.LADIA_ORE, OEBlocks.DEEPSLATE_LADIA_ORE,OEBlocks.NATURE_REAL_NAME_RECONFIGURABLE_APPARATUS_BLOCK,
                OEBlocks.NATURE_ENERGY_CONDUCTOR);
        addBlocksTag(OETags.Blocks.ENERGY_CRYSTAL, OEBlocks.NATURE_ENERGY_CONDUCTOR);
        addBlocksTag(Tags.Blocks.NEEDS_NETHERITE_TOOL, OEBlocks.LADIA_ORE, OEBlocks.DEEPSLATE_LADIA_ORE);
        addBlocksTag(Tags.Blocks.NEEDS_GOLD_TOOL, OEBlocks.NATURE_ENERGY_CONDUCTOR, OEBlocks.NATURE_REAL_NAME_RECONFIGURABLE_APPARATUS_BLOCK);
        addBlocksTag(BlockTags.NEEDS_DIAMOND_TOOL, OEBlocks.DEMON_ICE);
    }

    @SafeVarargs
    private void addBlocksTag(TagKey<Block> tag, Supplier<? extends Block>... blocks){
        for (Supplier<? extends Block> block : blocks){
            tag(tag).add(block.get());
        }
    }

    @Override
    @NotNull
    public String getName(){
        return "BlockTagsProvider:" + OriginalEnhancementMain.MOD_ID;
    }
}
