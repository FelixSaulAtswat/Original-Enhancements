package io.github.originalenhancementsmain.oeblock.apparatusblock;

import io.github.originalenhancementsmain.data.tags.OETags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public abstract class TransferBlock extends Block implements SimpleWaterloggedBlock{

    protected static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final BooleanProperty NORTH = PipeBlock.NORTH;
    protected static final BooleanProperty EAST = PipeBlock.EAST;
    protected static final BooleanProperty SOUTH = PipeBlock.SOUTH;
    protected static final BooleanProperty WEST = PipeBlock.WEST;

    public TransferBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, NORTH, EAST, SOUTH, WEST, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos chickPos = context.getClickedPos();
        FluidState fluidState = level.getFluidState(context.getClickedPos());


        return this.defaultBlockState().setValue(NORTH, hasEnergySource(level.getBlockState(chickPos.north())))
                .setValue(EAST, hasEnergySource(level.getBlockState(chickPos.east())))
                .setValue(SOUTH, hasEnergySource(level.getBlockState(chickPos.south())))
                .setValue(WEST, hasEnergySource(level.getBlockState(chickPos.west())))
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @SuppressWarnings("deprecation")
    @Deprecated
    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Deprecated
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)));
    }

    protected boolean hasEnergySource(BlockState state) {
        return false;
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
