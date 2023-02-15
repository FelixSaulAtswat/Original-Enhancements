package io.github.originalenhancementsmain;


import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.HealthBoostMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = OriginalEnhancementMain.MOD_ID)
public class PlayerAttributeModificationEvent {

    private static final ResourceLocation advancement = new ResourceLocation("originalenhancement:root");

    @SubscribeEvent
    public static void checkResurrectedPlayersHealthAndUp(PlayerEvent.PlayerRespawnEvent event) {
        Player pyr = event.getEntity();

        if (!pyr.level.isClientSide) {
            if (pyr instanceof ServerPlayer) {

                MinecraftServer server = pyr.getServer();
                if (pyr.getMaxHealth() < 300.0f && ((ServerPlayer) pyr).getAdvancements().getOrStartProgress(server.getAdvancements().getAdvancement(advancement)).isDone()){
                    Objects.requireNonNull(pyr.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(300.0d);
                    pyr.addEffect(new MobEffectInstance(MobEffects.HEAL, 20*10, 200));
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
                if (pyr.getMaxHealth() < 300.0f && ((ServerPlayer) pyr).getAdvancements().getOrStartProgress(server.getAdvancements().getAdvancement(advancement)).isDone()){
                    Objects.requireNonNull(pyr.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(300.0d);
                    if (pyr.getHealth() == 20.0f && level.dimension() == Level.END){
                        pyr.addEffect(new MobEffectInstance(MobEffects.HEAL, 20*10, 200));
                    }
                }
            }
        }
    }

}
