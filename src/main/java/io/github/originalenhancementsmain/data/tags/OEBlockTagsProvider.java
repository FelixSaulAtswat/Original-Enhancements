package io.github.originalenhancementsmain.data.tags;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.oeblock.OEBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OEBlockTagsProvider extends BlockTagsProvider {

    public OEBlockTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper){
        super(generator, OriginalEnhancementMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(OETags.Blocks.ENERGY_CRYSTAL).add(OEBlocks.NATURE_ENERGY_Conductor.get());
    }
}
