package com.souls.complexmod.menu;

import com.souls.complexmod.ModMenuTypes;
import com.souls.complexmod.block.entity.FurnaceStackBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FurnaceStackMenu extends AbstractContainerMenu {
    private final Level level;
    private final BlockPos pos;

    public FurnaceStackMenu(int containerId, Inventory inv, FriendlyByteBuf buf) {
        super(ModMenuTypes.FURNACE_STACK_MENU.get(), containerId);
        this.level = inv.player.level();
        this.pos = buf.readBlockPos();
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    public BlockPos getBlockPos() {
        return pos;
    }
}
