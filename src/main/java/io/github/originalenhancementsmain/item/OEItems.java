package io.github.originalenhancementsmain.item;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.entity.OEEntitiers;
import io.github.originalenhancementsmain.item.items.food.EndFruit;
import io.github.originalenhancementsmain.item.items.material.LadiaCrystalMax;
import io.github.originalenhancementsmain.item.items.material.MagicIcecrystal;
import io.github.originalenhancementsmain.item.items.material.NatureApprartusCoreItem;
import io.github.originalenhancementsmain.item.items.weapon.DeathKnightScepter;
import io.github.originalenhancementsmain.item.items.weapon.Frostmourne;
import io.github.originalenhancementsmain.item.items.weapon.KnightMetalSwordItemY;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;



public class OEItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OriginalEnhancementMain.MOD_ID);

    public static final RegistryObject<Item> MAGIC_ICECRYSTAL = register("magic_icecrystal", MagicIcecrystal::new);
    public static final RegistryObject<Item> LADIA_CRYSTASL = register("ladia_crystal", ()-> new Item(new Item.Properties().tab(OriginalEnhancementMain.OETab)));
    public static final RegistryObject<Item> LADA_CRYSTASL_MAX = register("ladia_crystal_max", LadiaCrystalMax::new);
    public static final RegistryObject<Item> LADA_CRYSTASL_POWDER = register("ladia_crystal_powder", ()-> new Item(new Item.Properties().tab(OriginalEnhancementMain.OETab)));
    public static final RegistryObject<Item> LADA_CRYSTASL_SHARD = register("ladia_crystal_shard", ()-> new Item(new Item.Properties().tab(OriginalEnhancementMain.OETab)));

    public static final RegistryObject<Item> AMETHYST_PLATE = register("amethyst_plate", ()-> new  Item(new Item.Properties().tab(OriginalEnhancementMain.OETab)));
    public static final RegistryObject<Item> NATURE_APPARATUS_CORE = register("nature_apparatus_core", NatureApprartusCoreItem::new);

    public static final RegistryObject<Item> END_FRUIT = register("end_fruit", EndFruit::new);

    public static final RegistryObject<Item> DEATH_KNIGHT_SCEPTER = register("death_knight_scepter", DeathKnightScepter::new);

    public  static final RegistryObject<Item> FROSTMOURNE = register("frostmourne", Frostmourne::new);
    public static final RegistryObject<Item> KNIGHTMETAL_SWORD_ITEMY = register("knightmetal_sword_twilighthomology", KnightMetalSwordItemY::new);

    public static final RegistryObject<Item> LEFT_ARROW_FOREST = register("left_arrow_forest", GUIItem::new);
    public static final RegistryObject<Item> RIGHT_ARROW_FOREST = register("right_arrow_forest", GUIItem::new);
    public static final RegistryObject<Item> FROSTMOURNE_SELF = register("frostmourne_self", OE3DItem::new);

    private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item)
    {
        return ITEMS.register(name, item);
    }

    public static final RegistryObject<Item> DEATH_KNIGHT_SPAWN_EGG = ITEMS.register("death_knight_spawn_egg",
            () -> new ForgeSpawnEggItem(OEEntitiers.DEATH_KNIGHT_IMI, 0, 88749, new Item.Properties().tab(OriginalEnhancementMain.OETab)));

}
