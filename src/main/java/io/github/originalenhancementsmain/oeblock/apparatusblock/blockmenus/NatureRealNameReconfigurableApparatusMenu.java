package io.github.originalenhancementsmain.oeblock.apparatusblock.blockmenus;

import io.github.originalenhancementsmain.data.util.BlockEntityUtil;
import io.github.originalenhancementsmain.data.util.GuiUtil.outputSlot;
import io.github.originalenhancementsmain.data.util.Util;
import io.github.originalenhancementsmain.oeblock.apparatusblock.BaseApparatusMenu;
import io.github.originalenhancementsmain.oeblock.apparatusblock.InteractiveBlockEntity;
import io.github.originalenhancementsmain.oeblock.apparatusblock.OEMenus;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureRealNameReconfigurableApparatusBlockEntity;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

import static io.github.originalenhancementsmain.oeblock.apparatusblock.InteractiveBlock.STRUCTURE_COMPOSITION;

public class NatureRealNameReconfigurableApparatusMenu extends BaseApparatusMenu<NatureRealNameReconfigurableApparatusBlockEntity> {
    @Getter
    public boolean hasCoreSlot = false;
    @Getter
    public boolean hasCrystalSlot = false;

    public NatureRealNameReconfigurableApparatusMenu(int id, Inventory inventory, NatureRealNameReconfigurableApparatusBlockEntity natureApparatus, ContainerData data) {
        super(id, OEMenus.NATURE_APPARATUS_MENU.get(), inventory, natureApparatus, data);

        if (natureApparatus != null) {
            checkContainerSize(inventory, 3);

            BlockEntity leftEntity = Util.getSideBlockEntity(Util.LEFT, natureApparatus);
            BlockEntity rightEntity = Util.getSideBlockEntity(Util.RIGHT, natureApparatus);
            hasCoreSlot = leftEntity instanceof InteractiveBlockEntity && BlockEntityUtil.checkFaceBlock(leftEntity, natureApparatus) && BlockEntityUtil.checkState(leftEntity, STRUCTURE_COMPOSITION);
            hasCrystalSlot = rightEntity instanceof InteractiveBlockEntity && BlockEntityUtil.checkFaceBlock(rightEntity, natureApparatus) && BlockEntityUtil.checkState(rightEntity, STRUCTURE_COMPOSITION);


            natureApparatus.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                this.addSlot(new SlotItemHandler(handler, 0, 80, 81));

                this.addSlot(new outputSlot(handler, 1, 64, -12));
                this.addSlot(new outputSlot(handler, 2, 96, -12));
            });


            this.addInventorySlots();
            this.addDataSlots(data);
        }
    }



    public NatureRealNameReconfigurableApparatusMenu(int id, Inventory inventory, FriendlyByteBuf buf){
        this(id, inventory, getTileEntityFromBuf(buf, NatureRealNameReconfigurableApparatusBlockEntity.class), new SimpleContainerData(2));
    }
}
