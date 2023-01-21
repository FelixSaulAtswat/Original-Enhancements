package io.github.originalenhancementsmain;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OESounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, OriginalEnhancementMain.MOD_ID);
    public static final RegistryObject<SoundEvent> DEATH_KNIGHTAMBIENT1 = build("entity.deathknight.deathknightsay1");
    public static final RegistryObject<SoundEvent> DEATH_KNIGHTAMBIENT2 = build("entity.deathknight.deathknightsay2");
    public static final RegistryObject<SoundEvent> DEATH_KNIGHTAMBIENT3 = build("entity.deathknight.deathknightsay3");
    public static final RegistryObject<SoundEvent> DEATH_KNIGHTDEATH = build("entity.deathknight.deathknightdeath");
    public static final RegistryObject<SoundEvent> DEATH_KNIGHTHURT1 = build("entity.deathknight.deathknighthit1");
    public static final RegistryObject<SoundEvent> DEATH_KNIGHTHURT2 = build("entity.deathknight.deathknighthit2");

    public static final RegistryObject<SoundEvent> DEATH_KNIGHTSTEP1 = build("entity.deathknight.deathknightstep1");
    public static final RegistryObject<SoundEvent> DEATH_KNIGHTSTEP2 = build("entity.deathknight.deathknightstep2");
    public static final RegistryObject<SoundEvent> DEATH_KNIGHTSTEP3 = build("entity.deathknight.deathknightstep3");
    public static final RegistryObject<SoundEvent> DEATH_KNIGHTSTEP4 = build("entity.deathknight.deathknightstep4" +
            "");
    public static final RegistryObject<SoundEvent> DEATH_KNIGHTSUMMON = build("entity.deathknight.deathknightsummon");
    public static final RegistryObject<SoundEvent> DEATH_KNIGHTSTEP5 = build("entity.deathknight.deathknightstep5");
    //public static final RegistryObject<SoundEvent> SCEPTER_DRAIN = build("item.twilightforest.scepter.drain");
    //public static final RegistryObject<SoundEvent> SCEPTER_PEARL = build("item.twilightforest.scepter.pearl");
    //public static final RegistryObject<SoundEvent> SCEPTER_USE = build("item.twilightforest.scepter.use");

    private static RegistryObject<SoundEvent> build(String id)
    {
        return SOUNDS.register(id, () -> new SoundEvent(new ResourceLocation(OriginalEnhancementMain.MOD_ID, id)));
    }

}
