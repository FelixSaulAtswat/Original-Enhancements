package io.github.originalenhancementsmain.client.render.effects;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import io.github.originalenhancementsmain.data.util.RenderUtil;
import io.github.originalenhancementsmain.effect.OEEffects;
import io.github.originalenhancementsmain.effect.OEMobEffect;
import io.github.originalenhancementsmain.effect.effects.TheEndEffect;
import io.github.originalenhancementsmain.item.OEItems;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureRealNameReconfigurableApparatusBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class TheEndRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public TheEndRenderLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack pose, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (Objects.requireNonNull(entity.getAttribute(Attributes.MOVEMENT_SPEED)).getModifier(TheEndEffect.THEEND_UUID) == null) {
            return;
        }
        if (entity.getHealth() <= 0.0f){
            return;
        }
        ItemStack stack = new ItemStack(OEItems.THE_END_EYE.get());
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        BakedModel model = renderer.getModel(stack, null, null, 1);

        float dw = entity.getBbWidth();
        float dh = entity.getBbHeight();

        pose.pushPose();
        if (RenderUtil.checkHasSize(entity)){
            pose.translate(0, -dh/(2 * RenderUtil.getSize(entity)), dw / RenderUtil.getSize(entity));
        }else {
            pose.translate(0, -dh/2, dw);
        }
        pose.scale(2, 2, 2);
        pose.mulPose(Vector3f.XP.rotationDegrees(30));
        renderer.render(stack, ItemTransforms.TransformType.GROUND, false, pose, buffer, light, OverlayTexture.NO_OVERLAY, model);
        pose.popPose();
    }
}
