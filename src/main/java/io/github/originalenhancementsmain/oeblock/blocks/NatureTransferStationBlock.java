package io.github.originalenhancementsmain.oeblock.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NatureTransferStationBlock extends FacingBlock{

    protected VoxelShape SHAPE_MIDDLE1 = box(5.0d, 0.0d, 5.0d, 11.0d, 6.0d, 11.0d);
    protected VoxelShape SHAPE_MIDDLE_NORTH = box(6.0d, 1.0d, 4.0d, 10.0d, 5.0d, 5.0d);
    protected VoxelShape SHAPE_MIDDLE_EAST = box(11.0d, 1.0d, 6.0d, 12.0d, 5.0d, 10.0d);
    protected VoxelShape SHAPE_MIDDLE_SOUTH = box(6.0d, 1.0d, 11.0d, 10.0d, 5.0d, 12.0d);
    protected VoxelShape SHAPE_MIDDLE_WEST = box(4.0d, 1.0d, 6.0d, 5.0d, 5.0d, 10.0d);

    public NatureTransferStationBlock(Properties properties){
        super(properties);
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context){
        return Shapes.or(SHAPE_MIDDLE1, SHAPE_MIDDLE_SOUTH, SHAPE_MIDDLE_WEST, SHAPE_MIDDLE_EAST, SHAPE_MIDDLE_NORTH);
    }

}
