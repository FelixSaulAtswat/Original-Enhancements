package io.github.originalenhancementsmain.item;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.entity.OEEntitiers;
import io.github.originalenhancementsmain.item.items.DeathKnightScepter;
import io.github.originalenhancementsmain.item.items.Frostmourne;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Supplier;



public class OEItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OriginalEnhancementsMain.MOD_ID);

    public static RegistryObject<Item> DEATH_KNIGHT_SCEPTER = register("death_knight_scepter", DeathKnightScepter::new);

    public  static  RegistryObject<Item> FROSTMOURNE = register("frostmourne", Frostmourne::new);
    private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item)
    {
        return ITEMS.register(name, item);
    }

    public static final RegistryObject<Item> OE_EGG = ITEMS.register("death_knight_spawn_egg",
            () -> new ForgeSpawnEggItem(OEEntitiers.DEATH_KNIGHT_IMI, 0, 88749, new Item.Properties().tab(OriginalEnhancementsMain.OETab)));

}
