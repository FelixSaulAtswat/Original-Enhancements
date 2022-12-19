package io.github.originalenhancementsmain.effects;

import io.github.originalenhancementsmain.OEMobEffect;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import java.util.Random;

public class FrostmourneEffect extends OEMobEffect {
    public FrostmourneEffect(MobEffectCategory type, int color, boolean isInstant) {
        super(type, color, isInstant);
    }
    @Override
    protected boolean canApplyEffect(int remainingTicks, int level) {
        return remainingTicks % 20 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int i) {

        Random random = new Random();
        int co = random.nextInt(5);

        entity.hurt(DamageSource.WITHER, 10.0F);

    }
    @Override
    public boolean isBeneficial() {
        return false;
    }
}
