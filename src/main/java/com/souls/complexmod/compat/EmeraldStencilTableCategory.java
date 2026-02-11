package com.souls.complexmod.compat;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.block.ModBlocks;
import com.souls.complexmod.recipe.EmeraldStencilShapedRecipe;

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

public class EmeraldStencilTableCategory implements IRecipeCategory<EmeraldStencilShapedRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(ComplexMod.MOD_ID, "emerald_stencil_table");
    public static final ResourceLocation TEXTURE = new ResourceLocation(ComplexMod.MOD_ID, "textures/gui/emerald_stencil_table.png");

    public static final RecipeType<EmeraldStencilShapedRecipe> EMERALD_STENCIL_TABLE_TYPE =
            new RecipeType<>(UID, EmeraldStencilShapedRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public EmeraldStencilTableCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 4, 4, 168, 128);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.EMERALD_STENCIL_TABLE.get()));
    }

    @Override
    public RecipeType<EmeraldStencilShapedRecipe> getRecipeType() {
        return EMERALD_STENCIL_TABLE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.complexmod.emerald_stencil_table");
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
    public void setRecipe(IRecipeLayoutBuilder builder, EmeraldStencilShapedRecipe recipe, IFocusGroup focuses) {
        int index = 0;

        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                    int x = 8 + (column * 18);
                    int y = 22 + (row * 18);
                    builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                            .addIngredients(recipe.getIngredients().get(index));
                    index++;
            }
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 134, 58)
                .addItemStack(recipe.getResultItem(null));
    }
}