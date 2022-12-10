package io.github.originalenhancementsmain.item.items;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.item.CustomItemTier;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class Frostmourne extends SwordItem {
    public Frostmourne() {
        super(CustomItemTier.MythologicalTools,49,-1,new Item.Properties().tab(OriginalEnhancementsMain.OETab).rarity(Rarity.EPIC));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lore, TooltipFlag flag) {
        super.appendHoverText(stack, level, lore, flag);
        lore.add(new TranslatableComponent(getDescriptionId() + ".lore").withStyle(ChatFormatting.GOLD));
        lore.add(new TranslatableComponent(getDescriptionId()+".lore2").withStyle(ChatFormatting.RED));
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {

        if (allowdedIn(tab)) {
            ItemStack stack = new ItemStack(this);
            CompoundTag tags = new CompoundTag();
            tags.putBoolean("Unbreakable", true);

            stack.setTag(tags);
            items.add(stack);
        }
    }

}
