package io.github.originalenhancementsmain.client.render.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureRealNameReconfigurableApparatusBlockEntity;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blocks.NatureRealNameReconfigurableApparatusBlock;
import io.github.originalenhancementsmain.oeblock.models.NatureApparatusModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class NatureApparatusRenderer extends GeoBlockRenderer<NatureRealNameReconfigurableApparatusBlockEntity> {

    public NatureApparatusRenderer(BlockEntityRendererProvider.Context renderDispatcher){
        super(renderDispatcher, new NatureApparatusModel());
    }

    @Override
    public RenderType getRenderType(NatureRealNameReconfigurableApparatusBlockEntity animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, ResourceLocation texture) {

        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void render(BlockEntity tile, float partialTicks, PoseStack pose, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        super.render(tile, partialTicks, pose, bufferSource, packedLight, packedOverlay);
        ItemStack stack = ((NatureRealNameReconfigurableApparatusBlockEntity) tile).itemHandler.getStackInSlot(1);
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        BakedModel bakedModel = renderer.getModel(stack, tile.getLevel(), null, stack.getCount());

        if (!stack.isEmpty() && tile.getBlockState().getValue(NatureRealNameReconfigurableApparatusBlock.STRUCTURE_COMPOSITION) && progress((NatureRealNameReconfigurableApparatusBlockEntity) tile) > 13*20){

            pose.pushPose();
            pose.translate(0.5d, 1.3F + ((NatureRealNameReconfigurableApparatusBlockEntity) tile).getF() * 0.1F, 0.5d);
            pose.mulPose(Vector3f.YP.rotationDegrees(((NatureRealNameReconfigurableApparatusBlockEntity) tile).angle));
            renderer.render(stack, ItemTransforms.TransformType.GROUND, false, pose, bufferSource, packedLight, OverlayTexture.NO_OVERLAY, bakedModel);
            pose.popPose();
        }
    }

    private static int progress(NatureRealNameReconfigurableApparatusBlockEntity entity){
        return entity.AnimationProgress;
    }
}
