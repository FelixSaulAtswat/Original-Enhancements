package io.github.originalenhancementsmain.item.items.material;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class LadiaCrystalMax extends Item {
    public LadiaCrystalMax(){super(new Item.Properties().durability(5).tab(OriginalEnhancementMain.OETab).rarity(Rarity.UNCOMMON));}

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText (ItemStack stack, Level world, List<Component> lore, TooltipFlag flags){
        super.appendHoverText(stack, world, lore, flags);
        lore.add(new TranslatableComponent(getDescriptionId() + ".lore1").withStyle(ChatFormatting.DARK_BLUE));
        lore.add(new TranslatableComponent(getDescriptionId() + ".lore2").withStyle(ChatFormatting.RED));
        lore.add(new TranslatableComponent("oe.crystal_charges", stack.getMaxDamage() - stack.getDamageValue()).withStyle(ChatFormatting.GRAY));
    }
}

