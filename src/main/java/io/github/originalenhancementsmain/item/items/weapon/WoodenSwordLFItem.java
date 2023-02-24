package io.github.originalenhancementsmain.item.items.weapon;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.item.CustomItemTier;
import io.github.originalenhancementsmain.item.OEItems;
import io.github.originalenhancementsmain.item.RarityProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

public class WoodenSwordLFItem extends SwordItem {

    public WoodenSwordLFItem(){
        super(CustomItemTier.OriginalTools,14,-1.8f, new Properties().tab(OriginalEnhancementMain.OETab).rarity(Rarity.create("wooden_sword_lush_forest", ChatFormatting.AQUA)));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lore, TooltipFlag flag) {
        super.appendHoverText(stack, level, lore, flag);
        lore.add(RarityProvider.RARITY.getRarity());
        lore.add(Component.translatable(getDescriptionId() + ".lore1").withStyle(ChatFormatting.RED));
        lore.add(Component.translatable(getDescriptionId() + ".lore2").withStyle(ChatFormatting.GRAY));
    }
}
