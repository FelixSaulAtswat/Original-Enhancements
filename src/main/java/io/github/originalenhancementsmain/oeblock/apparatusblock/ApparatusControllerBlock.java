package io.github.originalenhancementsmain.oeblock.apparatusblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;


public abstract class ApparatusControllerBlock extends BaseApparatusBlock {
    public static final BooleanProperty STRUCTURE_COMPOSITION = BooleanProperty.create("structure_composition");
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    protected static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected ApparatusControllerBlock(Properties properties){
        super(properties);

        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false).setValue(STRUCTURE_COMPOSITION, false).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ACTIVE, STRUCTURE_COMPOSITION, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context){
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
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

    protected boolean canClickToOpen(BlockState state){
        return  state.getValue(STRUCTURE_COMPOSITION) && !state.getValue(WATERLOGGED);
    }

    protected boolean showStatus(BlockState state, BlockPos pos, Level level, Player player){
        return false;
    }

    @Override
    protected boolean onPlayerClick(BlockPos pos, Level level, Player player){
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() == this){
            if (canClickToOpen(state)){
                return super.onPlayerClick(pos, level, player);
            }else {
                return showStatus(state, pos, level, player);
            }
        }
        return false;
    }

}
