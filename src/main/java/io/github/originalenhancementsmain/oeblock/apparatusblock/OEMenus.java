package io.github.originalenhancementsmain.oeblock.apparatusblock;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockmenus.NatureRealNameReconfigurableApparatusMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OEMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, OriginalEnhancementMain.MOD_ID);

    public static final RegistryObject<MenuType<NatureRealNameReconfigurableApparatusMenu>> NATURE_APPARATUS_MENU = registerMenu("nature_apparatus_menu", NatureRealNameReconfigurableApparatusMenu :: new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenu(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus bus){
        MENUS.register(bus);
    }

}
