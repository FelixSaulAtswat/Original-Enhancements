package io.github.originalenhancementsmain.oeblock.blockrenders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureRealNameReconfigurableApparatusBlockEntity;
import io.github.originalenhancementsmain.oeblock.models.NatureApparatusModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class NatureApparatusRender extends GeoBlockRenderer<NatureRealNameReconfigurableApparatusBlockEntity> {

    public NatureApparatusRender(BlockEntityRendererProvider.Context renderDispatcher){
        super(renderDispatcher, new NatureApparatusModel());
    }

    @Override
    public RenderType getRenderType(NatureRealNameReconfigurableApparatusBlockEntity animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, ResourceLocation texture) {

        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
