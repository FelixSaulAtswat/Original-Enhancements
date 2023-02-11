package io.github.originalenhancementsmain.oeblock.apparatusblock.Components;

import io.github.originalenhancementsmain.data.tags.OETags;
import io.github.originalenhancementsmain.oeblock.apparatusblock.TransferBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EnergyConductorBlock extends TransferBlock implements SimpleWaterloggedBlock {

    protected VoxelShape SHAPE_DOWN1 = box(4.0d, 0.0d, 4.0d, 12.0d, 1.0d, 12.0d);
    protected VoxelShape SHAPE_DOWN2 = box(5.0d, 1.0d, 5.0d, 11.0d, 2.0d, 11.0d);
    protected VoxelShape SHAPE_DOWN3 = box(7.0d, 2.0d, 7.0d, 9.0d, 6.0d, 9.0d);
    protected VoxelShape SHAPE_MIDDLE1 = box(5.0d, 6.0d, 5.0d, 11.0d, 12.0d, 11.0d);
    protected VoxelShape SHAPE_MIDDLE_NORTH = box(6.0d, 7.0d, 4.0d, 10.0d, 11.0d, 5.0d);
    protected VoxelShape SHAPE_MIDDLE_EAST = box(11.0d, 7.0d, 6.0d, 12.0d, 11.0d, 10.0d);
    protected VoxelShape SHAPE_MIDDLE_SOUTH = box(6.0d, 7.0d, 11.0d, 10.0d, 11.0d, 12.0d);
    protected VoxelShape SHAPE_MIDDLE_WEST = box(4.0d, 7.0d, 6.0d, 5.0d, 11.0d, 10.0d);

    protected VoxelShape ORIGINAL_SHAPE = Shapes.or(SHAPE_DOWN1, SHAPE_DOWN2, SHAPE_DOWN3, SHAPE_MIDDLE1, SHAPE_MIDDLE_NORTH, SHAPE_MIDDLE_EAST, SHAPE_MIDDLE_SOUTH, SHAPE_MIDDLE_WEST);

    protected VoxelShape SHAPE_UP1 = box(6.0D, 12.0D, 6.0D, 10.0D, 14.0D, 10.0D);
    protected VoxelShape SHAPE_UP2 = box(5.0d, 14.0d, 5.0d, 11.0d, 15.0d, 11.0d);
    protected VoxelShape SHAPE_UP3 = box(4.0d, 15.0d, 4.0d, 12.0d, 16.0d, 12.0d);

    protected VoxelShape READY_SHAPE = Shapes.or(ORIGINAL_SHAPE, SHAPE_UP1, SHAPE_UP2, SHAPE_UP3);

    public EnergyConductorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context){
        return modelBuilder(state);
    }

    private VoxelShape modelBuilder(BlockState state){
        VoxelShape shape = ORIGINAL_SHAPE;

        if (state.getValue(NORTH) && state.getValue(EAST) && state.getValue(SOUTH) && state.getValue(WEST))
            shape = READY_SHAPE;
        return shape;
    }

    public boolean hasEnergySource(BlockState state) {
        return state.is(OETags.Blocks.ENERGY_BLOCK);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos chickPos = context.getClickedPos();

        return this.defaultBlockState().setValue(NORTH, hasEnergySource(level.getBlockState(chickPos.north())))
                .setValue(EAST, hasEnergySource(level.getBlockState(chickPos.east())))
                .setValue(SOUTH, hasEnergySource(level.getBlockState(chickPos.south())))
                .setValue(WEST, hasEnergySource(level.getBlockState(chickPos.west())))
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState sideState, LevelAccessor levelAccessor, BlockPos pos, BlockPos sidePos){
        if (state.getValue(WATERLOGGED)) levelAccessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        return state.setValue(NORTH, hasEnergySource(levelAccessor.getBlockState(pos.north())))
                .setValue(EAST, hasEnergySource(levelAccessor.getBlockState(pos.east())))
                .setValue(SOUTH, hasEnergySource(levelAccessor.getBlockState(pos.south())))
                .setValue(WEST, hasEnergySource(levelAccessor.getBlockState(pos.west())));
    }
}
