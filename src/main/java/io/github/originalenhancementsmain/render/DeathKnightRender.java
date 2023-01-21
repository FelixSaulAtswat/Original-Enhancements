package io.github.originalenhancementsmain.render;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.client.model.entity.DeathKnightModel;
import io.github.originalenhancementsmain.entity.monster.DeathKnight;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DeathKnightRender extends MobRenderer<DeathKnight, DeathKnightModel<DeathKnight>> {
        //注意这个路径，要和上面model文件中的路径保持一致
        public static final ResourceLocation TEXTURE = new ResourceLocation(OriginalEnhancementMain.MOD_ID, "textures/entity/death_knight.png");

    public DeathKnightRender(EntityRendererProvider.Context manager) {
        super(manager, new DeathKnightModel<>(manager.bakeLayer(DeathKnightModel.LAYER_LOCATION)), 0.7F);
    }

        @Override
        public ResourceLocation getTextureLocation (DeathKnight p_110775_1_){
        return TEXTURE;
    }

}
