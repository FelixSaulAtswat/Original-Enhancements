package io.github.originalenhancementsmain.oeblock.apparatusblock.Components;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class EnergyConductorBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty READY = BooleanProperty.create("ready");
    private   boolean N = false;
    private   boolean S = false;
    private   boolean W = false;
    private   boolean E = false;

    public EnergyConductorBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(READY, false));

    }

    protected boolean hasEnoughEnergy(){
        return this.E && this.N && this.S && this.W;
    }
    protected boolean hasEnergySource(BlockState state){
        return state.is(BlockTags.BEACON_BASE_BLOCKS);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, READY);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState sideState, LevelAccessor levelAccessor, BlockPos pos, BlockPos sidePos) {

        if (direction != Direction.DOWN) {
            if (direction == Direction.NORTH) {
                N = hasEnergySource(sideState);
            }
            if (direction == Direction.SOUTH) {
                S = hasEnergySource(sideState);
            }
            if (direction == Direction.WEST) {
                W = hasEnergySource(sideState);
            }
            if (direction == Direction.EAST) {
                E = hasEnergySource(sideState);
            }
            return state.setValue(READY, hasEnoughEnergy());
        }
        return state;
    }
}
