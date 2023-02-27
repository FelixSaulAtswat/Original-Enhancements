package io.github.originalenhancementsmain.client.render.effects;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import io.github.originalenhancementsmain.data.util.RenderUtil;
import io.github.originalenhancementsmain.effect.effects.SoulBurningEffect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.monster.ElderGuardian;
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
        float w = entity.getBbWidth();
        float h = entity.getBbHeight();

        if (RenderUtil.checkHasSize(entity) && RenderUtil.getSize(entity) != 1.0F) {
            pose.pushPose();
            pose.mulPose(Vector3f.XP.rotationDegrees(180.0F));
            pose.translate((-w / 2.0F) / RenderUtil.getSize(entity), -1.5f, (-w / 2.0F) / RenderUtil.getSize(entity));
            pose.scale((1.0F / RenderUtil.getSize(entity)) * w, (1.0f / RenderUtil.getSize(entity)) * h, (1.0f /RenderUtil.getSize(entity)) * w);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.SOUL_FIRE.defaultBlockState(), pose, buffer, light, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.cutout());
            pose.popPose();
        }
        if (!RenderUtil.checkHasSize(entity) || RenderUtil.getSize(entity) == 1.0F){
            pose.pushPose();
            pose.mulPose(Vector3f.XP.rotationDegrees(180.0F));
            pose.translate(-w / 2.0F, - 1.5F, -w / 2.0F);
            if (entity instanceof ElderGuardian){
                pose.translate(w / 4.0F, 0.0F, w / 4.0F);
            }
            if (entity.getType() == EntityType.PHANTOM){
                pose.translate(0.0F, 1.5F, 0.0F);
            }
            if (entity.getType() == EntityType.SALMON){
                pose.translate(0.0F, 0.0F, -w / 2.0F);
            }
            pose.scale(w, h, w);
            if (entity instanceof Squid){
                pose.scale(1.0F, 2.0F, 1.0F);
            }
            if (entity instanceof ElderGuardian){
                float size = ElderGuardian.ELDER_SIZE_SCALE;
                pose.scale(1.0F / size, 1.0F / size, 1.0F / size);
            }
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.SOUL_FIRE.defaultBlockState(), pose, buffer, light, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.cutout());
            pose.popPose();
        }
    }
}