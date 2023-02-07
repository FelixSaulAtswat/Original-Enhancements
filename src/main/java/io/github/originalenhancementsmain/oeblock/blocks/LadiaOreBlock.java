package io.github.originalenhancementsmain.oeblock.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


public class LadiaOreBlock extends Block {

    private final UniformInt xp;
    public LadiaOreBlock(Properties properties, UniformInt uniformInt) {
        super(properties);
        this.xp = uniformInt;
    }

    @Override
    public int getExpDrop(BlockState state, LevelReader reader, RandomSource source, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.xp.sample(source) : 0;
    }
}
