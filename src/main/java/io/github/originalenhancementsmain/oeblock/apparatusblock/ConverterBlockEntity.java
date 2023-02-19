package io.github.originalenhancementsmain.oeblock.apparatusblock;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Iterator;

public abstract class ConverterBlockEntity extends ApparatusNameableMenuBlockEntity implements Container, WorldlyContainer, MenuProvider {

    public static final Component NAME = OriginalEnhancementMain.getTranslationWay("gui", "converter_disk");
    private static final String SLOTS = "slot";
    private static final String ITEMS = "item";
    private static final int SLOT = 0;
    protected int MAX_STACK_SIZE;
    private final NonNullList<ItemStack> inventorySize = NonNullList.withSize(1, ItemStack.EMPTY);

    @Getter
    protected IItemHandlerModifiable itemHandler;
    protected LazyOptional<IItemHandlerModifiable> itemHandlerCap;


    public ConverterBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState state,int maxStackSize) {
        super(type, worldPosition, state, NAME);
        this.itemHandler = new InvWrapper(this);
        this.itemHandlerCap = LazyOptional.of(() -> itemHandler);
        this.MAX_STACK_SIZE = maxStackSize;
    }


    public void interact(Player player, InteractionHand hand){

        if (level == null || level.isClientSide){
            return;
        }

        ItemStack handStack = player.getItemInHand(hand);
        ItemStack slotStack = itemHandler.getStackInSlot(0);
        if (slotStack.isEmpty() && !handStack.isEmpty()){
            ItemStack stack = handStack.split(1);
            player.setItemInHand(hand, handStack.isEmpty()? ItemStack.EMPTY : handStack);
            setItem(0, stack);
        }
        if (!slotStack.isEmpty()){

            ItemHandlerHelper.giveItemToPlayer(player, slotStack, player.getInventory().selected);
            setItem(0, ItemStack.EMPTY);
        }
    }

    @Override
    public int getContainerSize() {
        return this.inventorySize.size();
    }

    @Override
    public boolean isEmpty() {
        Iterator var1 = this.inventorySize.iterator();

        ItemStack itemstack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            itemstack = (ItemStack)var1.next();
        } while(itemstack.isEmpty());

        return false;
    }

    @Override
    public ItemStack getItem(int slot) {
        if (slot < 0 || slot >= this.inventorySize.size()) {
            return ItemStack.EMPTY;
        }

        return this.inventorySize.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        if (amount <= 0) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemStack = this.getItem(slot);
            if (itemStack.isEmpty()) {
                return ItemStack.EMPTY;
            } else if (itemStack.getCount() <= amount) {
                this.setItem(slot, ItemStack.EMPTY);
                this.setChangedFast();
                return itemStack;
            } else {
                itemStack = itemStack.split(amount);
                if (this.getItem(slot).getCount() == 0) {
                    this.setItem(slot, ItemStack.EMPTY);
                }

                this.setChangedFast();
                return itemStack;
            }
        }
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack itemStack = this.getItem(slot);
        this.setItem(slot, ItemStack.EMPTY);
        return itemStack;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (slot >= 0 && slot <= this.inventorySize.size()){
            ItemStack nowItemStack = (ItemStack) this.inventorySize.get(slot);
            this.inventorySize.set(slot, stack);
            if (!stack.isEmpty() && stack.getCount() > this.getMaxStackSize()){
                stack.setCount(this.getContainerSize());
            }
            if (ItemStack.matches(nowItemStack, stack)){
                this.setChangedFast();
            }
        }
    }

    @Override
    public int getMaxStackSize(){return this.MAX_STACK_SIZE;}

    @Override
    public boolean stillValid(Player player) {
        if (this.level != null && this.level.getBlockEntity(this.worldPosition) == this && this.getBlockState().getBlock() != Blocks.AIR) {
            return player.distanceToSqr((double)this.worldPosition.getX() + 0.5, (double)this.worldPosition.getY() + 0.5, (double)this.worldPosition.getZ() + 0.5) <= 64.0;
        } else {
            return false;
        }
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

    @Override
    public void load(CompoundTag nbt){
        super.load(nbt);
        ListTag list = nbt.getList(ITEMS, Tag.TAG_COMPOUND);

        int limit = this.getMaxStackSize();
        ItemStack stack;
        for (int i = 0; i < list.size(); ++i) {
            CompoundTag itemTag = list.getCompound(i);
            int slot = itemTag.getByte(SLOTS) & 255;
            if (slot < this.inventorySize.size()) {
                stack = ItemStack.of(itemTag);
                if (!stack.isEmpty() && stack.getCount() > limit) {
                    stack.setCount(limit);
                }
                this.inventorySize.set(slot, stack);
            }
        }
    }

    @Override
    public void saveSynced(CompoundTag tag){
       super.saveSynced(tag);
    }



    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        Container inventory = this;
        ListTag nbttaglist = new ListTag();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            if (!inventory.getItem(i).isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putByte(SLOTS, (byte) i);
                inventory.getItem(i).save(itemTag);
                nbttaglist.add(itemTag);
            }
        }

        tag.put(ITEMS, nbttaglist);
    }


    @Override
    public void clearContent() {
        Collections.fill(this.inventorySize, ItemStack.EMPTY);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return null;
    }
}
