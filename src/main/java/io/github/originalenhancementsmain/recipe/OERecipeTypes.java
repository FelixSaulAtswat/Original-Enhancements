package io.github.originalenhancementsmain.recipe;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.recipe.apparatusrecipes.NatureRealNameReconfigurableApparatusRecipes;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OERecipeTypes {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, OriginalEnhancementMain.MOD_ID);

    public static final RegistryObject<RecipeType<NatureRealNameReconfigurableApparatusRecipes>> NATURE_APPARATUS_RECIPE = RECIPE_TYPES.register("nature_apparatus", ()-> new RecipeType<>(){
        @Override
        public String toString() {
            return "originalenhancement:nature_apparatus";
        }
    });

    public static void register(IEventBus bus){
        RECIPE_TYPES.register(bus);
    }
}
