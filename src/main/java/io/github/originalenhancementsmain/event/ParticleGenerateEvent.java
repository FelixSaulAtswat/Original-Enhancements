package io.github.originalenhancementsmain.event;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.effect.OEEffects;
import io.github.originalenhancementsmain.effect.effects.LushEffect;
import io.github.originalenhancementsmain.particle.OEParticles;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = OriginalEnhancementMain.MOD_ID)
public class ParticleGenerateEvent {

    @SubscribeEvent
    public static void generateParticle(LivingEvent.LivingTickEvent event){
        LivingEntity entity = event.getEntity();
        if (!entity.getLevel().isClientSide){
            if (entity.hasEffect(OEEffects.LUSH.get())){
                if (Objects.requireNonNull(entity.getAttribute(Attributes.ATTACK_DAMAGE)).getModifier(LushEffect.THEEND_UUID) != null) {
                    ((ServerLevel) entity.getLevel()).sendParticles(OEParticles.GRASS.get(), entity.getX(), entity.getY() + entity.getBbHeight() / 2.0d, entity.getZ(), 1, entity.getBbWidth() / 2.0d, entity.getBbHeight() / 2.0d, entity.getBbWidth() / 2.0d, 0);
                }
            }
        }
    }
}
