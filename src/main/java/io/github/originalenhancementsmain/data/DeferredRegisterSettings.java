package io.github.originalenhancementsmain.data;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

@SuppressWarnings("Access")
public abstract class DeferredRegisterSettings<T> {
    protected final DeferredRegister<T> register;
    private final String id;
    protected DeferredRegisterSettings(ResourceKey<Registry<T>> registry, String id){
        this(DeferredRegister.create(registry, id), id);
    }
    protected DeferredRegisterSettings(DeferredRegister<T> deferredRegister, String id){
        this.register = deferredRegister;
        this.id = id;
    }

    public void register(IEventBus bus) {
        register.register(bus);
    }

}
