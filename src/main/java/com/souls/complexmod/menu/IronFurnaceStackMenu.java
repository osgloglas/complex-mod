package com.souls.complexmod.menu;

import com.souls.complexmod.block.ModBlocks;
import com.souls.complexmod.block.entity.IronFurnaceStackBlockEntity;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class IronFurnaceStackMenu extends AbstractContainerMenu {
    private final IronFurnaceStackBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;
    private final ContainerData data;

    //client constructor
    public IronFurnaceStackMenu(int containerId, Inventory pInv, FriendlyByteBuf buf) {
        this(containerId, pInv, (IronFurnaceStackBlockEntity) pInv.player.level().getBlockEntity(buf.readBlockPos()),
                new SimpleContainerData(2));
    }

    //server constructor
    public IronFurnaceStackMenu(int containerId, Inventory pInv, IronFurnaceStackBlockEntity blockEntity, ContainerData extraData) {
        super(ModMenus.IRON_FURNACE_STACK_MENU.get(), containerId);

        this.blockEntity = blockEntity;
        this.data = extraData;
        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        addDataSlots(extraData);

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
        return stillValid(this.levelAccess, pPlayer, ModBlocks.IRON_FURNACE_STACK.get());
    }

    //burning getters
    public int getBurnTime() {
        return data.get(0);
    }

    public int getBurnTimeTotal() {
        return data.get(1);
    }

    //flame
    public boolean isBurning() {
        return data.get(0) > 0;
    }

    public int getBurnProgress(int scale) {
        int burn = data.get(0);
        int burnTotal = data.get(1);

        if (burnTotal == 0) {
            return 0;
        }
        return burn * scale / burnTotal;
    }

    //slag tank getter
    public int getSlagAmount() {
        return blockEntity.getSlagAmount();
    }

    public int getSlagCapacity() {
        return blockEntity.getTank().getCapacity();
    }

    public int getSlagProgress(int scale) {
        int slagAmount = getSlagAmount();
        int slagCapacity = getSlagCapacity();

        if (slagCapacity == 0) {
            return 0;
        }
        return slagAmount * scale / slagCapacity;
    }

    public IronFurnaceStackBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}
