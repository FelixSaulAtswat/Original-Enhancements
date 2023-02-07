package io.github.originalenhancementsmain;

import io.github.originalenhancementsmain.data.tags.OEBlockTagsProvider;
import io.github.originalenhancementsmain.data.tags.OEItemTagProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OriginalEnhancementMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGather {

    @SubscribeEvent
    public static void setGatherData(final GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        OEBlockTagsProvider blockTagsProvider = new OEBlockTagsProvider(generator, helper);

        generator.addProvider(event.includeClient(), blockTagsProvider);
        generator.addProvider(event.includeClient(), new OEItemTagProvider(generator, blockTagsProvider, helper));
    }
}
