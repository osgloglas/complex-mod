package com.souls.complexmod.screen;

import org.jetbrains.annotations.NotNull;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.menu.IronFurnaceMenu;
import com.souls.complexmod.menu.StencilTableMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class IronFurnaceScreen extends AbstractContainerScreen<IronFurnaceMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ComplexMod.MOD_ID, "textures/gui/iron_furnace.png");

    private final int imageWidth, imageHeight;
    private int leftPos, topPos;

    public IronFurnaceScreen(IronFurnaceMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
    }

    //rendering
    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        renderBackground(pGuiGraphics);
        pGuiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        renderBurnProgress(pGuiGraphics, this.leftPos, this.topPos);
    }

    //burn progress render method
    private void renderBurnProgress(GuiGraphics pGuiGraphics, int leftPos, int topPos) {
        if (!menu.isBurning()) return;

        int burnHeight = menu.getBurnProgress(14);

        pGuiGraphics.blit(TEXTURE,
                leftPos + 81, //x position on screen
                topPos + 51 + (14 - burnHeight), //y position on screen
                176, //x position on texture
                burnHeight, //y position on texture
                14, //width of the burn icon
                14 - burnHeight); //height of the burn icon
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);
        renderTooltip(graphics, mouseX, mouseY);
    }

    //no pausing
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
