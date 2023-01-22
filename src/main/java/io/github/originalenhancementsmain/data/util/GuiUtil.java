package io.github.originalenhancementsmain.data.util;

import com.mojang.blaze3d.systems.RenderSystem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.swing.plaf.basic.BasicComboBoxUI;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GuiUtil {

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
    }
}
