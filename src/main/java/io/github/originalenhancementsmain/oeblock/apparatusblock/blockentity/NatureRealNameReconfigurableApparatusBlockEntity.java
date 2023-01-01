package io.github.originalenhancementsmain.oeblock.apparatusblock.blockentity;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.oeblock.OEBlockEntities;
import io.github.originalenhancementsmain.oeblock.apparatusblock.OENameableMenuBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class NatureRealNameReconfigurableApparatusBlockEntity extends OENameableMenuBlockEntity {

    public static final Component NAME = OriginalEnhancementsMain.getTranslationWay("gui", "nature");
    public NatureRealNameReconfigurableApparatusBlockEntity(BlockPos pos, BlockState state){
        this(OEBlockEntities.NATURE_APPARATUS_CONTROLLER_BLOCK_ENTITY.get(), pos, state)
    }

    public NatureRealNameReconfigurableApparatusBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state){
        super(type, pos, state, NAME);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player playerEntity) {
        return new AlloyerContainerMenu(id, inv, this);
    }

    public void neighborChanged(Direction direction){

    }
}
