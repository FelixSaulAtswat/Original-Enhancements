package io.github.originalenhancementsmain.effect.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import java.util.UUID;

public class TheEndEffect extends MobEffect {
    public static final UUID THEEND_UUID =UUID.fromString("fbb9827c-54ed-4e15-b5d3-0d33bff7f611");
    public TheEndEffect(MobEffectCategory type, int color){
        super(type, color);
    }
}
