package io.github.originalenhancementsmain.oeblock.apparatusblock;


import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;

public abstract class ConverterDiskBlock extends TransferBlock implements EntityBlock {

    private static final Component DO_NOT_PLACED_IN_WATER = OriginalEnhancementMain.getTranslationWay("multiblock", "converter_disk.do_not_placed_in_water");

    public ConverterDiskBlock(Properties properties){
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit){
        if (player.isSuppressingBounce()){
            return InteractionResult.PASS;
        }


        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof ConverterBlockEntity) {
            if (!state.getValue(WATERLOGGED)) {
                ((ConverterBlockEntity) entity).interact(player, hand);
            }else {
                player.displayClientMessage(DO_NOT_PLACED_IN_WATER, true);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity te = worldIn.getBlockEntity(pos);
            if (te != null) {
                te.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(inventory -> dropInventoryItems(state, worldIn, pos, inventory));
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }
        }

        super.onRemove(state, worldIn, pos, newState, isMoving);
    }

    protected void dropInventoryItems(BlockState state, Level level, BlockPos pos, IItemHandler inventory) {
        dropInventoryItems(level, pos, inventory);
    }

    public static void dropInventoryItems(Level level, BlockPos pos, IItemHandler inventory) {
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        for(int i = 0; i < inventory.getSlots(); ++i) {
            Containers.dropItemStack(level, x, y, z, inventory.getStackInSlot(i));
        }
    }
}


