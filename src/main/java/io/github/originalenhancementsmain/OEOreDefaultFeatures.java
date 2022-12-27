package io.github.originalenhancementsmain;


import io.github.originalenhancementsmain.data.OEPlacedFeatures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OriginalEnhancementsMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OEOreDefaultFeatures {

    private static boolean condition(boolean notHasTyp, ResourceKey<Biome> resourceKey, Biome.BiomeCategory category, Biome.BiomeCategory comparison, BiomeDictionary.Type type){
        if (notHasTyp || resourceKey == null) {
            if (comparison == null) {
                return category != Biome.BiomeCategory.NONE;
            }
            return category == comparison;
        }
        return BiomeDictionary.hasType(resourceKey, type);
    }

    @SubscribeEvent
    public static void saS(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder builder = event.getGeneration();
        Biome.BiomeCategory category = event.getCategory();
        ResourceLocation name = event.getName();
        ResourceKey<Biome> biomeResourceKey = name == null ? null : ResourceKey.create(Registry.BIOME_REGISTRY, name);
        boolean notHasType = biomeResourceKey == null || !BiomeDictionary.hasAnyType(biomeResourceKey);


        if (condition(notHasType, biomeResourceKey, category, null, BiomeDictionary.Type.OVERWORLD)) {
            OEPlacedFeatures.LADIA_ORE_PLACED.getHolder().ifPresent(holder -> builder.addFeature(Decoration.UNDERGROUND_ORES, holder));
        }
    }


}
