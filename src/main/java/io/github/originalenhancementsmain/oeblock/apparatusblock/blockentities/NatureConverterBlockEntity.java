package io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities;

import io.github.originalenhancementsmain.oeblock.OEBlockEntities;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ConverterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class NatureConverterBlockEntity extends ConverterBlockEntity {
    public NatureConverterBlockEntity(BlockPos pos, BlockState state){
        super(OEBlockEntities.NATURE_CONVERTER_BLOCK_ENTITY.get(), pos, state, 1);
    }
}
