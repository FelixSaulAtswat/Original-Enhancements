package io.github.originalenhancementsmain.recipe.apparatusrecipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.recipe.OERecipeTypes;
import io.github.originalenhancementsmain.recipe.OERecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class NatureRealNameReconfigurableApparatusRecipes implements Recipe<SimpleContainer> {

    private final ResourceLocation resourceId;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItem;

    private NatureRealNameReconfigurableApparatusRecipes(ResourceLocation resourceId, ItemStack output, NonNullList<Ingredient> recipeItem){
        this.resourceId = resourceId;
        this.output = output;
        this.recipeItem = recipeItem;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        return recipeItem.get(0).test(container.getItem(0)) && recipeItem.get(1).test(container.getItem(1)) && recipeItem.get(2).test(container.getItem(2));
    }

    @Override
    public ItemStack assemble(SimpleContainer container) {
        return output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients(){
        return recipeItem;
    }
    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(){
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return resourceId;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return OERecipes.NATURE_APPARATUS_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return OERecipeTypes.NATURE_APPARATUS_RECIPE.get();
    }

    public static class Serializer implements RecipeSerializer<NatureRealNameReconfigurableApparatusRecipes>{
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public @NotNull NatureRealNameReconfigurableApparatusRecipes fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json){
            ItemStack putItem = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");

            NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);
            for (int value = 0; value < inputs.size(); value ++){
                inputs.set(value, Ingredient.fromJson(ingredients.get(value)));
            }
            return new NatureRealNameReconfigurableApparatusRecipes(id, putItem, inputs);
        }

        @Override
        public NatureRealNameReconfigurableApparatusRecipes fromNetwork(@NotNull ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.fromNetwork(buf));

            ItemStack output = buf.readItem();
            return new NatureRealNameReconfigurableApparatusRecipes(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, NatureRealNameReconfigurableApparatusRecipes recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }

    }

}
