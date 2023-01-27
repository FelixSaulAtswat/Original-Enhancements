package io.github.originalenhancementsmain.client.seen;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.data.util.GuiUtil;
import io.github.originalenhancementsmain.data.util.ModuleScreenUtil;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockmenu.NatureRealNameReconfigurableApparatusMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class NatureApparatusScreen extends AbstractContainerScreen<NatureRealNameReconfigurableApparatusMenu> {

    private static final ResourceLocation TEXTURES = OriginalEnhancementMain.getLocationResource("textures/gui/nature_apparatus.png");

    private static final ModuleScreenUtil MODULE_SLOT = new ModuleScreenUtil(176, 118, 18, 19, 256, 256);

    public NatureApparatusScreen(NatureRealNameReconfigurableApparatusMenu menu, Inventory inventory, Component name){
        super(menu, inventory, name);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY){
        GuiUtil.settings(TEXTURES);
        GuiUtil.setApparatusGuiLocation(poseStack, this);

        int x = (width - GuiUtil.appImageWidth) / 2;
        int y = (height - GuiUtil.appImageHeight) / 2;

        if (menu.canWork()){
            blit(poseStack, x + 64, y + 33, 176, 45, 46, menu.getFusionProgress());
        }
        if (menu.isHasCoreSlot()){
            MODULE_SLOT.show(poseStack, x + 11, y + 88);
        }
        if (menu.isHasCrystalSlot()){
            MODULE_SLOT.show(poseStack, x + 147, y + 88);
        }
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick){
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }
}
