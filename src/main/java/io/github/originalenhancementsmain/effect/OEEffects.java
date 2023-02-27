package io.github.originalenhancementsmain.effect;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.effect.effects.FrostmourneEffect;
import io.github.originalenhancementsmain.effect.effects.LushEffect;
import io.github.originalenhancementsmain.effect.effects.SoulBurningEffect;
import io.github.originalenhancementsmain.effect.effects.TheEndEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;
import java.util.UUID;

import static io.github.originalenhancementsmain.effect.effects.TheEndEffect.THEEND_UUID;

public class OEEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, OriginalEnhancementMain.MOD_ID);

    public static final RegistryObject<MobEffect> FROSTMOURNE_EFFECT = EFFECTS.register("frostmourne_effect",()-> new FrostmourneEffect(MobEffectCategory.HARMFUL, 60159,false)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, "9c78ba98-70c0-4c2a-b71a-00f710acc426", -0.5F, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> LUSH = EFFECTS.register("lush", ()-> new LushEffect(MobEffectCategory.BENEFICIAL, 9109248, false)
            .addAttributeModifier(Attributes.ATTACK_DAMAGE, LushEffect.THEEND_UUID.toString(), 5.0F, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> THE_END = EFFECTS.register("the_end", ()-> new TheEndEffect(MobEffectCategory.HARMFUL, 0x6410b0)
            .addAttributeModifier(Attributes.ATTACK_DAMAGE, TheEndEffect.THEEND_UUID.toString(), -0.2F, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_SPEED, TheEndEffect.THEEND_UUID.toString(), -0.2F, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, TheEndEffect.THEEND_UUID.toString(), -0.2F, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> SOUL_BURNING = EFFECTS.register("soul_burning", ()-> new SoulBurningEffect(MobEffectCategory.HARMFUL, 0x23c0c6, false)
            .addAttributeModifier(Attributes.MAX_HEALTH, SoulBurningEffect.SOUL_BURNING_UUID.toString(), 0.0F, AttributeModifier.Operation.ADDITION));
}


