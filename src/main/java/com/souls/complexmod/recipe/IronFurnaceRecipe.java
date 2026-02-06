package com.souls.complexmod.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

public class IronFurnaceRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final Ingredient input;
    private final Ingredient inputFuel;
    private final ItemStack output;
    private final float experience;

    public IronFurnaceRecipe(ResourceLocation id, Ingredient input, Ingredient inputFuel, ItemStack output, float experience) {
        this.id = id;
        this.input = input;
        this.inputFuel = inputFuel;
        this.output = output;
        this.experience = experience;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        // Matching logic would go here
        if (level.isClientSide()) {
            return false;
        }

        return input.test(container.getItem(0)) && inputFuel.test(container.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return output.copy();
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
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<IronFurnaceRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "complexmod:iron_furnace";
    }

    public static class Serializer implements RecipeSerializer<IronFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(ComplexMod.MOD_ID, "iron_furnace");

        @Override
        public IronFurnaceRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient main = Ingredient.fromJson(json.get("input_main"));
            Ingredient fuel = Ingredient.fromJson(json.get("input_fuel"));
            
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            float experience = json.has("xp") ? json.get("xp").getAsFloat() : 0.0f;

            return new IronFurnaceRecipe(recipeId, main, fuel, output, experience);
        }

        @Override
        public IronFurnaceRecipe fromNetwork(ResourceLocation recipeId, net.minecraft.network.FriendlyByteBuf buffer) {
            Ingredient main = Ingredient.fromNetwork(buffer);
            Ingredient fuel = Ingredient.fromNetwork(buffer);

            ItemStack output = buffer.readItem();
            float experience = buffer.readFloat();

            return new IronFurnaceRecipe(recipeId, main, fuel, output, experience);
        }

        @Override
        public void toNetwork(net.minecraft.network.FriendlyByteBuf buffer, IronFurnaceRecipe recipe) {
            // Serialization logic would go here
            recipe.input.toNetwork(buffer);
            recipe.inputFuel.toNetwork(buffer);
            
            buffer.writeItemStack(recipe.output, false);
            buffer.writeFloat(recipe.experience);
        }
    }
}
