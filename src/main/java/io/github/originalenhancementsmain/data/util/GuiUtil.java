package io.github.originalenhancementsmain.data.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GuiUtil {

    public static final int appImageWidth = 176;
    public static final int appImageHeight = 222;
    public static final int appLArrowX = 92;
    public static final int appRArrowX = 18;
    public static final int appArrowY = 64;


    public static void setApparatusGuiLocation(PoseStack poseStack, AbstractContainerScreen<?> screen){
        screen.blit(poseStack, (screen.width - appImageWidth) / 2, (screen.height - appImageHeight) / 2, 0, 0, appImageWidth, appImageHeight);
    }

    public static void settings(ResourceLocation texture){
        settings(texture, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void settings(ResourceLocation texture, float red, float blue, float green, float alpha){
        textureSetting(texture);
        RenderSystem.setShaderColor(red, blue, green, alpha);
    }

    public static void textureSetting(ResourceLocation texture){
        RenderSystem.setShader(GameRenderer :: getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
    }

    public static class outputSlot extends SlotItemHandler{
        public outputSlot(IItemHandler handler, int index, int xPos, int yPos){
            super(handler, index, xPos, yPos);
        }

        @Override
        public boolean mayPlace(ItemStack stack){
            return false;
        }
    }

    public static void drawApparatusName(PoseStack poseStack, AbstractContainerScreen<?> screen, Font font, Component inventoryName){
        String apparatusName = screen.getTitle().getString();
        font.draw(poseStack, apparatusName, appImageWidth / 2f - font.width(apparatusName) / 2f, -23.0F,4210752);
        font.draw(poseStack, inventoryName, 8.0F, 100.0F, 4210752);
    }
}
