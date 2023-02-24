package io.github.originalenhancementsmain.effect.effects;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.effect.OEMobEffect;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Objects;
import java.util.UUID;

public class SoulBurningEffect extends OEMobEffect {

    public SoulBurningEffect(MobEffectCategory type, int color, boolean isInstant){
        super(type, color, isInstant);
    }

    public static final UUID SOUL_BURNING_UUID = UUID.fromString("3b66d1bf-eb55-4fce-ab34-ba1c81d648e7");

    @Override
    protected boolean canApplyEffect(int remainingTicks, int level) {
        return remainingTicks % 20 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int i) {
        i++;
        entity.hurt(OriginalEnhancementMain.getDamageSource("soul_burning").bypassArmor(), 6.0F * i);
        if (entity.getMaxHealth() - 6.0f >= 5.0f) {
            Objects.requireNonNull(entity.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(entity.getMaxHealth() - 3.0f);
        }
    }
    @Override
    public boolean isBeneficial() {
        return false;
    }
}
