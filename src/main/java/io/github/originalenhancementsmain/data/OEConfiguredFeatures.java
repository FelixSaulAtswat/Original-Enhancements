package io.github.originalenhancementsmain.data;

import com.google.common.base.Suppliers;
import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.block.OEBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class OEConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, OriginalEnhancementsMain.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> WORLD_LADIA_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, OEBlocks.LADIA_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, OEBlocks.DEEPSLATE_LADIA_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> LADIA_ORES = CONFIGURED_FEATURE.register("laida_ores", ()-> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(WORLD_LADIA_ORE.get(), 7)));

    public static void register(IEventBus bus) {
        CONFIGURED_FEATURE.register(bus);
    }
}
