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

    private static final ModuleScreenUtil MODULE_SLOT = new ModuleScreenUtil(176, 118, 18, 18, 256, 256);

    public NatureApparatusScreen(NatureRealNameReconfigurableApparatusMenu menu, Inventory inventory, Component name){
        super(menu, inventory, name);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY){
        GuiUtil.settings(TEXTURES);
        this.blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        if (menu.isHasCoreSlot()){
            MODULE_SLOT.show(poseStack, leftPos + 11, topPos + 89);
        }
        if (menu.isHasCrystalSlot()){
            MODULE_SLOT.show(poseStack, leftPos + 147, topPos + 89);
        }
        if (menu.canWork()){
            blit(poseStack, leftPos + 64, topPos + 33, 176, 45, 46, menu.getFusionProgress());
        }
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick){
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }
}
