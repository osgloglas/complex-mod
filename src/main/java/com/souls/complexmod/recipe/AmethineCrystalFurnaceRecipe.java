package com.souls.complexmod.recipe;

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

public class AmethineCrystalFurnaceRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;
    private final float experience;

    public AmethineCrystalFurnaceRecipe(ResourceLocation id, NonNullList<Ingredient> inputs, ItemStack output, float experience) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
        this.experience = experience;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        // Matching logic would go here
        if (level.isClientSide()) {
            return false;
        }

        return inputs.get(0).test(container.getItem(0)) && inputs.get(1).test(container.getItem(1));
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

    public float getXP() {
        return experience;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<AmethineCrystalFurnaceRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "complexmod:amethine_crystal_furnace";
    }

    public static class Serializer implements RecipeSerializer<AmethineCrystalFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(ComplexMod.MOD_ID, "amethine_crystal_furnace");

        @Override
        public AmethineCrystalFurnaceRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient main = Ingredient.fromJson(json.get("input_main"));
            Ingredient fuel = Ingredient.fromJson(json.get("input_fuel"));
            
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            float experience = json.get("xp").getAsFloat();

            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
            inputs.set(0, main);
            inputs.set(1, fuel);

            return new AmethineCrystalFurnaceRecipe(recipeId, inputs, output, experience);
        }

        @Override
        public AmethineCrystalFurnaceRecipe fromNetwork(ResourceLocation recipeId, net.minecraft.network.FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            float experience = buffer.readFloat();

            return new AmethineCrystalFurnaceRecipe(recipeId, inputs, output, experience);
        }

        @Override
        public void toNetwork(net.minecraft.network.FriendlyByteBuf buffer, AmethineCrystalFurnaceRecipe recipe) {
            // Serialization logic would go here
            buffer.writeInt(recipe.inputs.size());

            for (Ingredient ingredient : recipe.inputs) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItemStack(recipe.getResultItem(null), false);
        }
    }
}
