package com.souls.complexmod.util;

import org.checkerframework.checker.units.qual.s;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.recipe.AmethineCrystalFurnaceRecipe;
import com.souls.complexmod.recipe.AmethineCrystalStencilShapedRecipe;
import com.souls.complexmod.recipe.DiamondFurnaceRecipe;
import com.souls.complexmod.recipe.DiamondStencilShapedRecipe;
import com.souls.complexmod.recipe.EmeraldFurnaceRecipe;
import com.souls.complexmod.recipe.EmeraldStencilShapedRecipe;
import com.souls.complexmod.recipe.IronFurnaceRecipe;
import com.souls.complexmod.recipe.IronStencilShapedRecipe;
import com.souls.complexmod.recipe.NetheriteFurnaceRecipe;
import com.souls.complexmod.recipe.NetheriteStencilShapedRecipe;
import com.souls.complexmod.recipe.StencilShapedRecipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ComplexMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<StencilShapedRecipe>> STENCIL_SHAPED_RECIPE_SERIALIZER =
            SERIALIZERS.register("stencil_shaped", () -> StencilShapedRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<IronFurnaceRecipe>> IRON_FURNACE_RECIPE_SERIALIZER =
            SERIALIZERS.register("iron_furnace", () -> IronFurnaceRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<IronStencilShapedRecipe>> IRON_STENCIL_SHAPED_RECIPE_SERIALIZER =
            SERIALIZERS.register("iron_stencil_shaped", () -> IronStencilShapedRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<DiamondFurnaceRecipe>> DIAMOND_FURNACE_RECIPE_SERIALIZER =
            SERIALIZERS.register("diamond_furnace", () -> DiamondFurnaceRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<DiamondStencilShapedRecipe>> DIAMOND_STENCIL_SHAPED_RECIPE_SERIALIZER =
            SERIALIZERS.register("diamond_stencil_shaped", () -> DiamondStencilShapedRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<EmeraldFurnaceRecipe>> EMERALD_FURNACE_RECIPE_SERIALIZER =
            SERIALIZERS.register("emerald_furnace", () -> EmeraldFurnaceRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<EmeraldStencilShapedRecipe>> EMERALD_STENCIL_SHAPED_RECIPE_SERIALIZER =
            SERIALIZERS.register("emerald_stencil_shaped", () -> EmeraldStencilShapedRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<NetheriteFurnaceRecipe>> NETHERITE_FURNACE_RECIPE_SERIALIZER =
            SERIALIZERS.register("netherite_furnace", () -> NetheriteFurnaceRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<NetheriteStencilShapedRecipe>> NETHERITE_STENCIL_SHAPED_RECIPE_SERIALIZER =
            SERIALIZERS.register("netherite_stencil_shaped", () -> NetheriteStencilShapedRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<AmethineCrystalFurnaceRecipe>> AMETHINE_CRYSTAL_FURNACE_RECIPE_SERIALIZER =
            SERIALIZERS.register("amethine_crystal_furnace", () -> AmethineCrystalFurnaceRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<AmethineCrystalStencilShapedRecipe>> AMETHINE_CRYSTAL_STENCIL_SHAPED_RECIPE_SERIALIZER =
            SERIALIZERS.register("amethine_crystal_stencil_shaped", () -> AmethineCrystalStencilShapedRecipe.Serializer.INSTANCE);

    public static void register (IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
