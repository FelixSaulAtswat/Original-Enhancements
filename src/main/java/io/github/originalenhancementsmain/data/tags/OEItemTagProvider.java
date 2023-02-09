package io.github.originalenhancementsmain.data.tags;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.item.OEItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class OEItemTagProvider extends ItemTagsProvider {

    public OEItemTagProvider(DataGenerator generator, OEBlockTagsProvider tagsProvider, ExistingFileHelper existingFileHelper){
        super(generator ,tagsProvider, OriginalEnhancementMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        addItemTags(OETags.Items.UNBREAKABLE_ITEMS, OEItems.FROSTMOURNE);
        addItemTags(OETags.Items.INVENTORY_ITEMS, OEItems.FROSTMOURNE_SELF);
    }

    @SafeVarargs
    private void addItemTags(TagKey<Item> tagKey, Supplier<? extends Item>... items){
        for (Supplier<? extends Item> item : items){
            tag(tagKey).add(item.get());
        }
    }
}
