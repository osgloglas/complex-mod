package com.souls.complexmod.util;

import com.souls.complexmod.ComplexMod;
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

    public static void register (IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
