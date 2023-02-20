package io.github.originalenhancementsmain.oeblock.apparatusblock;


import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

public abstract class InteractiveBlock extends BaseApparatusBlock{

    protected static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final Component DO_NOT_PLACED_IN_WATER = OriginalEnhancementMain.getTranslationWay("multiblock", "converter_disk.do_not_placed_in_water");

    public static final BooleanProperty STRUCTURE_COMPOSITION = ApparatusControllerBlock.STRUCTURE_COMPOSITION;
    public InteractiveBlock(Properties properties){
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, false).setValue(STRUCTURE_COMPOSITION, false));

    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit){
        if (player.isSuppressingBounce()){
            return InteractionResult.PASS;
        }
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof InteractiveBlockEntity) {
            if (!state.getValue(WATERLOGGED)) {
                ((DiskBlockEntity) entity).interact(player, hand);
            }else {
                player.displayClientMessage(DO_NOT_PLACED_IN_WATER, true);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, STRUCTURE_COMPOSITION);
    }
    protected boolean findApparatus(BlockState state){
        return false;
    }

}


