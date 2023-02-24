package io.github.originalenhancementsmain.item.items.weapon;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.item.CustomItemTier;
import io.github.originalenhancementsmain.item.RarityProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = OriginalEnhancementMain.MOD_ID)
public class WoodenSwordOItem extends SwordItem {
    public WoodenSwordOItem() {
        super(CustomItemTier.OriginalTools,4,-2, new Properties().tab(OriginalEnhancementMain.OETab).rarity(Rarity.create("wooden_sword_original", ChatFormatting.WHITE)));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lore, TooltipFlag flag) {
        super.appendHoverText(stack, level, lore, flag);
        lore.add(RarityProvider.TRIVIAL.getRarity());
    }


}
