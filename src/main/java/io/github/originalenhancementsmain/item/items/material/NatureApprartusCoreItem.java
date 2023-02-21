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

import java.util.List;

public class NatureApprartusCoreItem extends Item {

    public NatureApprartusCoreItem(){
        super(new Properties().tab(OriginalEnhancementMain.OETab).rarity(Rarity.create("nature_apparatus_core", ChatFormatting.GOLD)));
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText (ItemStack stack, Level world, List<Component> lore, TooltipFlag flags){
        super.appendHoverText(stack, world, lore, flags);
        lore.add(Component.translatable(getDescriptionId()+".lore1").withStyle(ChatFormatting.AQUA));
    }
}
