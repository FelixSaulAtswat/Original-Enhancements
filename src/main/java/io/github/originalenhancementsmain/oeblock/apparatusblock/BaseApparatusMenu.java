package io.github.originalenhancementsmain.oeblock.apparatusblock;

import io.github.originalenhancementsmain.data.util.BlockEntityUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nullable;

public class BaseApparatusMenu<T extends BlockEntity> extends AbstractContainerMenu {

    private final T oeb;
    private final Inventory inventory;
    private final ContainerData data;

    protected BaseApparatusMenu(int containerId , MenuType<?> menuType, Inventory inventory, T oeb, ContainerData data){
        super(menuType, containerId);
        this.oeb = oeb;
        this.inventory = inventory;
        this.data = data;
    }

    public boolean canWork(){
        return data.get(0) > 0;
    }

    public int getFusionProgress(){
        int originalProgress = data.get(0);
        int maxProgress = data.get(1);
        int progressBarSize = 73;

        return originalProgress != 0 && maxProgress != 0 ? originalProgress * progressBarSize / maxProgress : 0;
    }

    //Prevents the instrument from remaining running after the chunk is unloaded
    @Override
    public boolean stillValid(Player player) {


        if (this.oeb == null){
            return true;
        }
        if (!oeb.isRemoved()){
            Level level = oeb.getLevel();
            if (level == null){
                return false;
            }
            return level.isLoaded(oeb.getBlockPos());
        }
        return false;
    }

    public void openApparatusMenu(ServerPlayer serverPlayer) {
        ServerLevel level = serverPlayer.getLevel();
        for (Player player : level.players()) {
            if (player == serverPlayer) {
                continue;
            }
            if (player.containerMenu instanceof BaseApparatusMenu) {
                if (this.giveGui((BaseApparatusMenu) player.containerMenu)) {
                    this.syncOtherContainer((BaseApparatusMenu) player.containerMenu, serverPlayer);
                    return;
                }
            }
        }
        this.syncNewContainer(serverPlayer);
    }

    public boolean giveGui(BaseApparatusMenu menu){
        if (this.oeb == null){
            return false;
        }
        return this.oeb == menu.oeb;
    }

    protected void syncOtherContainer(BaseApparatusMenu menuContainer, ServerPlayer player){}
    protected void syncNewContainer(ServerPlayer player){}

    @Override
    public NonNullList<ItemStack> getItems() {
        return super.getItems();
    }


    protected int getInventoryXOffset() {
        return 8;
    }

    public static int BASE_Y_OFFSET = 84;

    protected int getInventoryYOffset() {
        return BASE_Y_OFFSET;
    }

    private int playerInventoryStart = -1;


    //Add Player's inventory to GUI
    protected void addInventorySlots() {
        if (this.inventory != null) {
            this.addInventorySlots(this.inventory);
        }
    }
    protected void addInventorySlots(Inventory inv) {
        int yOffset = this.getInventoryYOffset();
        int xOffset = this.getInventoryXOffset();

        int start = this.slots.size();

        for (int slotY = 0; slotY < 3; slotY++) {
            for (int slotX = 0; slotX < 9; slotX++) {
                addSlot(new Slot(inv, slotX + slotY * 9 + 9, xOffset + slotX * 18, yOffset + slotY * 18));
            }
        }

        yOffset += 58;
        for (int slotY = 0; slotY < 9; slotY++) {
            addSlot(new Slot(inv, slotY, xOffset + slotY * 18, yOffset));
        }

        this.playerInventoryStart = start;
    }

    @Override
    protected Slot addSlot(Slot slotIn) {
        if (this.playerInventoryStart >= 0) {
            throw new IllegalStateException("BaseContainer: Player inventory has to be last slots. Add all slots before adding the player inventory.");
        }
        return super.addSlot(slotIn);
    }

    //Apparatus's inventory <-> Player's inventory
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {

        if (this.playerInventoryStart < 0) {

            return ItemStack.EMPTY;
        }

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            int end = this.slots.size();

            if (index < this.playerInventoryStart) {
                if (!this.moveItemStackTo(itemstack1, this.playerInventoryStart, end, true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(itemstack1, 0, this.playerInventoryStart, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    protected boolean moveItemStackTo(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex) {
        boolean ret = this.mergeItemStackRefill(stack, startIndex, endIndex, useEndIndex);
        if (!stack.isEmpty() && stack.getCount() > 0) {
            ret |= this.mergeItemStackMove(stack, startIndex, endIndex, useEndIndex);
        }
        return ret;
    }

    protected boolean mergeItemStackRefill(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex) {
        if (stack.getCount() <= 0) {
            return false;
        }

        boolean flag1 = false;
        int k = startIndex;

        if (useEndIndex) {
            k = endIndex - 1;
        }

        Slot slot;
        ItemStack itemstack1;

        if (stack.isStackable()) {
            while (stack.getCount() > 0 && (!useEndIndex && k < endIndex || useEndIndex && k >= startIndex)) {
                slot = this.slots.get(k);
                itemstack1 = slot.getItem();

                if (!itemstack1.isEmpty() && itemstack1.getItem() == stack.getItem() && ItemStack.tagMatches(stack, itemstack1) && this.canTakeItemForPickAll(stack, slot)) {
                    int l = itemstack1.getCount() + stack.getCount();
                    int limit = Math.min(stack.getMaxStackSize(), slot.getMaxStackSize(stack));

                    if (l <= limit) {
                        stack.setCount(0);
                        itemstack1.setCount(l);
                        slot.setChanged();
                        flag1 = true;
                    } else if (itemstack1.getCount() < limit) {
                        stack.shrink(limit - itemstack1.getCount());
                        itemstack1.setCount(limit);
                        slot.setChanged();
                        flag1 = true;
                    }
                }

                if (useEndIndex) {
                    --k;
                } else {
                    ++k;
                }
            }
        }
        return flag1;
    }

    protected boolean mergeItemStackMove(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex) {
        if (stack.getCount() <= 0) {
            return false;
        }

        boolean flag1 = false;
        int k;

        if (useEndIndex) {
            k = endIndex - 1;
        } else {
            k = startIndex;
        }

        while (!useEndIndex && k < endIndex || useEndIndex && k >= startIndex) {
            Slot slot = this.slots.get(k);
            ItemStack itemstack1 = slot.getItem();

            // Forge: Make sure to respect isItemValid in the slot.
            if (itemstack1.isEmpty() && slot.mayPlace(stack) && this.canTakeItemForPickAll(stack, slot)) {
                int limit = slot.getMaxStackSize(stack);
                ItemStack stack2 = stack.copy();

                if (stack2.getCount() > limit) {
                    stack2.setCount(limit);
                    stack.shrink(limit);
                } else {
                    stack.setCount(0);
                }

                slot.set(stack2);
                slot.setChanged();
                flag1 = true;

                if (stack.isEmpty()) {
                    break;
                }
            }

            if (useEndIndex) {
                --k;
            } else {
                ++k;
            }
        }

        return flag1;
    }

    @Nullable
    public static <T extends BlockEntity> T getTileEntityFromBuf(@Nullable FriendlyByteBuf buf, Class<T> type) {
        if (buf == null) {
            return null;
        }
        return DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> BlockEntityUtil.get(type, Minecraft.getInstance().level, buf.readBlockPos()).orElse(null));
    }
}
