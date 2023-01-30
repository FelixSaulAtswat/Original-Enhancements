package io.github.originalenhancementsmain.client.seen;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.originalenhancementsmain.data.util.GuiUtil;
import io.github.originalenhancementsmain.data.util.ScreenComponentUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class BaseApparatusScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

    protected static final ScreenComponentUtil ITEM_SLOT1 = new ScreenComponentUtil(176, 73, 18, 18, 256, 256);
    protected static final ScreenComponentUtil ITEM_SLOT2 = new ScreenComponentUtil(194, 73, 18, 18, 256, 256);
    protected static final int rightArrowPosX = 18;
    protected static final int leftArrowPosX = 92;
    protected static final int arrowPosY = 64;

    public BaseApparatusScreen(T menu, Inventory inventory, Component name) {
        super(menu, inventory, name);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {}

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
