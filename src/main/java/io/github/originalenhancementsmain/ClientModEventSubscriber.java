package io.github.originalenhancementsmain;

import io.github.originalenhancementsmain.client.bakemodel.OEBakeModel;
import io.github.originalenhancementsmain.entity.OEEntitiers;
import io.github.originalenhancementsmain.entity.models.DeathKnightModel;
import io.github.originalenhancementsmain.entity.monster.DeathKnight;
import io.github.originalenhancementsmain.item.OEItems;
import io.github.originalenhancementsmain.mobrender.DeathKnightRender;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.Objects;

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
    @SubscribeEvent
    public static void onModelBaked(ModelEvent.BakingCompleted event){
        getBakedModel(OEItems.FROSTMOURNE, event);
    }

    private static void getBakedModel(RegistryObject<Item> item, ModelEvent.BakingCompleted event){
        Map<ResourceLocation, BakedModel> modelRegistry = event.getModels();
        ModelResourceLocation resource = new ModelResourceLocation(Objects.requireNonNull(item.getId()), "inventory");
        BakedModel model = modelRegistry.get(resource);
        if (model == null){
            throw new RuntimeException("Did not find model in register");
        }else if (model instanceof OEBakeModel){
            throw new RuntimeException("Attempt to replace the item model twice is hidden");
        }else {
            OEBakeModel newModel = new OEBakeModel(model);
            event.getModels().put(resource, newModel);
        }
    }
}
