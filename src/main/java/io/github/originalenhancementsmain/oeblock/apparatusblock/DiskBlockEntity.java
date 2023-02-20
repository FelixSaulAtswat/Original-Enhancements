package io.github.originalenhancementsmain.oeblock.apparatusblock;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.data.network.InventorySlotSyncPacket;
import io.github.originalenhancementsmain.data.network.OENetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class DiskBlockEntity extends InteractiveBlockEntity implements WorldlyContainer {
    public static final int SLOT = 0;
    private static final Component NAME = OriginalEnhancementMain.getTranslationWay("gui", "converter_disk");

    protected DiskBlockEntity(BlockEntityType<?> beType, BlockPos pos, BlockState state) {
        super(beType, pos, state, NAME, false,1, 1);

    }


    public void interact(Player player, InteractionHand hand){

        if (level == null || level.isClientSide){
            return;
        }

        ItemStack handStack = player.getItemInHand(hand);
        ItemStack slotStack = getItem(0);

        if (slotStack.isEmpty() && !handStack.isEmpty()){
            ItemStack stack = handStack.split(1);
            player.setItemInHand(hand, handStack.isEmpty()? ItemStack.EMPTY : handStack);
            setItem(SLOT, stack);
        }
        if (!slotStack.isEmpty()){
            ItemHandlerHelper.giveItemToPlayer(player, slotStack, player.getInventory().selected);
            setItem(0, ItemStack.EMPTY);
        }
    }

    @Override
    public void setItem(int slot, ItemStack itemstack) {
        if (level != null && level instanceof ServerLevel && !level.isClientSide && !ItemStack.matches(itemstack, getItem(slot))) {
            OENetwork.getInstance().sendToClientsAround(new InventorySlotSyncPacket(itemstack, slot, worldPosition), (ServerLevel) level, this.worldPosition);
        }
        super.setItem(slot, itemstack);
    }


    @Override
    @Nonnull
    public int[] getSlotsForFace(Direction side) {
        return new int[]{SLOT};
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return index == SLOT;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return index == SLOT;
    }


    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {return null;}

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        writeInventoryToNBT(nbt);
        return nbt;
    }

    @Override
    protected boolean shouldSyncOnUpdate() {
        return true;
    }

}
