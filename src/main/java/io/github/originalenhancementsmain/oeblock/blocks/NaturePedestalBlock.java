package io.github.originalenhancementsmain.oeblock.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NaturePedestalBlock extends FacingBlock{

    protected VoxelShape pedestal1 = box(3.5d, 0.0d, 3.5d, 12.5d, 1.5d, 12.5d);
    protected VoxelShape pedestal2 = box(5.0d, 1.5d, 5.0d, 11.0d, 3.0d, 11.0d);

    public NaturePedestalBlock(Properties properties){
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context){
        return Shapes.or(pedestal1, pedestal2);
    }
}
