package io.github.originalenhancementsmain.item.items;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.item.CustomItemTier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

public class Frostmourne extends SwordItem {
    public Frostmourne() {
        super(CustomItemTier.PreciseAttack,50,-1,new Item.Properties().tab(OriginalEnhancementsMain.OETab));
    }
}
