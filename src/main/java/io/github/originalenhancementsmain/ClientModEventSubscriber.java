package io.github.originalenhancementsmain;

import io.github.originalenhancementsmain.client.model.entity.DeathKnightModel;
import io.github.originalenhancementsmain.entity.OEEntitiers;
import io.github.originalenhancementsmain.entity.monster.DeathKnight;
import io.github.originalenhancementsmain.recipe.OERecipes;
import io.github.originalenhancementsmain.render.DeathKnightRender;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
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
        event.registerEntityRenderer(OEEntitiers.DEATH_KNIGHT_IMI.get(), DeathKnightRender::new);
    }

    //将所有的生物的属性信息写在这个函数里，有几个写几个
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(OEEntitiers.DEATH_KNIGHT_IMI.get(), DeathKnight.registerAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event){
        CriteriaTriggers.register(OERecipes.OPENED_TRIGGER);
    }
}
