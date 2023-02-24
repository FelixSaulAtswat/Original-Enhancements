package io.github.originalenhancementsmain.effect.effects;

import io.github.originalenhancementsmain.effect.OEMobEffect;
import io.github.originalenhancementsmain.particle.OEParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.UUID;

public class LushEffect extends OEMobEffect {

    public static final UUID THEEND_UUID =UUID.fromString("6591ce6e-b17b-422d-b94d-2f9d519eab0c");
    public LushEffect(MobEffectCategory type, int color, boolean isInstant){
        super(type, color, isInstant);
    }

    @Override
    protected boolean canApplyEffect(int remainingTicks, int level) {
        return remainingTicks % 10 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int i) {
        i++;
        entity.heal(i);

    }
    @Override
    public boolean isBeneficial() {
        return true;
    }
}
