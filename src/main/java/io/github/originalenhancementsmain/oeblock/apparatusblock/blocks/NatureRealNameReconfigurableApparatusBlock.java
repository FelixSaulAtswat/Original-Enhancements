package io.github.originalenhancementsmain.oeblock.apparatusblock.blocks;

import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusMultiBlockControllerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public abstract class NatureRealNameReconfigurableApparatusBlock extends ApparatusMultiBlockControllerBlock {
    public NatureRealNameReconfigurableApparatusBlock(BlockBehaviour.Properties properties){
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Deprecated
    @Override
    public float getShadeBrightness(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }


}
