package io.github.originalenhancementsmain.oeblock.apparatusblock.blocks;

import io.github.originalenhancementsmain.data.util.BlockEntityGetter;
import io.github.originalenhancementsmain.data.util.UtilProvider;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusMultiBlockControllerBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentity.NatureRealNameReconfigurableApparatusBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class NatureRealNameReconfigurableApparatusBlock extends ApparatusMultiBlockControllerBlock {
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state){
        return new NatureRealNameReconfigurableApparatusBlockEntity(pos, state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving){
        Direction direction = UtilProvider.directionFromOffset(pos, fromPos);

        if (direction != Direction.DOWN){
            BlockEntityGetter.get(NatureRealNameReconfigurableApparatusBlockEntity.class, level, pos).ifPresent(nbe -> nbe.nei);
        }

    }
}
