package io.github.originalenhancementsmain;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = OriginalEnhancementMain.MOD_ID)
public class PlayerAttributeModificationEvent {

    private static final ResourceLocation advancement = new ResourceLocation("minecraft:end/kill_dragon");

    @SubscribeEvent
    public static void checkResurrectedPlayersHealthAndUp(PlayerEvent.PlayerRespawnEvent event) {
        Player pyr = event.getEntity();

        if (!pyr.level.isClientSide) {
            if (pyr instanceof ServerPlayer) {

                MinecraftServer server = pyr.getServer();
                if (pyr.getMaxHealth() < 300 && ((ServerPlayer) pyr).getAdvancements().getOrStartProgress(server.getAdvancements().getAdvancement(advancement)).isDone()){
                    Objects.requireNonNull(pyr.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(300.0d);
                    pyr.setHealth(300.0f);
                }
            }
        }
    }

    @SubscribeEvent
    public static void checkLeavingPlayersHealthAndUp(EntityLeaveLevelEvent event){
        Level level = event.getLevel();
        Entity player = event.getEntity();

        if (!player.level.isClientSide){
            if (level != null && player instanceof ServerPlayer){

                Player pyr = (Player) player;
                MinecraftServer server = player.getServer();
                if (pyr.getMaxHealth() < 300 && ((ServerPlayer) pyr).getAdvancements().getOrStartProgress(server.getAdvancements().getAdvancement(advancement)).isDone()){

                    Objects.requireNonNull(pyr.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(300.0d);
                    pyr.setHealth(300.f);
                }
            }
        }
    }

}
