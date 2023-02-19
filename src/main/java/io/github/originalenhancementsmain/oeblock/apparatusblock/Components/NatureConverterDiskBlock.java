package io.github.originalenhancementsmain.oeblock.apparatusblock.Components;

import io.github.originalenhancementsmain.oeblock.apparatusblock.ConverterDiskBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureConverterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class NatureConverterDiskBlock extends ConverterDiskBlock {

    public NatureConverterDiskBlock(Properties properties){
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NatureConverterBlockEntity(pos, state);
    }
}
