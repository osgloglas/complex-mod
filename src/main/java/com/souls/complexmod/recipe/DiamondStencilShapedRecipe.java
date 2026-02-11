package com.souls.complexmod.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.souls.complexmod.ComplexMod;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

public class DiamondStencilShapedRecipe implements Recipe<SimpleContainer> {
    // Implementation details would go here
    private final ResourceLocation id;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    public DiamondStencilShapedRecipe(ResourceLocation id, String group, int width, int height,
            NonNullList<Ingredient> inputs, ItemStack output) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        // Matching logic would go here
        if (level.isClientSide()) {
            return false;
        }

        return inputs.get(0).test(container.getItem(0)) && inputs.get(1).test(container.getItem(1)) &&
               inputs.get(2).test(container.getItem(2)) && inputs.get(3).test(container.getItem(3)) &&
               inputs.get(4).test(container.getItem(4)) && inputs.get(5).test(container.getItem(5)) &&
               inputs.get(6).test(container.getItem(6)) && inputs.get(7).test(container.getItem(7)) &&
               inputs.get(8).test(container.getItem(8));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<DiamondStencilShapedRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "complexmod:diamond_stencil_shaped";
    }

    public static class Serializer implements RecipeSerializer<DiamondStencilShapedRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(ComplexMod.MOD_ID, "diamond_stencil_shaped");

        @Override
        public DiamondStencilShapedRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");

            JsonArray patternArray = GsonHelper.getAsJsonArray(json, "pattern");
            List<String> pattern = new ArrayList<>();

            for (JsonElement element : patternArray) {
                pattern.add(element.getAsString());
            }

            int width = pattern.size();
            int height = pattern.get(0).length();

            JsonObject keyObject = GsonHelper.getAsJsonObject(json, "key");
            Map<Character, Ingredient> key = new HashMap<>();

            for (Map.Entry<String, JsonElement> entry : keyObject.entrySet()) {
                char symbol = entry.getKey().charAt(0);
                Ingredient ingredient = Ingredient.fromJson(entry.getValue());
                key.put(symbol, ingredient);
            }

            NonNullList<Ingredient> inputs = NonNullList.withSize(9, Ingredient.EMPTY);

            for (int row = 0; row < height; row++) {
                String line = pattern.get(row);

                for (int col = 0; col < width; col++) {
                    char symbol = line.charAt(col);
                    Ingredient ingredient = key.getOrDefault(symbol, Ingredient.EMPTY);

                    inputs.set(row * 3 + col, ingredient);
                }
            }
            
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            return new DiamondStencilShapedRecipe(recipeId, "", 3, 3, inputs, output);
        }

        @Override
        public DiamondStencilShapedRecipe fromNetwork(ResourceLocation recipeId, net.minecraft.network.FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            return new DiamondStencilShapedRecipe(recipeId, "", 3, 3, inputs, output);
        }

        @Override
        public void toNetwork(net.minecraft.network.FriendlyByteBuf buffer, DiamondStencilShapedRecipe recipe) {
            // Serialization logic would go here
            buffer.writeInt(recipe.inputs.size());

            for (Ingredient ingredient : recipe.inputs) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItemStack(recipe.getResultItem(null), false);
        }
    }
}
