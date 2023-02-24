package io.github.originalenhancementsmain;


import io.github.originalenhancementsmain.client.render.effects.TheEndRenderLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = OriginalEnhancementMain.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetUpEvent {


    private static Field field;

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public static void addRenderLayers(EntityRenderersEvent.AddLayers event) {
        if (field == null) {
            try {
                field = EntityRenderersEvent.AddLayers.class.getDeclaredField("renderers");
                field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        if (field != null) {
            event.getSkins().forEach(renderer -> {
                LivingEntityRenderer<Player, EntityModel<Player>> skin = event.getSkin(renderer);
                addRenderLayers(Objects.requireNonNull(skin));
            });
            try {
                ((Map<EntityType<?>, EntityRenderer<?>>) field.get(event)).values().stream().
                        filter(LivingEntityRenderer.class::isInstance).map(LivingEntityRenderer.class::cast).forEach(ClientSetUpEvent::addRenderLayers);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static <T extends LivingEntity, M extends EntityModel<T>> void addRenderLayers(LivingEntityRenderer<T, M> renderer) {
        renderer.addLayer(new TheEndRenderLayer<>(renderer));
    }

}
