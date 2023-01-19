package io.github.originalenhancementsmain.oeblock.apparatusblock.blockmenu;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusControllerMenu;
import io.github.originalenhancementsmain.oeblock.apparatusblock.OEMenus;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentity.NatureRealNameReconfigurableApparatusBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class NatureRealNameReconfigurableApparatusMenu extends ApparatusControllerMenu<NatureRealNameReconfigurableApparatusBlockEntity> {

    public static final ResourceLocation APPARATUS_FORMAT = OriginalEnhancementsMain.getLocationResource("nature_apparatus");

    public NatureRealNameReconfigurableApparatusMenu(int id, Inventory inventory, NatureRealNameReconfigurableApparatusBlockEntity natureApparatus){
        super(id, OEMenus.NATURE_APPARATUS_MENU.get(), inventory, natureApparatus);

        if (natureApparatus != null){
            this.addInventorySlots();
        }
    }

    public NatureRealNameReconfigurableApparatusMenu(int id, Inventory inventory, FriendlyByteBuf buf){
        this(id, inventory, getTileEntityFromBuf(buf, NatureRealNameReconfigurableApparatusBlockEntity.class));
    }
}
