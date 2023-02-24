package io.github.originalenhancementsmain.particle;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OEParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, OriginalEnhancementMain.MOD_ID);

    public static final RegistryObject<SimpleParticleType> GRASS = PARTICLE_TYPES.register("grass", ()-> new SimpleParticleType(false));

    public static void register(IEventBus bus){
        PARTICLE_TYPES.register(bus);
    }
}
