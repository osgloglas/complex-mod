package com.souls.complexmod.compat;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.block.ModBlocks;
import com.souls.complexmod.recipe.IronStencilShapedRecipe;

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

public class IronStencilTableCategory implements IRecipeCategory<IronStencilShapedRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(ComplexMod.MOD_ID, "iron_stencil_table");
    public static final ResourceLocation TEXTURE = new ResourceLocation(ComplexMod.MOD_ID, "textures/gui/iron_stencil_table.png");

    public static final RecipeType<IronStencilShapedRecipe> IRON_STENCIL_TABLE_TYPE =
            new RecipeType<>(UID, IronStencilShapedRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public IronStencilTableCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 4, 4, 168, 76);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.IRON_STENCIL_TABLE.get()));
    }

    @Override
    public RecipeType<IronStencilShapedRecipe> getRecipeType() {
        return IRON_STENCIL_TABLE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.complexmod.iron_stencil_table");
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
    public void setRecipe(IRecipeLayoutBuilder builder, IronStencilShapedRecipe recipe, IFocusGroup focuses) {
        int index = 0;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                    int x = 26 + (column * 18);
                    int y = 13 + (row * 18);
                    builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                            .addIngredients(recipe.getIngredients().get(index));
                    index++;
            }
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 120, 31)
                .addItemStack(recipe.getResultItem(null));
    }
}