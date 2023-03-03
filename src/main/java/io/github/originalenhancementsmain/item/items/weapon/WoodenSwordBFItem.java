package io.github.originalenhancementsmain.item.items.weapon;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.effect.OEEffects;
import io.github.originalenhancementsmain.item.CustomItemTier;
import io.github.originalenhancementsmain.item.RarityProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class WoodenSwordBFItem extends SwordItem {

    public WoodenSwordBFItem(){
        super(CustomItemTier.OriginalTools,19,-1.8f, new Properties().tab(OriginalEnhancementMain.OETab).rarity(Rarity.create("wooden_sword_lush_forest", ChatFormatting.AQUA)));
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

        if (result) {
            entity.addEffect(new MobEffectInstance(OEEffects.SOUL_BURNING.get(), 20 * 3, 1));
        }
        return result;
    }
}
