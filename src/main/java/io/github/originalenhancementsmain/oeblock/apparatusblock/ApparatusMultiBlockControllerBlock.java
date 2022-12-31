package io.github.originalenhancementsmain.oeblock.apparatusblock;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.data.tags.OETags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ApparatusMultiBlockControllerBlock extends ApparatusControllerBlock {

    private static final Component CAN_NOT_DETECTION_STRUCTURE = OriginalEnhancementsMain .getTranslationWay("multiblock", "apparatus.can_not_detection_structure");

    protected ApparatusMultiBlockControllerBlock(Properties properties){
        super(properties);
    }

    protected boolean isValidEnergySource(BlockState state){
        return state.is(OETags.Blocks.FULL_STRUCTURE);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context){
        BlockState state = super.getStateForPlacement(context);

        if (state != null){
            return state.setValue(STRUCTURE_COMPOSITION, isValidEnergySource(context.getLevel().getBlockState(context.getClickedPos().below())));
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    @Deprecated
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState sideState, LevelAccessor levelAccessor, BlockPos pos, BlockPos sidePos) {
        if (direction == Direction.DOWN) {
            return state.setValue(STRUCTURE_COMPOSITION, isValidEnergySource(sideState));
        }
        return state;
    }

    @Override
    protected boolean showState(BlockState state, BlockPos pos, Level world, Player player) {
        if (!world.isClientSide && !state.getValue(STRUCTURE_COMPOSITION)) {
            player.displayClientMessage(CAN_NOT_DETECTION_STRUCTURE, true);
        }
        return true;
    }

}
