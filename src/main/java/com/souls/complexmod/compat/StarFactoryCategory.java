package com.souls.complexmod.compat;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.block.ModBlocks;
import com.souls.complexmod.recipe.StarFactoryRecipe;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class StarFactoryCategory implements IRecipeCategory<StarFactoryRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(ComplexMod.MOD_ID, "star_factory");
    public static final ResourceLocation TEXTURE = new ResourceLocation(ComplexMod.MOD_ID, "textures/gui/star_factory.png");

    public static final RecipeType<StarFactoryRecipe> STAR_FACTORY_TYPE =
            new RecipeType<>(UID, StarFactoryRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public StarFactoryCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 4, 4, 168, 76);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.STAR_FACTORY.get()));
    }

    @Override
    public RecipeType<StarFactoryRecipe> getRecipeType() {
        return STAR_FACTORY_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.complexmod.star_factory");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, StarFactoryRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 52, 13)
                .addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 52, 49)
                .addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 112, 31)
                .addItemStack(recipe.getResultItem(null));
    }
}