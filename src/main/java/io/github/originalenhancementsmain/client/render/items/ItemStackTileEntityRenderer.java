package io.github.originalenhancementsmain.client.render.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;

public class ItemStackTileEntityRenderer extends BlockEntityWithoutLevelRenderer {

    public ItemStackTileEntityRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }
}
