package io.github.originalenhancementsmain;

import io.github.originalenhancementsmain.client.seen.NatureApparatusScreen;
import io.github.originalenhancementsmain.data.placement.OEConfiguredFeatures;
import io.github.originalenhancementsmain.data.placement.OEPlacedFeatures;
import io.github.originalenhancementsmain.data.util.Util;
import io.github.originalenhancementsmain.effect.OEEffects;
import io.github.originalenhancementsmain.entity.OEEntitiers;
import io.github.originalenhancementsmain.item.OEItems;
import io.github.originalenhancementsmain.oeblock.OEBlockEntities;
import io.github.originalenhancementsmain.oeblock.OEBlocks;
import io.github.originalenhancementsmain.oeblock.apparatusblock.OEMenus;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureRealNameReconfigurableApparatusBlockEntity;
import io.github.originalenhancementsmain.oeblock.blockrenders.NatureApparatusRender;
import io.github.originalenhancementsmain.recipe.OERecipeTypes;
import io.github.originalenhancementsmain.recipe.OERecipes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.RenderTypeHelper;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.GeckoLib;


@Mod(OriginalEnhancementMain.MOD_ID)
@Mod.EventBusSubscriber
public class OriginalEnhancementMain {
    public static final String MOD_ID = "originalenhancement";
    public static final Logger LOG = LogManager.getLogger(MOD_ID);

    @SubscribeEvent
    public static void playJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        player.sendSystemMessage(Component.translatable("Be careful, once you complete certain levels, some monsters' abilities will increase dramatically."));
    }
    public static final CreativeModeTab OETab = new CreativeModeTab(OriginalEnhancementMain.MOD_ID + ".conventional") {

        @Override
        @OnlyIn(Dist.CLIENT)
        public @NotNull ItemStack makeIcon(){
            return new ItemStack(OEItems.LADA_CRYSTASL_MAX.get());
        }
    };

    public static final CreativeModeTab OE3D = new CreativeModeTab(OriginalEnhancementMain.MOD_ID + ".3d") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(OEItems.FROSTMOURNE.get());
        }
    };
    
    public OriginalEnhancementMain() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        OEItems.ITEMS.register(bus);
        OEBlocks.BLOCKS.register(bus);
        OESounds.SOUNDS.register(bus);
        OEEntitiers.ENTITY_TYPES.register(bus);
        GeckoLib.initialize();
        OEEffects.EFFECTS.register(bus);
        OEConfiguredFeatures.register(bus);
        OEPlacedFeatures.register(bus);
        OEBlockEntities.register(bus);
        OERecipes.register(bus);
        OERecipeTypes.register(bus);
        OEMenus.register(bus);
        bus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);

    }

    //Register screen
    private void clientSetup(final FMLClientSetupEvent event){
        MenuScreens.register(OEMenus.NATURE_APPARATUS_MENU.get(), NatureApparatusScreen :: new);
    }

    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(OEBlockEntities.NATURE_APPARATUS_CONTROLLER_BLOCK_ENTITY.get(), NatureApparatusRender::new);
    }

    public static ResourceLocation getLocationResource(String name){
        return new ResourceLocation(MOD_ID, name);
    }


    public static MutableComponent getTranslationWay(String base, String name) {
        return Component.translatable(getTranslationKey(base, name));
    }

    public static String getTranslationKey(String base, String name) {
        return Util.getTranslationKey(base, getLocationResource(name));
    }

}
