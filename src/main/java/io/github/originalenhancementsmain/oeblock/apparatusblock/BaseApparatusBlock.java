package io.github.originalenhancementsmain.oeblock.apparatusblock;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public abstract class BaseApparatusBlock extends Block implements EntityBlock {
    protected BaseApparatusBlock(BlockBehaviour.Properties properties){
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos){
        BlockEntity blockEntity = level.getBlockEntity(pos);
        return blockEntity instanceof MenuProvider ? (MenuProvider) blockEntity : null;
    }

    protected boolean onPlayerClick(BlockPos pos, Level level, Player player){

        if (!level.isClientSide){
            MenuProvider provider = this.getMenuProvider(level.getBlockState(pos), level, pos);
            if (provider != null && player instanceof ServerPlayer serverPlayer){
                NetworkHooks.openGui(serverPlayer, provider, pos);
                if (player.containerMenu instanceof BaseOEMenu menu){
                    menu.openNatureApparatusMenu(serverPlayer);
                }
            }
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result){
        if (!player.isSuppressingBounce()){
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

        }
    }
}
