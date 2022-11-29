package io.github.originalenhancementsmain;

import io.github.originalenhancementsmain.client.model.entity.DeathKnightModel;
import io.github.originalenhancementsmain.entity.OEEntitiers;
import io.github.originalenhancementsmain.entity.monster.DeathKnight;
import io.github.originalenhancementsmain.rander.DeathKnightRander;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OriginalEnhancementsMain.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEventSubscriber {

    //将所有的生物的皮肤贴图信息写在这个函数里，有几个写几个
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DeathKnightModel.LAYER_LOCATION, DeathKnightModel::createBodyLayer);
    }

    //将所有的生物的渲染信息写在这个函数里，有几个写几个
    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OEEntitiers.DEATH_KNIGHT_IMI.get(), DeathKnightRander::new);
    }

    //将所有的生物的属性信息写在这个函数里，有几个写几个
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(OEEntitiers.DEATH_KNIGHT_IMI.get(), DeathKnight.registerAttributes().build());
    }

}
