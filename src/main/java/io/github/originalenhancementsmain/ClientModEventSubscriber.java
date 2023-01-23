package io.github.originalenhancementsmain;

import io.github.originalenhancementsmain.client.model.entity.DeathKnightModel;
import io.github.originalenhancementsmain.client.seen.NatureApparatusScreen;
import io.github.originalenhancementsmain.entity.OEEntitiers;
import io.github.originalenhancementsmain.entity.monster.DeathKnight;
import io.github.originalenhancementsmain.oeblock.apparatusblock.OEMenus;
import io.github.originalenhancementsmain.recipe.OERecipes;
import io.github.originalenhancementsmain.recipe.apparatusrecipes.NatureRealNameReconfigurableApparatusRecipes;
import io.github.originalenhancementsmain.render.DeathKnightRender;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = OriginalEnhancementMain.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEventSubscriber {

    //Mob's texture
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DeathKnightModel.LAYER_LOCATION, DeathKnightModel::createBodyLayer);
    }

    //Mob's render
    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OEEntitiers.DEATH_KNIGHT_IMI.get(), DeathKnightRender::new);
    }

    //Mob's attribute
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(OEEntitiers.DEATH_KNIGHT_IMI.get(), DeathKnight.registerAttributes().build());
    }
    //Register recipe
    @SubscribeEvent
    public static void onRegisterRecipeSerializers(final RegistryEvent.Register<RecipeSerializer<?>> event){
        Registry.register(Registry.RECIPE_TYPE, NatureRealNameReconfigurableApparatusRecipes.Type.ID, NatureRealNameReconfigurableApparatusRecipes.Type.INSTANCE);
    }

}
