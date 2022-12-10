package io.github.originalenhancementsmain.block;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.item.OEItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public class OEBlocks {
    //注册机
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            OriginalEnhancementsMain.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = OEItems.ITEMS;

    //定义一个属于我们的方块
    public static final RegistryObject<Block> EXAMPLE_BLOCK = register("demon_ice",
            () -> new Block(BlockBehaviour.Properties.of(Material.ICE, MaterialColor.COLOR_PURPLE).strength(10.0f, 3.0f).speedFactor(0.4F)
                    .sound(SoundType.GLASS).requiresCorrectToolForDrops()),
            object -> () -> new BlockItem(object.get(), new Item.Properties().tab(OriginalEnhancementsMain.OETab)));


    private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<? extends T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<? extends T> block,
                                                                Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> obj = registerBlock(name, block);
        ITEMS.register(name, item.apply(obj));
        return obj;
    }
}
