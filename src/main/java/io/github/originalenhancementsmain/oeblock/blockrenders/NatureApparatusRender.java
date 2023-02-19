package io.github.originalenhancementsmain.oeblock.blockrenders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureRealNameReconfigurableApparatusBlockEntity;
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

public class NatureApparatusRender extends GeoBlockRenderer<NatureRealNameReconfigurableApparatusBlockEntity> {

    private double range = 0.0d;
    private float angle = 0.0f;

    public NatureApparatusRender(BlockEntityRendererProvider.Context renderDispatcher){
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
        BakedModel bakedModel = renderer.getModel(stack, animatable.getLevel(), null, 1);

        if (!stack.isEmpty()) {
            angle++;
            range++;
            float f = (float) Math.sin(0.05d * range);
            if (0.05d * range > Math.PI * 100000.0d){
                range = 0.0d;
            }
            if (angle >= 360.0f){
                angle = 0.0f;
            }

            pose.pushPose();
            pose.translate(0.5d, 1.3F + f * 0.1F, 0.5d);
            pose.mulPose(Vector3f.YP.rotationDegrees(angle));
            renderer.render(stack, ItemTransforms.TransformType.GROUND, false, pose, bufferSource, packedLight, OverlayTexture.NO_OVERLAY, bakedModel);
            pose.popPose();
        }
    }
}
