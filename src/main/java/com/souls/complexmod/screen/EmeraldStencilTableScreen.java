package com.souls.complexmod.screen;

import org.jetbrains.annotations.NotNull;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.menu.EmeraldStencilTableMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class EmeraldStencilTableScreen extends AbstractContainerScreen<EmeraldStencilTableMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ComplexMod.MOD_ID, "textures/gui/emerald_stencil_table.png");

    private final int imageWidth, imageHeight;
    private int leftPos, topPos;

    public EmeraldStencilTableScreen(EmeraldStencilTableMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        
        this.imageWidth = 176;
        this.imageHeight = 216;
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

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        // Draw the title
        pGuiGraphics.drawString(this.font, this.title, 8, -12, 4210752, false);
        // Draw the player's inventory label
        pGuiGraphics.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 120, 4210752, false);
    }

    //no pausing
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
