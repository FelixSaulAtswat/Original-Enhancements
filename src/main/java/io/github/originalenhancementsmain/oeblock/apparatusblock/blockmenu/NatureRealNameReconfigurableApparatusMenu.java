package io.github.originalenhancementsmain.oeblock.apparatusblock.blockmenu;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.data.util.GuiUtil.outputSlot;
import io.github.originalenhancementsmain.oeblock.apparatusblock.BaseApparatusMenu;
import io.github.originalenhancementsmain.oeblock.apparatusblock.OEMenus;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentity.NatureRealNameReconfigurableApparatusBlockEntity;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class NatureRealNameReconfigurableApparatusMenu extends BaseApparatusMenu<NatureRealNameReconfigurableApparatusBlockEntity> {

    public static final ResourceLocation APPARATUS_FORMAT = OriginalEnhancementMain.getLocationResource("nature_apparatus");

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
                if (level.getBlockState(west).is(Blocks.DIRT)) {
                    hasCoreSlot = natureApparatus.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).filter(handler -> {
                        this.addSlot(new SlotItemHandler(handler, 0, 11, 89));
                        return true;
                    }).isPresent();
                }
                if (level.getBlockState(east).is(Blocks.STONE)) {
                    hasCrystalSlot = natureApparatus.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).filter(handler -> {
                        this.addSlot(new SlotItemHandler(handler, 1, 147, 89));
                        return true;
                    }).isPresent();
                }
                natureApparatus.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                    this.addSlot(new SlotItemHandler(handler, 2, 89, 108));

                    this.addSlot(new outputSlot(handler, 3, 63, 15));
                    this.addSlot(new outputSlot(handler, 4, 95, 15));
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
