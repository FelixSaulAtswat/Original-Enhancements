package io.github.originalenhancementsmain.data.placement;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static io.github.originalenhancementsmain.data.placement.OrePlacement.rareOrePlacement;

public class OEPlacedFeatures {
    public static final DeferredRegisterPlacedFeature MOD_PLACED_FEATURE = new DeferredRegisterPlacedFeature(OriginalEnhancementMain.MOD_ID) {};

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, OriginalEnhancementMain.MOD_ID);

    public static final RegistryObject<PlacedFeature> LADIA_ORE_PLACED = PLACED_FEATURE.register("ladia_ore_placed", ()-> new PlacedFeature(OEConfiguredFeatures.LADIA_ORES.getHolder().get(), rareOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-63), VerticalAnchor.aboveBottom(90)))));

    public static void register(IEventBus bus){

        PLACED_FEATURE.register(bus);
        MOD_PLACED_FEATURE.register(bus);
    }
}
