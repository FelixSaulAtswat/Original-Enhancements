package io.github.originalenhancementsmain.oeblock.apparatusblock.blockmenus;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.data.util.GuiUtil.outputSlot;
import io.github.originalenhancementsmain.oeblock.apparatusblock.BaseApparatusMenu;
import io.github.originalenhancementsmain.oeblock.apparatusblock.OEMenus;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureRealNameReconfigurableApparatusBlockEntity;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class NatureRealNameReconfigurableApparatusMenu extends BaseApparatusMenu<NatureRealNameReconfigurableApparatusBlockEntity> {

    @Getter
    public boolean hasCoreSlot = false;
    @Getter
    public boolean hasCrystalSlot = false;

    public NatureRealNameReconfigurableApparatusMenu(int id, Inventory inventory, NatureRealNameReconfigurableApparatusBlockEntity natureApparatus, ContainerData data){
        super(id, OEMenus.NATURE_APPARATUS_MENU.get(), inventory, natureApparatus, data);

        if (natureApparatus != null){
            checkContainerSize(inventory, 5);
            Level level = natureApparatus.getLevel();
            BlockPos west = natureApparatus.getBlockPos().west();
            BlockPos east = natureApparatus.getBlockPos().east();

            if (level != null){
                if (level.getBlockState(east).is(Blocks.DIRT)) {
                    hasCoreSlot = natureApparatus.getCapability(ForgeCapabilities.ITEM_HANDLER).filter(handler -> {
                        this.addSlot(new SlotItemHandler(handler, 0, 12, 62));
                        return true;
                    }).isPresent();
                }
                if (level.getBlockState(west).is(Blocks.STONE)) {
                    hasCrystalSlot = natureApparatus.getCapability(ForgeCapabilities.ITEM_HANDLER).filter(handler -> {
                        this.addSlot(new SlotItemHandler(handler, 1, 148, 62));
                        return true;
                    }).isPresent();
                }
                natureApparatus.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                    this.addSlot(new SlotItemHandler(handler, 2, 80, 81));

                    this.addSlot(new outputSlot(handler, 3, 64, -12));
                    this.addSlot(new outputSlot(handler, 4, 96, -12));
                });
            }

            this.addInventorySlots();
            this.addDataSlots(data);
        }
    }

    public NatureRealNameReconfigurableApparatusMenu(int id, Inventory inventory, FriendlyByteBuf buf){
        this(id, inventory, getTileEntityFromBuf(buf, NatureRealNameReconfigurableApparatusBlockEntity.class), new SimpleContainerData(2));
    }
}
