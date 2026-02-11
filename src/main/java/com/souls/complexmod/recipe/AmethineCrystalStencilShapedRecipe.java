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

public class AmethineCrystalStencilShapedRecipe implements Recipe<SimpleContainer> {
    // Implementation details would go here
    private final ResourceLocation id;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    public AmethineCrystalStencilShapedRecipe(ResourceLocation id, String group, int width, int height,
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
               inputs.get(8).test(container.getItem(8)) && inputs.get(9).test(container.getItem(9)) &&
               inputs.get(10).test(container.getItem(10)) && inputs.get(11).test(container.getItem(11)) &&
               inputs.get(12).test(container.getItem(12)) && inputs.get(13).test(container.getItem(13)) &&
               inputs.get(14).test(container.getItem(14)) && inputs.get(15).test(container.getItem(15)) &&
               inputs.get(16).test(container.getItem(16)) && inputs.get(17).test(container.getItem(17)) &&
               inputs.get(18).test(container.getItem(18)) && inputs.get(19).test(container.getItem(19)) &&
               inputs.get(20).test(container.getItem(20)) && inputs.get(21).test(container.getItem(21)) &&
               inputs.get(22).test(container.getItem(22)) && inputs.get(23).test(container.getItem(23)) &&
               inputs.get(24).test(container.getItem(24)) && inputs.get(25).test(container.getItem(25)) &&
               inputs.get(26).test(container.getItem(26)) && inputs.get(27).test(container.getItem(27)) &&
               inputs.get(28).test(container.getItem(28)) && inputs.get(29).test(container.getItem(29)) &&
               inputs.get(30).test(container.getItem(30)) && inputs.get(31).test(container.getItem(31)) &&
               inputs.get(32).test(container.getItem(32)) && inputs.get(33).test(container.getItem(33)) &&
               inputs.get(34).test(container.getItem(34)) && inputs.get(35).test(container.getItem(35)) && inputs.get(36).test(container.getItem(36)) &&
               inputs.get(37).test(container.getItem(37)) && inputs.get(38).test(container.getItem(38)) && inputs.get(39).test(container.getItem(39)) &&
               inputs.get(40).test(container.getItem(40)) && inputs.get(41).test(container.getItem(41)) && inputs.get(42).test(container.getItem(42)) &&
               inputs.get(43).test(container.getItem(43)) && inputs.get(44).test(container.getItem(44)) && inputs.get(45).test(container.getItem(45)) &&
               inputs.get(46).test(container.getItem(46)) && inputs.get(47).test(container.getItem(47)) && inputs.get(48).test(container.getItem(48));
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

    public static class Type implements RecipeType<AmethineCrystalStencilShapedRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "complexmod:amethine_crystal_stencil_shaped";
    }

    public static class Serializer implements RecipeSerializer<AmethineCrystalStencilShapedRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(ComplexMod.MOD_ID, "amethine_crystal_stencil_shaped");

        @Override
        public AmethineCrystalStencilShapedRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
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

            NonNullList<Ingredient> inputs = NonNullList.withSize(49, Ingredient.EMPTY);

            for (int row = 0; row < height; row++) {
                String line = pattern.get(row);

                for (int col = 0; col < width; col++) {
                    char symbol = line.charAt(col);
                    Ingredient ingredient = key.getOrDefault(symbol, Ingredient.EMPTY);

                    inputs.set(row * 7 + col, ingredient);
                }
            }
            
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            return new AmethineCrystalStencilShapedRecipe(recipeId, "", 7, 7, inputs, output);
        }

        @Override
        public AmethineCrystalStencilShapedRecipe fromNetwork(ResourceLocation recipeId, net.minecraft.network.FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            return new AmethineCrystalStencilShapedRecipe(recipeId, "", 7, 7, inputs, output);
        }

        @Override
        public void toNetwork(net.minecraft.network.FriendlyByteBuf buffer, AmethineCrystalStencilShapedRecipe recipe) {
            // Serialization logic would go here
            buffer.writeInt(recipe.inputs.size());

            for (Ingredient ingredient : recipe.inputs) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItemStack(recipe.getResultItem(null), false);
        }
    }
}
