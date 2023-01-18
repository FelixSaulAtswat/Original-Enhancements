package io.github.originalenhancementsmain.oeblock.apparatusblock;

import io.github.originalenhancementsmain.recipe.OERecipes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ApparatusControllerMenu<T extends BlockEntity>extends BaseApparatusMenu<T>{
    protected ApparatusControllerMenu(int id, MenuType<?> type, Inventory inventory, T tile){
        super(id, type, inventory, tile);
        OERecipes.OPENED_TRIGGER.trigger(tile, inventory);
    }
}
