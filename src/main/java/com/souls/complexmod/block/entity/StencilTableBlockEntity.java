package com.souls.complexmod.block.entity;

import javax.annotation.Nullable;

import com.souls.complexmod.menu.StencilTableMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

public class StencilTableBlockEntity extends BlockEntity implements MenuProvider {
    private static final Component TITLE = Component.translatable("gui.complexmod.stencil_table");

    private final ItemStackHandler itemHandler = new ItemStackHandler(10);

    private static final int INPUT_1_SLOT = 0;
    private static final int INPUT_2_SLOT = 1;
    private static final int INPUT_3_SLOT = 2;
    private static final int INPUT_4_SLOT = 3;
    private static final int INPUT_5_SLOT = 4;
    private static final int INPUT_6_SLOT = 5;
    private static final int INPUT_7_SLOT = 6;
    private static final int INPUT_8_SLOT = 7;
    private static final int INPUT_9_SLOT = 8;
    private static final int OUTPUT_SLOT = 9;

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    public StencilTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STENCIL_TABLE_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        lazyItemHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return TITLE;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new StencilTableMenu(id, playerInventory, this);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }
    
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    //save and load
    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("Items"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("Items", itemHandler.serializeNBT());
        super.saveAdditional(nbt);
    }
}
