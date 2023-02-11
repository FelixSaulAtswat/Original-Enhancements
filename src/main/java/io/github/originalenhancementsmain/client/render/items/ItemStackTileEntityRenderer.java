package io.github.originalenhancementsmain.client.render.items;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.originalenhancementsmain.item.OEItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

public class ItemStackTileEntityRenderer extends BlockEntityWithoutLevelRenderer {

    public ItemStackTileEntityRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType camera, PoseStack pos, MultiBufferSource source, int light, int overlay){
        if (camera != ItemTransforms.TransformType.GUI){
            renderNewInventorModel(stack, OEItems.FROSTMOURNE, OEItems.FROSTMOURNE_SELF, camera, pos, source, light, overlay);
        }
    }

    private void renderNewInventorModel(ItemStack stack, RegistryObject<Item> oldModel, RegistryObject<Item> newModel, ItemTransforms.TransformType camera, PoseStack pos, MultiBufferSource source, int light, int overlay){
        if (stack.getItem() == oldModel.get()) {
            ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
            ItemStack itemStack = new ItemStack(newModel.get());
            BakedModel bakedModel = renderer.getModel(itemStack, null, null, 1);
            pos.pushPose();
            renderer.render(stack, camera, false, pos, source, light, overlay, bakedModel.applyTransform(camera, pos, false));
            pos.popPose();
        }
    }
}
