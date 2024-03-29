package io.github.originalenhancementsmain.oeblock.apparatusblock;

import io.github.originalenhancementsmain.oeblock.OEBlocks;
import io.github.originalenhancementsmain.data.util.INameableMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
@SuppressWarnings("WeakerAccess")
public abstract class BaseApparatusBlock extends Block implements EntityBlock {
    protected BaseApparatusBlock(Properties properties){
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos){
        BlockEntity blockEntity = level.getBlockEntity(pos);
        return blockEntity instanceof MenuProvider ? (MenuProvider) blockEntity : null;
    }

    protected boolean onPlayerClick(BlockPos pos, Level level, Player player){

        if (!level.isClientSide()){
            MenuProvider provider = this.getMenuProvider(level.getBlockState(pos), level, pos);
            if (provider != null && player instanceof ServerPlayer serverPlayer){
                NetworkHooks.openScreen(serverPlayer, provider, pos);
                if (player.containerMenu instanceof BaseApparatusMenu<?> menu){
                    menu.openApparatusMenu(serverPlayer);
                }
            }
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result){
        if (player.isSuppressingBounce()){
            return InteractionResult.PASS;
        }
        if (!level.isClientSide){
            return this.onPlayerClick(pos, level, player) ? InteractionResult.SUCCESS : InteractionResult.PASS;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack){
        super.setPlacedBy(level, pos, state, placer, stack);

        if (stack.hasCustomHoverName()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof INameableMenuProvider provider){
                provider.setCustomName(stack.getHoverName());
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity te = worldIn.getBlockEntity(pos);
            if (te != null) {
                te.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(inventory -> dropInventoryItems(state, worldIn, pos, inventory));
                worldIn.updateNeighbourForOutputSignal(pos, this);
                if (worldIn.getBlockState(pos.above()).is(OEBlocks.MODEL_PROVIDER_BLOCK.get())){
                    worldIn.destroyBlock(pos.above(),false);
                }
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

    @SuppressWarnings("deprecation")
    @Deprecated
    @Override
    public boolean triggerEvent(BlockState state, Level worldIn, BlockPos pos, int id, int param) {
        super.triggerEvent(state, worldIn, pos, id, param);
        BlockEntity be = worldIn.getBlockEntity(pos);
        return be != null && be.triggerEvent(id, param);
    }
}
