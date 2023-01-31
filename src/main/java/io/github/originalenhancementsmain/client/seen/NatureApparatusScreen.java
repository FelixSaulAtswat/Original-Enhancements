package io.github.originalenhancementsmain.client.seen;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.data.util.GuiUtil;
import io.github.originalenhancementsmain.item.OEItems;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockmenu.NatureRealNameReconfigurableApparatusMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class NatureApparatusScreen extends BaseApparatusScreen<NatureRealNameReconfigurableApparatusMenu> {

    private static final ResourceLocation TEXTURES = OriginalEnhancementMain.getLocationResource("textures/gui/nature_apparatus.png");

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
            blit(poseStack, x + 64, y + 33, 176, 0, 46, menu.getFusionProgress());
        }
        if (menu.isHasCoreSlot()){
            ITEM_SLOT1.draw(poseStack, x + 11, y + 89);
            itemRenderer.renderAndDecorateItem(new ItemStack(OEItems.RIGHT_ARROW_FOREST.get()), x + rightArrowPosX + 24, y + arrowPosY + 26);
        }
        GuiUtil.settings(TEXTURES);
        if (menu.isHasCrystalSlot()){
            ITEM_SLOT2.draw(poseStack, x + 147, y + 89);
            itemRenderer.renderAndDecorateItem( new ItemStack(OEItems.LEFT_ARROW_FOREST.get()), x + leftArrowPosX + 24, y + arrowPosY + 27);
        }
    }

}
