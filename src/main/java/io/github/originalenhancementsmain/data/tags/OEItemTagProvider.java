package io.github.originalenhancementsmain.data.tags;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OEItemTagProvider extends ItemTagsProvider {

    public OEItemTagProvider(DataGenerator generator, OEBlockTagsProvider tagsProvider, ExistingFileHelper existingFileHelper){
        super(generator, tagsProvider, OriginalEnhancementMain.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags(){
    }
}
