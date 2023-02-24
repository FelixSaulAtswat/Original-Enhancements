package io.github.originalenhancementsmain.client.render.effects;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import io.github.originalenhancementsmain.effect.effects.SoulBurningEffect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.data.ModelData;

import java.util.Objects;

public class SoulBarningRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public SoulBarningRenderLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack pose, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (Objects.requireNonNull(entity.getAttribute(Attributes.MAX_HEALTH)).getModifier(SoulBurningEffect.SOUL_BURNING_UUID) == null) {
            return;
        }
        pose.pushPose();
        float dx = (entity.getBbWidth() * 2.0F - entity.getBbWidth()) * 0.1F;
        float dy = 2.0F - entity.getBbHeight();
        float dz = (entity.getBbWidth() * 2.0F - entity.getBbWidth()) * 0.1F;
        pose.translate(dx, dy, dz);
        pose.scale(1.0f, 5.0f*entity.getBbHeight()/4.0f, 1.0f);
        pose.mulPose(Vector3f.XP.rotationDegrees(180));
        pose.translate(-0.6F, -0.6F, -0.4F);
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.SOUL_FIRE.defaultBlockState(), pose, buffer, light, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.cutout());
        pose.popPose();
    }
}