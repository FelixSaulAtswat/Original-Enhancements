package io.github.originalenhancementsmain.item;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.entity.OEEntitiers;
import io.github.originalenhancementsmain.item.items.food.EndFruit;
import io.github.originalenhancementsmain.item.items.material.LadiaCrystal;
import io.github.originalenhancementsmain.item.items.material.LadiaCrystalMax;
import io.github.originalenhancementsmain.item.items.weapon.DeathKnightScepter;
import io.github.originalenhancementsmain.item.items.weapon.Frostmourne;
import io.github.originalenhancementsmain.item.items.weapon.KnightMetalSwordItemY;
import io.github.originalenhancementsmain.item.items.material.MagicIcecrystal;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;



public class OEItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OriginalEnhancementsMain.MOD_ID);

    public static final RegistryObject<Item> MAGIC_ICECRYSTAL = register("magic_icecrystal", MagicIcecrystal::new);
    public static final RegistryObject<Item> LADIA_CRYSTASL = register("ladia_crystal", LadiaCrystal::new);
    public static final RegistryObject<Item> LADA_CRYSTASL_MAX = register("ladia_crystal_max", LadiaCrystalMax::new);

    public static final RegistryObject<Item> END_FRUIT = register("end_fruit", EndFruit::new);

    public static final RegistryObject<Item> DEATH_KNIGHT_SCEPTER = register("death_knight_scepter", DeathKnightScepter::new);

    public  static final RegistryObject<Item> FROSTMOURNE = register("frostmourne", Frostmourne::new);
    public static final RegistryObject<Item> KNIGHTMETAL_SWORD_ITEMY = register("knightmetal_sword_twilighthomology", KnightMetalSwordItemY::new);
    private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item)
    {
        return ITEMS.register(name, item);
    }

    public static final RegistryObject<Item> DEATH_KNIGHT_SPAWN_EGG = ITEMS.register("death_knight_spawn_egg",
            () -> new ForgeSpawnEggItem(OEEntitiers.DEATH_KNIGHT_IMI, 0, 88749, new Item.Properties().tab(OriginalEnhancementsMain.OETab)));

}
