package io.github.originalenhancementsmain.item.items.material;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class MagicIcecrystal extends Item {
    public MagicIcecrystal(){
        super(new Properties().tab(OriginalEnhancementMain.OETab).rarity(Rarity.create("ladia_crystal", ChatFormatting.GOLD)));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lore, TooltipFlag flag) {
        super.appendHoverText(stack, level, lore, flag);
        lore.add(Component.translatable(getDescriptionId() + ".lore1").withStyle(ChatFormatting.DARK_BLUE));
        lore.add(Component.translatable(getDescriptionId()+".lore2").withStyle(ChatFormatting.RED));
    }
}
