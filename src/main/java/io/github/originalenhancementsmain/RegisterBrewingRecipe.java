package io.github.originalenhancementsmain;

import io.github.originalenhancementsmain.item.OEItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class RegisterBrewingRecipe {

    public static void init(){
        register(Items.POTION, Potions.THICK, OEItems.CHORUS_CLUSTER.get(), null, null, OEItems.CHORUS_EXTRACT.get());
        register(Items.POTION, Potions.THICK, Items.GOLDEN_APPLE, null, null, OEItems.FOREST_EXTRACT.get());

    }

    private static void register(ItemLike inputItem, ItemLike ingredient, ItemLike outputItem){
        BrewingRecipeRegistry.addRecipe(Ingredient.of(inputItem), Ingredient.of(ingredient), new ItemStack(outputItem));
    }

    private static void register(ItemLike inputPotionItem, Potion inputPotion, ItemLike ingredient, ItemLike outputPotionItem1, Potion outPotion, ItemLike outputItem2){
        if (outputItem2 == null) {
            BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(inputPotionItem), inputPotion)),
                    Ingredient.of(ingredient), PotionUtils.setPotion(new ItemStack(outputPotionItem1), outPotion));
        }
        if (outputPotionItem1 == null && outPotion == null){
            BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(inputPotionItem), inputPotion)),
                    Ingredient.of(ingredient), new ItemStack(outputItem2));
        }
    }
}

