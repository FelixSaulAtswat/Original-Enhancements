package io.github.originalenhancementsmain.client.seen;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.data.util.GuiUtil;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockmenu.NatureRealNameReconfigurableApparatusMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class NatureApparatusScreen extends AbstractContainerScreen<NatureRealNameReconfigurableApparatusMenu> {

    private static final ResourceLocation TEXTURES = OriginalEnhancementsMain.getLocationResource("textures/gui/nature_apparatus.png");

    public NatureApparatusScreen(NatureRealNameReconfigurableApparatusMenu menu, Inventory inventory, Component name){
        super(menu, inventory, name);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY){
        GuiUtil.settings(TEXTURES);
        this.blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);

    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick){
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }
}
