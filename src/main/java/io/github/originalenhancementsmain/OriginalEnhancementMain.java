package io.github.originalenhancementsmain;

import io.github.originalenhancementsmain.client.seen.NatureApparatusScreen;
import io.github.originalenhancementsmain.data.util.UtilProvider;
import io.github.originalenhancementsmain.oeblock.OEBlockEntities;
import io.github.originalenhancementsmain.oeblock.OEBlocks;
import io.github.originalenhancementsmain.data.placement.OEConfiguredFeatures;
import io.github.originalenhancementsmain.data.placement.OEPlacedFeatures;
import io.github.originalenhancementsmain.effects.OEEffect;
import io.github.originalenhancementsmain.entity.OEEntitiers;
import io.github.originalenhancementsmain.item.OEItems;
import io.github.originalenhancementsmain.oeblock.apparatusblock.OEMenus;
import io.github.originalenhancementsmain.recipe.OERecipes;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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

public class OriginalEnhancementMain {
    public static final String MOD_ID = "originalenhancement";
    public static final Logger LOG = LogManager.getLogger(MOD_ID);

    @SubscribeEvent
    public static void playJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getPlayer();
        Level level = player.level;

        player.sendMessage(new TextComponent("Be careful, once you complete certain levels, some monsters' abilities will increase dramatically., "+player.getDisplayName().getString()+"From "+(level.isClientSide?"CLIENT":"SERVER"+".")), Util.NIL_UUID);
    }
    public static final CreativeModeTab OETab = new CreativeModeTab(OriginalEnhancementMain.MOD_ID + ".conventional") {

        @Override
        @OnlyIn(Dist.CLIENT)
        public @NotNull ItemStack makeIcon(){
            return new ItemStack(OEBlocks.EXAMPLE_BLOCK.get());
        }
    };

    public static final CreativeModeTab OE3D = new CreativeModeTab(OriginalEnhancementMain.MOD_ID + ".3d") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(OEItems.DEATH_KNIGHT_SCEPTER.get());
        }
    };
    
    public OriginalEnhancementMain() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        OEItems.ITEMS.register(bus);
        OEBlocks.BLOCKS.register(bus);
        OESounds.SOUNDS.register(bus);
        OEEntitiers.ENTITY_TYPES.register(bus);
        GeckoLib.initialize();
        OEEffect.EFFECTS.register(bus);
        OEConfiguredFeatures.register(bus);
        OEPlacedFeatures.register(bus);
        OEBlockEntities.register(bus);
        OERecipes.register(bus);
        OEMenus.register(bus);
        bus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);

    }

    //Register screen
    private void clientSetup(final FMLClientSetupEvent event){
        MenuScreens.register(OEMenus.NATURE_APPARATUS_MENU.get(), NatureApparatusScreen :: new);
    }

    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
    }

    public static ResourceLocation getLocationResource(String name){
        return new ResourceLocation(MOD_ID, name);
    }

    public static MutableComponent getTranslationWay(String base, String name) {
        return new TranslatableComponent(getTranslationKey(base, name));
    }

    public static String getTranslationKey(String base, String name) {
        return UtilProvider.getTranslationKey(base, getLocationResource(name));
    }

}
