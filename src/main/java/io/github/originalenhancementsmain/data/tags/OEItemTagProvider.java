package io.github.originalenhancementsmain.data.tags;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.item.OEItems;
import io.github.originalenhancementsmain.oeblock.OEBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
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
        this.copy(OETags.Blocks.CONVERTER_Disk, OETags.Items.CONVERTER_Disk);
        tag(OETags.Items.CONVERTER_Disk).add(OEBlocks.NATURE_CONVERTER_DISK.get().asItem());
        this.copy(OETags.Blocks.WOOD, OETags.Items.WOOD);
        addItemTags(OETags.Items.WOOD, Blocks.OAK_WOOD.asItem(), Blocks.ACACIA_WOOD.asItem(),Blocks.DARK_OAK_WOOD.asItem(), Blocks.JUNGLE_WOOD.asItem(),
                Blocks.SPRUCE_WOOD.asItem(), Blocks.MANGROVE_WOOD.asItem(), Blocks.BIRCH_WOOD.asItem());
    }

    @SafeVarargs
    private void addItemTags(TagKey<Item> tagKey, Supplier<? extends Item>... items){
        for (Supplier<? extends Item> item : items){
            tag(tagKey).add(item.get());
        }
    }

    private void addItemTags(TagKey<Item> tagKey, Item... items){
        for (Item item : items){
            tag(tagKey).add(item);
        }
    }
}
