package io.github.originalenhancementsmain.oeblock.apparatusblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class BaseApparatusBlockEntity extends BlockEntity{

    public BaseApparatusBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state){
        super(entityType, pos, state);
    }

    public boolean isClient(){
        return this.getLevel() != null && this.getLevel().isClientSide;
    }

    protected boolean shouldSyncOnUpdate() {
        return false;
    }

    public void setChangedFast() {
        if (level != null) {
            if (level.hasChunkAt(worldPosition)) {
                level.getChunkAt(worldPosition).setUnsaved(true);
            }
        }
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return shouldSyncOnUpdate() ? ClientboundBlockEntityDataPacket.create(this) : null;
    }

    protected void saveSynced(CompoundTag nbt) {}

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = new CompoundTag();
        saveSynced(nbt);
        return nbt;
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        saveSynced(nbt);
    }
}
