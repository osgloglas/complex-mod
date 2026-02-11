package com.souls.complexmod.compat;

import java.util.List;

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
import com.souls.complexmod.recipe.StarFactoryRecipe;
import com.souls.complexmod.recipe.StencilShapedRecipe;
import com.souls.complexmod.screen.AmethineCrystalFurnaceScreen;
import com.souls.complexmod.screen.AmethineCrystalStencilTableScreen;
import com.souls.complexmod.screen.DiamondFurnaceScreen;
import com.souls.complexmod.screen.DiamondStencilTableScreen;
import com.souls.complexmod.screen.EmeraldFurnaceScreen;
import com.souls.complexmod.screen.EmeraldStencilTableScreen;
import com.souls.complexmod.screen.IronFurnaceScreen;
import com.souls.complexmod.screen.IronStencilTableScreen;
import com.souls.complexmod.screen.NetheriteFurnaceScreen;
import com.souls.complexmod.screen.NetheriteStencilTableScreen;
import com.souls.complexmod.screen.StarFactoryScreen;
import com.souls.complexmod.screen.StencilTableScreen;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

@JeiPlugin
public class JEIComplexModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ComplexMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new StencilTableCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new IronStencilTableCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new DiamondStencilTableCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new EmeraldStencilTableCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new NetheriteStencilTableCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new AmethineCrystalStencilTableCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new IronFurnaceCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new DiamondFurnaceCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new EmeraldFurnaceCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new NetheriteFurnaceCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new AmethineCrystalFurnaceCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new StarFactoryCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<StencilShapedRecipe> stencilTableRecipes = recipeManager.getAllRecipesFor(StencilShapedRecipe.Type.INSTANCE);
        List<IronStencilShapedRecipe> ironStencilTableRecipes = recipeManager.getAllRecipesFor(IronStencilShapedRecipe.Type.INSTANCE);
        List<DiamondStencilShapedRecipe> diamondStencilTableRecipes = recipeManager.getAllRecipesFor(DiamondStencilShapedRecipe.Type.INSTANCE);
        List<EmeraldStencilShapedRecipe> emeraldStencilTableRecipes = recipeManager.getAllRecipesFor(EmeraldStencilShapedRecipe.Type.INSTANCE);
        List<NetheriteStencilShapedRecipe> netheriteStencilTableRecipes = recipeManager.getAllRecipesFor(NetheriteStencilShapedRecipe.Type.INSTANCE);
        List<AmethineCrystalStencilShapedRecipe> amethineCrystalStencilTableRecipes = recipeManager.getAllRecipesFor(AmethineCrystalStencilShapedRecipe.Type.INSTANCE);
        List<IronFurnaceRecipe> ironFurnaceRecipes = recipeManager.getAllRecipesFor(IronFurnaceRecipe.Type.INSTANCE);
        List<DiamondFurnaceRecipe> diamondFurnaceRecipes = recipeManager.getAllRecipesFor(DiamondFurnaceRecipe.Type.INSTANCE);
        List<EmeraldFurnaceRecipe> emeraldFurnaceRecipes = recipeManager.getAllRecipesFor(EmeraldFurnaceRecipe.Type.INSTANCE);
        List<NetheriteFurnaceRecipe> netheriteFurnaceRecipes = recipeManager.getAllRecipesFor(NetheriteFurnaceRecipe.Type.INSTANCE);
        List<AmethineCrystalFurnaceRecipe> amethineCrystalFurnaceRecipes = recipeManager.getAllRecipesFor(AmethineCrystalFurnaceRecipe.Type.INSTANCE);
        List<StarFactoryRecipe> starFactoryRecipes = recipeManager.getAllRecipesFor(StarFactoryRecipe.Type.INSTANCE);

        registration.addRecipes(StencilTableCategory.STENCIL_TABLE_TYPE, stencilTableRecipes);
        registration.addRecipes(IronStencilTableCategory.IRON_STENCIL_TABLE_TYPE, ironStencilTableRecipes);
        registration.addRecipes(DiamondStencilTableCategory.DIAMOND_STENCIL_TABLE_TYPE, diamondStencilTableRecipes);
        registration.addRecipes(EmeraldStencilTableCategory.EMERALD_STENCIL_TABLE_TYPE, emeraldStencilTableRecipes);
        registration.addRecipes(NetheriteStencilTableCategory.NETHERITE_STENCIL_TABLE_TYPE, netheriteStencilTableRecipes);
        registration.addRecipes(AmethineCrystalStencilTableCategory.AMETHINE_CRYSTAL_STENCIL_TABLE_TYPE, amethineCrystalStencilTableRecipes);
        registration.addRecipes(IronFurnaceCategory.IRON_FURNACE_TYPE, ironFurnaceRecipes);
        registration.addRecipes(DiamondFurnaceCategory.DIAMOND_FURNACE_TYPE, diamondFurnaceRecipes);
        registration.addRecipes(EmeraldFurnaceCategory.EMERALD_FURNACE_TYPE, emeraldFurnaceRecipes);
        registration.addRecipes(NetheriteFurnaceCategory.NETHERITE_FURNACE_TYPE, netheriteFurnaceRecipes);
        registration.addRecipes(AmethineCrystalFurnaceCategory.AMETHINE_CRYSTAL_FURNACE_TYPE, amethineCrystalFurnaceRecipes);
        registration.addRecipes(StarFactoryCategory.STAR_FACTORY_TYPE, starFactoryRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(StencilTableScreen.class, 90, 35, 22, 15,
                StencilTableCategory.STENCIL_TABLE_TYPE);
        registration.addRecipeClickArea(IronStencilTableScreen.class, 90, 35, 22, 15,
                IronStencilTableCategory.IRON_STENCIL_TABLE_TYPE);
        registration.addRecipeClickArea(DiamondStencilTableScreen.class, 90, 35, 22, 15,
                DiamondStencilTableCategory.DIAMOND_STENCIL_TABLE_TYPE);
        registration.addRecipeClickArea(EmeraldStencilTableScreen.class, 104, 37, 22, 15,
                EmeraldStencilTableCategory.EMERALD_STENCIL_TABLE_TYPE);
        registration.addRecipeClickArea(NetheriteStencilTableScreen.class, 104, 37, 22, 15,
                NetheriteStencilTableCategory.NETHERITE_STENCIL_TABLE_TYPE);
        registration.addRecipeClickArea(AmethineCrystalStencilTableScreen.class, 125, 26, 25, 15,
                AmethineCrystalStencilTableCategory.AMETHINE_CRYSTAL_STENCIL_TABLE_TYPE);
        registration.addRecipeClickArea(IronFurnaceScreen.class, 80, 35, 22, 15,
                IronFurnaceCategory.IRON_FURNACE_TYPE);
        registration.addRecipeClickArea(DiamondFurnaceScreen.class, 80, 35, 22, 15,
                DiamondFurnaceCategory.DIAMOND_FURNACE_TYPE);
        registration.addRecipeClickArea(EmeraldFurnaceScreen.class, 80, 35, 22, 15,
                EmeraldFurnaceCategory.EMERALD_FURNACE_TYPE);
        registration.addRecipeClickArea(NetheriteFurnaceScreen.class, 80, 35, 22, 15,
                NetheriteFurnaceCategory.NETHERITE_FURNACE_TYPE);
        registration.addRecipeClickArea(AmethineCrystalFurnaceScreen.class, 80, 35, 22, 15,
                AmethineCrystalFurnaceCategory.AMETHINE_CRYSTAL_FURNACE_TYPE);
        registration.addRecipeClickArea(StarFactoryScreen.class, 80, 35, 22, 15,
                StarFactoryCategory.STAR_FACTORY_TYPE);
    }
}
