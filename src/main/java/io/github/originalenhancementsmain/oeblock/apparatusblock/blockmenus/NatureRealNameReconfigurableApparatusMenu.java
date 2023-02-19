package io.github.originalenhancementsmain.oeblock.apparatusblock.blockmenus;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.data.util.GuiUtil.outputSlot;
import io.github.originalenhancementsmain.data.util.Util;
import io.github.originalenhancementsmain.oeblock.OEBlocks;
import io.github.originalenhancementsmain.oeblock.apparatusblock.BaseApparatusMenu;
import io.github.originalenhancementsmain.oeblock.apparatusblock.OEMenus;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureRealNameReconfigurableApparatusBlockEntity;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blocks.NatureRealNameReconfigurableApparatusBlock;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;

public class NatureRealNameReconfigurableApparatusMenu extends BaseApparatusMenu<NatureRealNameReconfigurableApparatusBlockEntity> {
    @Getter
    public boolean hasCoreSlot = false;
    @Getter
    public boolean hasCrystalSlot = false;

    public NatureRealNameReconfigurableApparatusMenu(int id, Inventory inventory, NatureRealNameReconfigurableApparatusBlockEntity natureApparatus, ContainerData data){
        super(id, OEMenus.NATURE_APPARATUS_MENU.get(), inventory, natureApparatus, data);

        if (natureApparatus != null){
            checkContainerSize(inventory, 3);

                hasCoreSlot = Util.getSideBlockEntity(Util.LEFT, natureApparatus) != null;
                hasCrystalSlot = Util.getSideBlockEntity(Util.RIGHT, natureApparatus) != null;
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
