package io.github.originalenhancementsmain.effects;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OEEffect {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, OriginalEnhancementsMain.MOD_ID);

    public static final RegistryObject<MobEffect> FROSTMOURNE_EFFECT = EFFECTS.register("frostmourne_effect",()->
            new FrostmourneEffect(MobEffectCategory.HARMFUL, 2883584,false).addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.5F, AttributeModifier.Operation.MULTIPLY_TOTAL));

}


