package io.github.originalenhancementsmain.oeblock.apparatusblock;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class BaseOEMenu <OEBlockEntity extends BlockEntity> extends AbstractContainerMenu {

    private final OEBlockEntity oeb;
    private final Inventory inventory;
    protected BaseOEMenu(int containerId , MenuType<?> menuType, Inventory inventory, OEBlockEntity oeb){
        super(menuType, containerId);
        this.oeb = oeb;
        this.inventory = inventory;
    }

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

    public void openNatureApparatusMenu(ServerPlayer serverPlayer) {
        ServerLevel level = serverPlayer.getLevel();
        for (Player player : level.players())
            if (serverPlayer == player){
                if (player.containerMenu instanceof BaseOEMenu){
                    if (this.giveGui((BaseOEMenu) player.containerMenu)){
                        this.giveOtherContainer((BaseOEMenu) player.containerMenu, serverPlayer);
                        return;
                    }
                }
            }
        this.giveNewContainer(serverPlayer);
    }

    public boolean giveGui(BaseOEMenu menu){
        if (this.oeb == null){
            return false;
        }
        return this.oeb == menu.oeb;
    }

    protected void giveOtherContainer(BaseOEMenu menuContainer, ServerPlayer player){}
    protected void giveNewContainer(ServerPlayer player){}
}
