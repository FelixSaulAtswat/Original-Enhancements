package io.github.originalenhancementsmain.client.render.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.originalenhancementsmain.oeblock.apparatusblock.Components.NatureConverterDiskBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.InteractiveBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureConverterBlockEntity;
import io.github.originalenhancementsmain.oeblock.models.NatureConverterDiskModel;
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
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class NatureConverterDiskRender extends GeoBlockRenderer<NatureConverterBlockEntity> {

    private double range = 0.0d;
    private float volume = 1000.0f;

    public NatureConverterDiskRender(BlockEntityRendererProvider.Context rendererProvider) {
        super(rendererProvider, new NatureConverterDiskModel());
    }

    @Override
    public RenderType getRenderType(NatureConverterBlockEntity animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void render(BlockEntity tile, float partialTicks, PoseStack pose, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        super.render(tile, partialTicks, pose, bufferSource, packedLight, packedOverlay);
        ItemStack stack = ((NatureConverterBlockEntity) tile).getItem(0);
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        BakedModel bakedModel = renderer.getModel(stack, tile.getLevel(), null, 1);
        BlockState state = tile.getBlockState();
        NatureConverterDiskBlock block = (NatureConverterDiskBlock) state.getBlock();

        if (!stack.isEmpty()) {
            if (state.getValue(InteractiveBlock.STRUCTURE_COMPOSITION)) {
                if (!block.sideActive) {

                    pose.pushPose();
                    pose.translate(0.5d, 1.3F + ((NatureConverterBlockEntity) tile).getF() * 0.1F, 0.5d);
                    renderer.render(stack, ItemTransforms.TransformType.GROUND, false, pose, bufferSource, packedLight, OverlayTexture.NO_OVERLAY, bakedModel);
                    pose.popPose();
                }else {
                    pose.pushPose();
                    pose.translate(0.5d, 1.3f - 0.4f/((NatureConverterBlockEntity) tile).volume, 0.5d);
                    pose.scale(((NatureConverterBlockEntity) tile).volume/1000, ((NatureConverterBlockEntity) tile).volume/1000, ((NatureConverterBlockEntity) tile).volume/1000);
                    renderer.render(stack, ItemTransforms.TransformType.GROUND, false, pose, bufferSource, packedLight, OverlayTexture.NO_OVERLAY, bakedModel);
                    pose.popPose();
                }

            }else {
                pose.pushPose();
                pose.translate(0.5d, 0.5d, 0.5d);
                renderer.render(stack, ItemTransforms.TransformType.GROUND, false, pose, bufferSource, packedLight, OverlayTexture.NO_OVERLAY, bakedModel);
                pose.popPose();
            }
        }
    }
}