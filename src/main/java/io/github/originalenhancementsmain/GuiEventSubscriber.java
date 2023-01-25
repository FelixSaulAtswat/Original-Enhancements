package io.github.originalenhancementsmain;

import io.github.originalenhancementsmain.oeblock.apparatusblock.BaseApparatusMenu;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;

import java.util.Objects;

@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Mod.EventBusSubscriber(modid = OriginalEnhancementMain.MOD_ID)
public class GuiEventSubscriber {

    @SubscribeEvent
    static void openGuiHelper(RightClickBlock event){
        Player player = event.getPlayer();

        if (player.isSpectator()){
            BlockPos pos = event.getPos();
            Level world = event.getWorld();
            BlockState state = world.getBlockState(pos);

            if (OriginalEnhancementMain.MOD_ID.equals(Objects.requireNonNull(state.getBlock().getRegistryName()).getNamespace())){
                MenuProvider provider = state.getMenuProvider(world, pos);
                event.setCanceled(true);

                if (provider != null){
                    if (player instanceof ServerPlayer serverPlayer){
                        NetworkHooks.openGui(serverPlayer, provider, pos);

                        if (player.containerMenu instanceof BaseApparatusMenu<?> menu){
                            menu.openApparatusMenu(serverPlayer);
                        }
                    }
                    event.setCancellationResult(InteractionResult.SUCCESS);
                }
                event.setCancellationResult(InteractionResult.PASS);
            }
        }
    }
}
