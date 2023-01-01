package io.github.originalenhancementsmain.recipe;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.recipe.apparatusrecipes.NatureRealNameReconfigurableApparatusRecipes;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OERecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, OriginalEnhancementsMain.MOD_ID);

    public static final RegistryObject<RecipeSerializer<NatureRealNameReconfigurableApparatusRecipes>> NATURE_APPARATUS_RECIPES =
            SERIALIZERS.register("nature_apparatus", ()-> NatureRealNameReconfigurableApparatusRecipes.Serializer.INSTANCE);

    public static void register(IEventBus bus){
        SERIALIZERS.register(bus);
    }
}
