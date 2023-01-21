package io.github.originalenhancementsmain.recipe.apparatusrecipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;


import javax.annotation.Nullable;

public class NatureRealNameReconfigurableApparatusRecipes implements Recipe<SimpleContainer> {

    private final ResourceLocation resourceId;
    private final ItemStack putItem;
    private final NonNullList<Ingredient> recipeItem;

    private NatureRealNameReconfigurableApparatusRecipes(ResourceLocation resourceId, ItemStack putItem, NonNullList<Ingredient> recipeItem){
        this.resourceId = resourceId;
        this.putItem = putItem;
        this.recipeItem = recipeItem;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        return recipeItem.get(0).test(container.getItem(1)) && recipeItem.get(1).test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer container) {
        return putItem;
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
        return putItem.copy();
    }

    @Override
    public ResourceLocation getId() {
        return resourceId;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<NatureRealNameReconfigurableApparatusRecipes>{
        private Type(){}
        public static final Type INSTANCE = new Type();
        public static final String ID = "nature_apparatus";
    }

    public static class Serializer implements RecipeSerializer<NatureRealNameReconfigurableApparatusRecipes>{
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(OriginalEnhancementMain.MOD_ID, "nature_apparatus");

        @Override
        public @NotNull NatureRealNameReconfigurableApparatusRecipes fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json){
            ItemStack putItem = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "putItem"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");

            NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);
            for (int value = 0; value < inputs.size();){
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

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return Serializer.castClass();
        }

        @SuppressWarnings("unchecked")
        private static <G> Class<G> castClass() {
            return (Class<G>) RecipeSerializer.class;
        }

    }

}
