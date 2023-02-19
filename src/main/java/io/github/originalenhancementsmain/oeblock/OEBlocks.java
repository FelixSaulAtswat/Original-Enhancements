package io.github.originalenhancementsmain.oeblock;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.item.OEItems;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusControllerBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.Components.EnergyConductorBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.Components.NatureConverterDiskBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blocks.NatureRealNameReconfigurableApparatusBlock;
import io.github.originalenhancementsmain.oeblock.blocks.LadiaOreBlock;
import io.github.originalenhancementsmain.oeblock.blocks.ModelProviderBlock;
import io.github.originalenhancementsmain.oeblock.blocks.TestBlocks;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class OEBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OriginalEnhancementMain.MOD_ID);

    public static final RegistryObject<Block> DEMON_ICE = registerBlock("demon_ice", () -> new Block(BlockBehaviour.Properties.of(Material.ICE, MaterialColor.COLOR_PURPLE)
            .strength(10.0f, 3.0f).speedFactor(0.4F).sound(SoundType.GLASS).requiresCorrectToolForDrops()), OriginalEnhancementMain.OETab);


    public static final RegistryObject<Block> LADIA_ORE = registerBlock("ladia_ore",() -> new LadiaOreBlock(BlockBehaviour.Properties.of(Material.STONE)
            .strength(100.0f,300.0f).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion(),UniformInt.of(10,20)), OriginalEnhancementMain.OETab);

    public static final RegistryObject<Block> DEEPSLATE_LADIA_ORE = registerBlock("deepslate_ladia_ore",() -> new LadiaOreBlock(BlockBehaviour.Properties.of(Material.STONE,MaterialColor.DEEPSLATE)
            .strength(100.0f,300.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops().noOcclusion(),UniformInt.of(10,20)), OriginalEnhancementMain.OETab);

    public static final RegistryObject<Block> NATURE_REAL_NAME_RECONFIGURABLE_APPARATUS_BLOCK = registerBlock("nature_real_name_reconfigurable_apparatus", ()-> new NatureRealNameReconfigurableApparatusBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(10.0f, 30.0f).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().noOcclusion().lightLevel(canGlowing(ApparatusControllerBlock.STRUCTURE_COMPOSITION, 15))), OriginalEnhancementMain.OETab);

    public static final RegistryObject<Block> NATURE_ENERGY_CONDUCTOR = registerBlock("nature_energy_conductor", ()-> new EnergyConductorBlock(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.COLOR_GREEN)
            .strength(10.0f, 30.0f).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().noOcclusion()), OriginalEnhancementMain.OETab);

    public static final RegistryObject<Block> NATURE_CONVERTER_DISK = registerBlock("nature_converter_disk", ()-> new NatureConverterDiskBlock(BlockBehaviour.Properties.copy(OEBlocks.NATURE_ENERGY_CONDUCTOR.get())), OriginalEnhancementMain.OETab);

    public static final RegistryObject<Block> MODEL_PROVIDER_BLOCK = BLOCKS.register("model_provider_block", ()-> new ModelProviderBlock(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.COLOR_GRAY)
            .strength(10.0f, 30.0f).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().noOcclusion()));


    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block", ()-> new TestBlocks(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(1500.0f, 1500.0f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)), OriginalEnhancementMain.OETab);
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        OEItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static ToIntFunction<BlockState> canGlowing(BooleanProperty property, int LightValue){
        return (state) -> state.getValue(property) ? LightValue : 0;
    }

}
