package io.github.originalenhancementsmain.client.bakemodel;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class OEBakeModel implements BakedModel {

    private final BakedModel oldModel;

    public OEBakeModel(BakedModel oldModel){
        this.oldModel = oldModel;
    }
    @SuppressWarnings("deprecation")
    @Override
    public  List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random random) {
        return this.oldModel.getQuads(state, side, random);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return this.oldModel.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.oldModel.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return this.oldModel.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public  TextureAtlasSprite getParticleIcon() {
        return this.oldModel.getParticleIcon();
    }

    @Override
    public ItemOverrides getOverrides() {
        return this.oldModel.getOverrides();
    }

    @Override
    public boolean doesHandlePerspectives()
    {
        return true;
    }

    @Override
    public BakedModel handlePerspective(ItemTransforms.TransformType camera, PoseStack poseStack){
        if (camera == ItemTransforms.TransformType.GUI){
            return this;
        }
        return oldModel.handlePerspective(camera, poseStack);
    }
}
