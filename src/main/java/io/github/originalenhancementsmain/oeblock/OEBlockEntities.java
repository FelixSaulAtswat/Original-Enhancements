package io.github.originalenhancementsmain.oeblock;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentity.NatureRealNameReconfigurableApparatusBlockEntity;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.mojang.datafixers.types.Type;

import java.util.function.Supplier;

public class OEBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, OriginalEnhancementMain.MOD_ID);

    public static final RegistryObject<BlockEntityType<NatureRealNameReconfigurableApparatusBlockEntity>> NATURE_APPARATUS_CONTROLLER_BLOCK_ENTITY = registryBlockEntity("nature_real_name_reconfigurable_apparatus_block_entity",
            NatureRealNameReconfigurableApparatusBlockEntity :: new, OEBlocks.NATURE_REAL_NAME_RECONFIGURABLE_APPARATUS_BLOCK);


    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registryBlockEntity(String name, BlockEntityType.BlockEntitySupplier<? extends T> blockEntity, Supplier<? extends Block> block){
        return BLOCK_ENTITIES.register(name, () ->  BlockEntityType.Builder.<T>of(blockEntity, block.get()).build(getType(name)));
    }
    public static void register(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }

    private static Type<?> getType(String name){
        return Util.fetchChoiceType(References.BLOCK_ENTITY, resourceLocationName(name));
    }

    protected static String resourceLocationName(String name){
        return OriginalEnhancementMain.MOD_ID + ":" + name;
    }
}
