package io.github.originalenhancementsmain.data.placement;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

import static io.github.originalenhancementsmain.data.placement.OrePlacement.rareOrePlacement;

public class OEPlacedFeatures {
    public static final DeferredRegisterPlacedFeature PLACED_FEATURE = new DeferredRegisterPlacedFeature(OriginalEnhancementsMain.MOD_ID) {
    };

    public static final RegistryObject<PlacedFeature> LADIA_ORE_PLACED = PLACED_FEATURE.register("ladia_ore_placed", OEConfiguredFeatures.LADIA_ORES, rareOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-63), VerticalAnchor.aboveBottom(90))));

    public static void register(IEventBus bus){

        PLACED_FEATURE.register(bus);
    }
}
