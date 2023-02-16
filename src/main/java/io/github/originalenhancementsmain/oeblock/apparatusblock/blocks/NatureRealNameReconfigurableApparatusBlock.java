package io.github.originalenhancementsmain.oeblock.apparatusblock.blocks;

import io.github.originalenhancementsmain.data.util.BlockEntityUtil;
import io.github.originalenhancementsmain.data.util.Util;
import io.github.originalenhancementsmain.oeblock.OEBlockEntities;
import io.github.originalenhancementsmain.oeblock.OEBlocks;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusMultiBlockControllerBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureRealNameReconfigurableApparatusBlockEntity;
import io.github.originalenhancementsmain.oeblock.blocks.ModelProviderBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class NatureRealNameReconfigurableApparatusBlock extends ApparatusMultiBlockControllerBlock {

    protected VoxelShape OGLDown1 = box(3.0d, 0.0d, 3.0d, 13.0d, 1.0d, 13.0d);
    protected VoxelShape OGLDown2 = box(5.0d, 1.0d, 5.0d, 11.0d, 2.0d, 11.0d);
    protected VoxelShape ODLMain = box(2.0d, 2.0d, 2.0d, 14.0d, 14.0d, 14.0d);

    protected VoxelShape ORIGINAL_SHAPE = Shapes.or(ODLMain, OGLDown1, OGLDown2);

    protected VoxelShape core1 = box(2.0d, 2.0d, 2.0d, 14.0d, 3.0d, 14.0d);
    protected VoxelShape core2 = box(3.5d, 2.75d, 3.5d, 12.5d, 3.25d, 12.5d);
    protected VoxelShape core3 = box(5.0d, 4.25d, 5.0d, 11.0d, 5.75d, 11.0d);
    protected VoxelShape core4 = box(6.5d, 5.75d, 6.5d, 9.5d, 10.25d, 9.5d);
    protected VoxelShape core5 = box(5.75d, 10.25d, 5.75d, 10.25d, 11.0d, 10.25d);
    protected VoxelShape core6 = box(6.5d, 11.0d, 5.75d, 9.5d, 11.75d, 6.5d);
    protected VoxelShape core7 = box(5.75d, 11.0d, 5.75d, 6.5d, 11.75d, 10.25d);
    protected VoxelShape core8 = box(6.5d, 11.0d, 9.5d, 9.5d, 11.75d, 10.25d);
    protected VoxelShape core9 = box(9.5d, 11.0d, 5.75d, 10.25d, 11.75d, 10.25d);
    protected VoxelShape core10 = box(7.25d, 11.0d, 7.25d, 8.75d, 14.0d, 8.75d);



    protected VoxelShape BOOTING_SHAPE = Shapes.or(OGLDown1, OGLDown2, core1, core2, core3, core4, core5, core6, core7, core8, core9, core10);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context){
        return modelBuilder(state);
    }

    private VoxelShape modelBuilder(BlockState state){
        VoxelShape shape = ORIGINAL_SHAPE;

        if (state.getValue(STRUCTURE_COMPOSITION))
            shape = BOOTING_SHAPE;
        return shape;
    }

    public NatureRealNameReconfigurableApparatusBlock(Properties properties){
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Deprecated
    @Override
    public float getShadeBrightness(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state){
        return new NatureRealNameReconfigurableApparatusBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> wanted) {
        return pLevel.isClientSide ? null : BlockEntityUtil.castTicker(wanted, OEBlockEntities.NATURE_APPARATUS_CONTROLLER_BLOCK_ENTITY.get(), NatureRealNameReconfigurableApparatusBlockEntity::tick);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving){
        Direction direction = Util.directionFromOffset(pos, fromPos);

        if (direction != Direction.DOWN){
            BlockEntityUtil.get(NatureRealNameReconfigurableApparatusBlockEntity.class, level, pos).ifPresent(nbe -> nbe.neighborChanged(direction));
        }

    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

}
