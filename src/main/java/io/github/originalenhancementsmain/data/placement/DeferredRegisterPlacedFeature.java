package io.github.originalenhancementsmain.data.placement;

import io.github.originalenhancementsmain.data.DeferredRegisterSettings;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class DeferredRegisterPlacedFeature extends DeferredRegisterSettings<PlacedFeature> {
    public DeferredRegisterPlacedFeature(String modId){
        super(Registry.PLACED_FEATURE_REGISTRY, modId);
    }

    public RegistryObject<PlacedFeature> registry(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers){
        return register.register(name, ()-> new PlacedFeature(Holder.hackyErase(configuredFeature.getHolder().orElseThrow(()-> new IllegalStateException("This modifier does not have holders"))), List.copyOf(modifiers)));
    }

    public RegistryObject<PlacedFeature> register(String name, RegistryObject<ConfiguredFeature<?,?>> feature, List<PlacementModifier> placementModifiers) {
        return registry(name, feature, placementModifiers);
    }
}
