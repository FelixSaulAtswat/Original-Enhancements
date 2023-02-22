package io.github.originalenhancementsmain.oeblock.apparatusblock.Components;

import io.github.originalenhancementsmain.oeblock.OEBlocks;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusControllerBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.InteractiveBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureConverterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class NatureConverterDiskBlock extends InteractiveBlock implements SimpleWaterloggedBlock {
    public boolean sideActive = false;

    public NatureConverterDiskBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context){
        return this.modelBuilder(state);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NatureConverterBlockEntity(pos, state);
    }

    @Override
    protected boolean findApparatus(BlockState state) {
        return state.is(OEBlocks.NATURE_REAL_NAME_RECONFIGURABLE_APPARATUS_BLOCK.get());
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        FluidState fluidState = level.getFluidState(context.getClickedPos());
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();

        if (direction == Direction.NORTH) {
            return this.defaultBlockState().setValue(FACING, Direction.SOUTH)
                    .setValue(STRUCTURE_COMPOSITION, findApparatus(level.getBlockState(pos.south())))
                    .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        }
        if (direction == Direction.EAST) {
            return this.defaultBlockState().setValue(FACING, Direction.WEST)
                    .setValue(STRUCTURE_COMPOSITION, findApparatus(level.getBlockState(pos.west())))
                    .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        }
        if (direction == Direction.SOUTH) {
            return this.defaultBlockState().setValue(FACING, Direction.NORTH)
                    .setValue(STRUCTURE_COMPOSITION, findApparatus(level.getBlockState(pos.north())))
                    .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        }
        if (direction == Direction.WEST) {
            return this.defaultBlockState().setValue(FACING, Direction.EAST)
                    .setValue(STRUCTURE_COMPOSITION, findApparatus(level.getBlockState(pos.east())))
                    .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        }

        return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState sideState, LevelAccessor levelAccessor, BlockPos pos, BlockPos sidePos) {
        if (state.getValue(WATERLOGGED)) levelAccessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        if (direction == state.getValue(FACING)){
            if (findApparatus(sideState)){
                this.sideActive = sideState.getValue(ApparatusControllerBlock.ACTIVE);
            }else {
                return state.setValue(STRUCTURE_COMPOSITION, false);
            }
        }
        return state;
    }

    public VoxelShape modelBuilder(BlockState state){

        VoxelShape shape = this.ORIGINAL;
        if (state.getValue(STRUCTURE_COMPOSITION)) {
            shape = this.ORIGINAL_S;
        }
        return shape;
    }

    protected VoxelShape MIN = box(4.0d, 0.0d, 4.0d, 12.0d, 0.9d, 12.0d);
    protected VoxelShape CORE = box(6.5d, 0.9d, 6.5d, 9.5d, 1.4d, 9.5d);


    protected VoxelShape ORIGINAL = Shapes.or(MIN, CORE);

    protected VoxelShape MIN_S = box(4.0d, 10.8d, 4.0d, 12.0d, 11.7d, 12.0d);
    protected VoxelShape CORE_S = box(6.5d, 11.7d, 6.5d, 9.5d, 12.2d, 9.5d);
    protected VoxelShape DOWN = box(6.5d, 8.7d, 6.5d, 9.5d, 11.7d, 9.5d);

    protected VoxelShape ORIGINAL_S = Shapes.or(MIN_S, CORE_S, DOWN);
}
