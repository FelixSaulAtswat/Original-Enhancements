package io.github.originalenhancementsmain.oeblock.models;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusControllerBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureRealNameReconfigurableApparatusBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NatureApparatusModel extends AnimatedGeoModel<NatureRealNameReconfigurableApparatusBlockEntity> {

    private static final BooleanProperty STRUCTURE_COMPOSITION = ApparatusControllerBlock.STRUCTURE_COMPOSITION;

    @Override
    public ResourceLocation getModelResource(NatureRealNameReconfigurableApparatusBlockEntity model) {
        BlockState state = model.getBlockState();
        if (state.getValue(STRUCTURE_COMPOSITION)) {
            return OriginalEnhancementMain.getLocationResource("geo/nature_apparatus_booting.geo.json");
        }
        return OriginalEnhancementMain.getLocationResource("geo/nature_apparatus_original.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NatureRealNameReconfigurableApparatusBlockEntity textures) {
        BlockState state = textures.getBlockState();

        if (state.getValue(STRUCTURE_COMPOSITION)) {
            return OriginalEnhancementMain.getLocationResource("textures/block/apparatus/nature_apparatus_booting.png");
        }
        return OriginalEnhancementMain.getLocationResource("textures/block/apparatus/nature_apparatus_original.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NatureRealNameReconfigurableApparatusBlockEntity animatable) {
        BlockState state = animatable.getBlockState();

        if (state.getValue(STRUCTURE_COMPOSITION)) {
            return OriginalEnhancementMain.getLocationResource("animations/nature_apparatus_booting.animation.json");
        }
        return OriginalEnhancementMain.getLocationResource("animations/nature_apparatus_original.animation.json");
    }
}
