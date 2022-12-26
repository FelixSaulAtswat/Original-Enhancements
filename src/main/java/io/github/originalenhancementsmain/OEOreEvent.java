package io.github.originalenhancementsmain;


import io.github.originalenhancementsmain.data.OEPlacedFeatures;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;

public class OEOreEvent {

    public static void addOEOres(BiomeGenerationSettingsBuilder builder) {
        OEPlacedFeatures.LADIA_ORE_PLACED.getHolder().ifPresent(holder -> builder.addFeature(Decoration.UNDERGROUND_ORES, holder));
    }


}
