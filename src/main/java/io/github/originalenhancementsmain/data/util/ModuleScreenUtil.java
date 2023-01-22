package io.github.originalenhancementsmain.data.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;

public class ModuleScreenUtil {

    public final int x;
    public final int y;
    public final int w;
    public final int h;

    public int textureW;
    public int textureH;

    public static int originalTextureW = 256;
    public static int originalTextureH = 256;

    public ModuleScreenUtil(int x, int y, int w, int h, int textureW, int textureH){
        this(x, y, w, h);

        this.getTextureOnGui(textureW, textureH);

        originalTextureH = textureH;
        originalTextureW = textureW;
    }

    public ModuleScreenUtil(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.getTextureOnGui(originalTextureW, originalTextureH);
    }

    public void getTextureOnGui(int w, int h){
        this.textureW = w;
        this.textureH = h;
    }

    public int show(PoseStack poseStack, int poseX, int poseY){
        Screen.blit(poseStack, poseX, poseY, this.x, this.y, this.w, this.h, this.textureW, this.textureH);
        return this.w;
    }
}
