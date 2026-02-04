package com.souls.complexmod.menu;

import com.souls.complexmod.block.ModBlocks;
import com.souls.complexmod.block.entity.FurnaceStackBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FurnaceStackMenu extends AbstractContainerMenu {
    private final FurnaceStackBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;

    //client constructor
    public FurnaceStackMenu(int containerId, Inventory pInv, FriendlyByteBuf extraData) {
        this(containerId, pInv, pInv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    //server constructor
    public FurnaceStackMenu(int containerId, Inventory pInv, BlockEntity blockEntity) {
        super(ModMenus.FURNACE_STACK_MENU.get(), containerId);
        if (blockEntity instanceof FurnaceStackBlockEntity be) {
            this.blockEntity = be;
        } else {
            throw new IllegalStateException("Block entity is not a FurnaceStackBlockEntity");
        }

        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        createPlayerHotbar(pInv);
        createPlayerInventory(pInv);
    }
    
    private void createPlayerHotbar(Inventory pInv) {
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(pInv, column, 8 + (column * 18), 142));
        }
    }
    
    private void createPlayerInventory(Inventory pInv) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(pInv, 9 + column + (row * 9), 8 + (column * 18), 84 + (row * 18)));
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.levelAccess, pPlayer, ModBlocks.FURNACE_STACK.get());
    }

    //flame
    /*public boolean isLit() {
        return this.blockEntity.getBlockState().getValue(FurnaceBlock.LIT);
    }

    public int getBurnProgress() {
        int maxHeight = 14;
        int burn = blockEntity.getBurnTime();
        int burnTotal = blockEntity.getBurnTimeTotal();
        if (burnTotal == 0) return 0;
        return burn * maxHeight / burnTotal;
    }*/

    public FurnaceStackBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}
