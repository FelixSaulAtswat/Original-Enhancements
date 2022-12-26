package io.github.originalenhancementsmain.data;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

import static io.github.originalenhancementsmain.data.OrePlacement.commonOrePlacement;

public class OEPlacedFeatures {
    public static final DeferredRegisterPlacedFeature PLACED_FEATURE = new DeferredRegisterPlacedFeature(OriginalEnhancementsMain.MOD_ID) {
    };

    public static final RegistryObject<PlacedFeature> LADIA_ORE_PLACED = PLACED_FEATURE.register("ladia_ore_placed", OEConfiguredFeatures.LADIA_ORES, commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-63), VerticalAnchor.aboveBottom(30))));

    public static void register(IEventBus bus){

        PLACED_FEATURE.register(bus);
    }
}
