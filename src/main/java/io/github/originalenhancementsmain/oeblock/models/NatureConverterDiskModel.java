package io.github.originalenhancementsmain.oeblock.models;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusControllerBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureConverterBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NatureConverterDiskModel extends AnimatedGeoModel<NatureConverterBlockEntity> {

    private static final BooleanProperty STRUCTURE_COMPOSITION = ApparatusControllerBlock.STRUCTURE_COMPOSITION;

    @Override
    public ResourceLocation getModelResource(NatureConverterBlockEntity model) {

        if (model.getBlockState().getValue(STRUCTURE_COMPOSITION)){
            return OriginalEnhancementMain.getLocationResource("geo/nature_converter_disk_in_structure.geo.json");
        }
        return OriginalEnhancementMain.getLocationResource("geo/nature_converter_disk_original.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NatureConverterBlockEntity texture) {

        if (texture.getBlockState().getValue(STRUCTURE_COMPOSITION)){
            return OriginalEnhancementMain.getLocationResource("textures/block/apparatus/nature_converter_disk_in_structure.png");
        }
        return OriginalEnhancementMain.getLocationResource("textures/block/apparatus/nature_converter_disk_original.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NatureConverterBlockEntity animatable) {

        if (animatable.getBlockState().getValue(STRUCTURE_COMPOSITION)){
            return OriginalEnhancementMain.getLocationResource("animations/nature_converter_disk_in_structure.animation.json");
        }
        return OriginalEnhancementMain.getLocationResource("animations/nature_converter_disk_original.animation.json");
    }
}
