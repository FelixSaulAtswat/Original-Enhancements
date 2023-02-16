package io.github.originalenhancementsmain.oeblock.apparatusblock;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.data.tags.OETags;
import io.github.originalenhancementsmain.oeblock.OEBlocks;
import io.github.originalenhancementsmain.oeblock.apparatusblock.Components.EnergyConductorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public abstract class ApparatusMultiBlockControllerBlock extends ApparatusControllerBlock {

    private static final Component CAN_NOT_DETECTION_STRUCTURE = OriginalEnhancementMain.getTranslationWay("multiblock", "apparatus.can_not_detection_structure");

    protected ApparatusMultiBlockControllerBlock(Properties properties){
        super(properties);
    }

    protected boolean isValidEnergySource(BlockState state){
        return state.is(OETags.Blocks.ENERGY_CRYSTAL) && state.getValue(EnergyConductorBlock.NORTH) && state.getValue(EnergyConductorBlock.EAST) && state.getValue(EnergyConductorBlock.SOUTH) && state.getValue(EnergyConductorBlock.WEST);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context){
        BlockState state = super.getStateForPlacement(context);
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState sideState = level.getBlockState(pos.below());

        if (state != null){
            context.getLevel().setBlock(context.getClickedPos().above(), isValidEnergySource(sideState) ? OEBlocks.MODEL_PROVIDER_BLOCK.get().defaultBlockState() : Blocks.AIR.defaultBlockState(), 3);
            return state.setValue(STRUCTURE_COMPOSITION, isValidEnergySource(sideState));
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState sideState, LevelAccessor levelAccessor, BlockPos pos, BlockPos sidePos) {
        if (direction == Direction.DOWN) {
            levelAccessor.setBlock(pos.above(), isValidEnergySource(sideState) ? OEBlocks.MODEL_PROVIDER_BLOCK.get().defaultBlockState() : Blocks.AIR.defaultBlockState(), 3);
            return state.setValue(STRUCTURE_COMPOSITION, isValidEnergySource(sideState));
        }
        return state;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {

        if (!level.isClientSide()){
            BlockEntity entity = level.getBlockEntity(pos);

            if (entity instanceof BaseApparatusBlockEntity){
                return super.use(state, level, pos, player, hand, result);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected boolean showStatus(BlockState state, BlockPos pos, Level world, Player player) {
        if (!world.isClientSide && !state.getValue(STRUCTURE_COMPOSITION)) {
            player.displayClientMessage(CAN_NOT_DETECTION_STRUCTURE, true);
        }
        return true;
    }

}
