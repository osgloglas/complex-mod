package com.souls.complexmod.compat;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.block.ModBlocks;
import com.souls.complexmod.recipe.AmethineCrystalStencilShapedRecipe;

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

public class AmethineCrystalStencilTableCategory implements IRecipeCategory<AmethineCrystalStencilShapedRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(ComplexMod.MOD_ID, "amethine_crystal_stencil_table");
    public static final ResourceLocation TEXTURE = new ResourceLocation(ComplexMod.MOD_ID, "textures/gui/amethine_crystal_stencil_table.png");

    public static final RecipeType<AmethineCrystalStencilShapedRecipe> AMETHINE_CRYSTAL_STENCIL_TABLE_TYPE =
            new RecipeType<>(UID, AmethineCrystalStencilShapedRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public AmethineCrystalStencilTableCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 4, 4, 191, 149);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.AMETHINE_CRYSTAL_STENCIL_TABLE.get()));
    }

    @Override
    public RecipeType<AmethineCrystalStencilShapedRecipe> getRecipeType() {
        return AMETHINE_CRYSTAL_STENCIL_TABLE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.complexmod.amethine_crystal_stencil_table");
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
    public void setRecipe(IRecipeLayoutBuilder builder, AmethineCrystalStencilShapedRecipe recipe, IFocusGroup focuses) {
        int index = 0;

        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                    int x = 4 + (column * 18);
                    int y = 12 + (row * 18);
                    builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                            .addIngredients(recipe.getIngredients().get(index));
                    index++;
            }
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 167, 58)
                .addItemStack(recipe.getResultItem(null));
    }
}