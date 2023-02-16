package io.github.originalenhancementsmain.oeblock.blocks;

import io.github.originalenhancementsmain.oeblock.OEBlocks;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusControllerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModelProviderBlock extends Block {

    public static final BooleanProperty NATURE = BooleanProperty.create("nature");

    public ModelProviderBlock(Properties properties){
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(NATURE, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NATURE);
    }

    protected VoxelShape ORIGINAL = box(0.0d, 0.0d, 0.0d, 16.0d, 16.0d, 16.0d);

    protected VoxelShape Up = box(2.0d, 31.25d - 16.3d, 2.0d, 14.0d, 32.0d- 16.3d, 14.0d);
    protected VoxelShape BOTUpNorth1 = box(6.5d, 27.5d- 16.3d, 2.0d, 9.5d, 28.25d- 16.3d, 2.75d);
    protected VoxelShape BOTUpNorth2 = box(5.75d, 28.25d- 16.3d, 2.0d, 10.25d, 29.0d- 16.3d, 2.75d);
    protected VoxelShape BOTUpNorth3 = box(5.0d, 29.0d- 16.3d, 2.0d, 11.0d, 29.75d- 16.3d, 2.75d);
    protected VoxelShape BOTUpNorth4 = box(4.25d, 29.75d- 16.3d, 2.0d, 11.75d, 30.5d- 16.3d, 2.75d);
    protected VoxelShape BOTUpNorth5 = box(3.5d, 30.5d- 16.3d, 2.0d, 12.5d, 31.25d- 16.3d, 2.75d);

    protected VoxelShape BOTUpEast1 = box(13.25d, 27.5d- 16.3d, 6.5d, 14.0d, 28.25d- 16.3d, 9.5d);
    protected VoxelShape BOTUpEast2 = box(13.25d, 28.25d- 16.3d, 5.75d, 14.0d, 29.0d- 16.3d, 10.25d);
    protected VoxelShape BOTUpEast3 = box(13.25d, 29.0d- 16.3d, 5.0d, 14.0d, 29.75d- 16.3d, 11.0d);
    protected VoxelShape BOTUpEast4 = box(13.25d, 29.75d- 16.3d, 4.25d, 14.0d, 30.5d- 16.3d, 11.75d);
    protected VoxelShape BOTUpEast5 = box(13.25d, 30.5d- 16.3d, 4.25d, 14.0d, 31.25d- 16.3d, 12.5d);

    protected VoxelShape BOTUpSouth1 = box(6.5d, 27.5d- 16.3d, 13.25d, 9.5d, 28.25d- 16.3d, 14.0d);
    protected VoxelShape BOTUpSouth2 = box(5.75d, 28.25d- 16.3d, 13.25d, 10.25d, 29.0d- 16.3d, 14.0d);
    protected VoxelShape BOTUpSouth3 = box(5.0d, 29.0d- 16.3d, 13.25d, 11.0d, 29.75d- 16.3d, 14.0d);
    protected VoxelShape BOTUpSouth4 = box(4.25d, 29.75d- 16.3d, 13.25d, 11.75d, 30.5d- 16.3d, 14.0d);
    protected VoxelShape BOTUpSouth5 = box(3.5d, 30.5d- 16.3d, 13.25d, 12.5d, 31.25d- 16.3d, 14.0d);

    protected VoxelShape BOTUpWest1 = box(2.0d, 27.5d- 16.3d, 6.5d, 2.75d, 28.25d- 16.3d, 9.5d);
    protected VoxelShape BOTUpWest2 = box(2.0d, 28.25d- 16.3d, 5.75d, 2.75d, 29.0d- 16.3d, 10.25d);
    protected VoxelShape BOTUpWest3 = box(2.0d, 29.0d- 16.3d, 5.0d, 2.75d, 29.75d- 16.3d, 11.0d);
    protected VoxelShape BOTUpWest4 = box(2.0d, 29.75d- 16.3d, 4.25d, 2.75d, 30.5d- 16.3d, 11.75d);
    protected VoxelShape BOTUpWest5 = box(2.0d, 30.5d- 16.3d, 4.25d, 2.75d, 31.25d- 16.3d, 12.5d);

    protected VoxelShape UpCore1 = box(5.75d, 30.5d- 16.3d, 6.5d, 10.25d, 31.25d- 16.3d, 9.5d);
    protected VoxelShape UpCore2 = box(10.25d, 30.5d- 16.3d, 7.25d, 11.0d, 31.25d- 16.3d, 8.75d);
    protected VoxelShape UpCore3 = box(5.0d, 30.25d- 16.3d, 7.25d, 5.75d, 31.25d- 16.3d, 8.75d);
    protected VoxelShape UpCore4 = box(6.5d, 30.5d- 16.3d, 9.5d, 9.5d, 31.25d- 16.3d, 10.25d);
    protected VoxelShape UpCore5 = box(6.5d, 30.5d- 16.3d, 5.75d, 9.5d, 31.25d- 16.3d, 6.5d);
    protected VoxelShape UpCore6 = box(7.25d, 30.5d- 16.3d, 5.0d, 8.75d, 31.25d- 16.3d, 5.75d);
    protected VoxelShape UpCore7 = box(7.25d, 30.5d- 16.3d, 10.25d, 8.75d, 31.25d- 16.3d, 11.0d);

    protected VoxelShape NATURE_APPARATUS = Shapes.or(Up, BOTUpNorth1, BOTUpNorth2, BOTUpNorth3, BOTUpNorth4, BOTUpNorth5, BOTUpEast1, BOTUpEast2, BOTUpEast3,
            BOTUpEast4, BOTUpEast5, BOTUpSouth1, BOTUpSouth2, BOTUpSouth3, BOTUpSouth4, BOTUpSouth5, BOTUpWest1, BOTUpWest2,
            BOTUpWest3, BOTUpWest4, BOTUpWest5, UpCore1, UpCore2, UpCore3, UpCore4, UpCore5, UpCore6, UpCore7);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context){
        return modelBuilder(state);
    }

    private VoxelShape modelBuilder(BlockState state){
        VoxelShape shape = this.ORIGINAL;
        if (state.getValue(NATURE)){
            shape = this.NATURE_APPARATUS;
        }
        return shape;
    }

    private boolean checkAndUpdate(LevelAccessor level, BlockPos pos, Block block, BooleanProperty property){
        BlockState state = level.getBlockState(pos);

        return state.is(block) && state.getValue(property);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState sideState, LevelAccessor levelAccessor, BlockPos pos, BlockPos sidePos){
            return state.setValue(NATURE, checkAndUpdate(levelAccessor, pos.below(), OEBlocks.NATURE_REAL_NAME_RECONFIGURABLE_APPARATUS_BLOCK.get(), ApparatusControllerBlock.STRUCTURE_COMPOSITION));
    }
}
