package io.github.originalenhancementsmain.data.tags;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.oeblock.OEBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class OEBlockTagsProvider extends BlockTagsProvider {

    public OEBlockTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper){
        super(generator, OriginalEnhancementMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(OETags.Blocks.ENERGY_CRYSTAL).add(OEBlocks.NATURE_ENERGY_CONDUCTOR.get());
        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(OEBlocks.LADIA_ORE.get(), OEBlocks.DEEPSLATE_LADIA_ORE.get());
        tag(Tags.Blocks.NEEDS_GOLD_TOOL).add(OEBlocks.NATURE_ENERGY_CONDUCTOR.get(), OEBlocks.NATURE_REAL_NAME_RECONFIGURABLE_APPARATUS_BLOCK.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(OEBlocks.DEMON_ICE.get());

    }

    @Override
    @NotNull
    public String getName(){
        return "BlockTagsProvider:" + OriginalEnhancementMain.MOD_ID;
    }
}
