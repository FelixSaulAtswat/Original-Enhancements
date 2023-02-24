package io.github.originalenhancementsmain.item.items.weapon;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.effect.OEEffects;
import io.github.originalenhancementsmain.effect.OEMobEffect;
import io.github.originalenhancementsmain.item.CustomItemTier;
import io.github.originalenhancementsmain.item.RarityProvider;
import io.github.originalenhancementsmain.particle.OEParticles;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class WoodenSwordTFOTEItem extends SwordItem {
    public WoodenSwordTFOTEItem(){
        super(CustomItemTier.RarityTools,24,-1.8f, new Properties().tab(OriginalEnhancementMain.OETab).rarity(Rarity.create("wooden_sword_the_forest_of_the_end", ChatFormatting.AQUA)));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lore, TooltipFlag flag) {
        super.appendHoverText(stack, level, lore, flag);
        lore.add(RarityProvider.RARITY.getRarity());
        lore.add(Component.translatable(getDescriptionId() + ".lore1").withStyle(ChatFormatting.RED));
        lore.add(Component.translatable(getDescriptionId() + ".lore2").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity entity, @NotNull LivingEntity user) {
        boolean result = super.hurtEnemy(stack, entity, user);

        if (result){
            if (!entity.hasEffect(OEEffects.THE_END.get())) {
                entity.addEffect(new MobEffectInstance(OEEffects.THE_END.get(), 20 * 5, 1));
            }else {
                entity.addEffect(new MobEffectInstance(OEEffects.THE_END.get(), 20 * 5, 2));
            }
        }
        return result;
    }
}
