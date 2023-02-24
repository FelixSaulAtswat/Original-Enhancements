package io.github.originalenhancementsmain.event;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.effect.OEEffects;
import io.github.originalenhancementsmain.item.OEItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

import static io.github.originalenhancementsmain.item.items.weapon.Frostmourne.EXTRA_DAMAGE;

@Mod.EventBusSubscriber(modid = OriginalEnhancementMain.MOD_ID)
public class WeaponsEvent {

    @SubscribeEvent
    public static void onAttacked(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();

        if (!entity.level.isClientSide) {
            ItemStack stack = entity.getMainHandItem();


            if (!stack.isEmpty()) {
                if ((stack.is(OEItems.WOODEN_SWORD_LF.get()))) {
                    if (!entity.hasEffect(OEEffects.LUSH.get())) {
                        entity.addEffect(new MobEffectInstance(OEEffects.LUSH.get(), 20 * 5, 0));
                    }else if (Objects.requireNonNull(entity.getEffect(OEEffects.LUSH.get())).getAmplifier() < 1 && Objects.requireNonNull(entity.getEffect(OEEffects.LUSH.get())).getDuration() <= 40) {
                        entity.addEffect(new MobEffectInstance(OEEffects.LUSH.get(), 20 * 6, 1));
                    }else if (Objects.requireNonNull(entity.getEffect(OEEffects.LUSH.get())).getAmplifier() <= 2 && Objects.requireNonNull(entity.getEffect(OEEffects.LUSH.get())).getDuration() <= 40){
                        entity.addEffect(new MobEffectInstance(OEEffects.LUSH.get(), 20 * 7, 2));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void extraDamage(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        MobType type = entity.getMobType();

        if(!entity.level.isClientSide && event.getSource().getDirectEntity() instanceof LivingEntity living) {
            ItemStack stack = living.getMainHandItem();

            if (!stack.isEmpty()){
                if ((stack.is(OEItems.FROSTMOURNE.get()))) {
                    if (type != MobType.UNDEAD) {
                        event.setAmount(event.getAmount() + EXTRA_DAMAGE);
                    }
                }
            }
        }

    }

    @SubscribeEvent
    public static void setDamage(LivingDamageEvent event){
        LivingEntity entity = event.getEntity();

        if (!entity.level.isClientSide){
            if (entity.hasEffect(OEEffects.LUSH.get())){
                float f = ( 1.0f - ((float)Objects.requireNonNull(entity.getEffect(OEEffects.LUSH.get())).getAmplifier() + 1) / 10.0f);
                event.setAmount(event.getAmount() * (f < 0 ? 0 : f));
            }
        }
    }

    @SubscribeEvent
    public static void extraHeal(LivingDeathEvent deathEvent){
        LivingEntity death = deathEvent.getEntity();
        MobType type = death.getMobType();

        if(!death.level.isClientSide && deathEvent.getSource().getDirectEntity() instanceof LivingEntity player){
            ItemStack item = player.getMainHandItem();

            if (!item.isEmpty()) {
                if ((item.is(OEItems.FROSTMOURNE.get()))) {
                    if (type != MobType.UNDEAD) {
                        player.heal(30);
                    }
                }
            }
        }
    }
}
