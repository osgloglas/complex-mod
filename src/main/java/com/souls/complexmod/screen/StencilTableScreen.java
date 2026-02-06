package com.souls.complexmod.screen;

import org.jetbrains.annotations.NotNull;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.menu.StencilTableMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class StencilTableScreen extends AbstractContainerScreen<StencilTableMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ComplexMod.MOD_ID, "textures/gui/stencil_table.png");

    private final int imageWidth, imageHeight;
    private int leftPos, topPos;

    public StencilTableScreen(StencilTableMenu menu, Inventory inventory, Component title) {
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
