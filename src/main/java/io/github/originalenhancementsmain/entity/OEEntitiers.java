package io.github.originalenhancementsmain.entity;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.entity.monster.DeathKnight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OEEntitiers {public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
        OriginalEnhancementMain.MOD_ID);

    //我们的生物类型为MONSTER(怪物)，你可以定义其他类型MOB等，两个字符串要一样比如"re8dimi"
    public static final RegistryObject<EntityType<DeathKnight>> DEATH_KNIGHT_IMI = ENTITY_TYPES.register("deathknightimi",
            () -> EntityType.Builder.of(DeathKnight::new, MobCategory.CREATURE).sized(0.8f,3.0f).setTrackingRange(30).build(new ResourceLocation(OriginalEnhancementMain.MOD_ID, "deathknightimi").toString()));
}




