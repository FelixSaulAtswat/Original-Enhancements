package io.github.originalenhancementsmain.client.seen;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.client.component.ApparatusGuiComponents;
import io.github.originalenhancementsmain.data.util.GuiUtil;
import io.github.originalenhancementsmain.data.util.ScreenComponentUtil;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockmenu.NatureRealNameReconfigurableApparatusMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class NatureApparatusScreen extends AbstractContainerScreen<NatureRealNameReconfigurableApparatusMenu> {

    private static final ResourceLocation TEXTURES = OriginalEnhancementMain.getLocationResource("textures/gui/nature_apparatus.png");

    private static final ScreenComponentUtil ITEM_SLOT1 = new ScreenComponentUtil(176, 73, 18, 18, 256, 256);
    private static final ScreenComponentUtil ITEM_SLOT2 = new ScreenComponentUtil(194, 73, 18, 18, 156, 256);

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
            blit(poseStack, x + 64, y - 41, 176, 45, 46, menu.getFusionProgress());
        }
        if (menu.isHasCoreSlot()){
            ITEM_SLOT1.draw(poseStack, x + 11, y + 88);
            GuiUtil.drawAppComponents(poseStack, ApparatusGuiComponents.forestLeftArrow, GuiUtil.appLArrowX, GuiUtil.appArrowY);
        }
        if (menu.isHasCrystalSlot()){
            ITEM_SLOT2.draw(poseStack, x + 147, y + 88);
            GuiUtil.drawAppComponents(poseStack, ApparatusGuiComponents.forestRightArrow, x + GuiUtil.appRArrowX, x + GuiUtil.appArrowY);
        }
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick){
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY){
        GuiUtil.drawApparatusName(poseStack, this, this.font, this.playerInventoryTitle);
    }
}
