package com.souls.complexmod.block.entity;

import java.util.Optional;

import javax.annotation.Nullable;

import com.souls.complexmod.menu.IronStencilTableMenu;
import com.souls.complexmod.menu.StencilTableMenu;
import com.souls.complexmod.recipe.IronStencilShapedRecipe;
import com.souls.complexmod.recipe.StencilShapedRecipe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

public class IronStencilTableBlockEntity extends BlockEntity implements MenuProvider {
    private static final Component TITLE = Component.translatable("gui.complexmod.iron_stencil_table");

    private final ItemStackHandler itemHandler = new ItemStackHandler(10) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };

    //this is for a FURNACE
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

    public IronStencilTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IRON_STENCIL_TABLE_BLOCK_ENTITY.get(), pos, state);
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
        return new IronStencilTableMenu(id, playerInventory, this);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }
    
    @Override
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
        itemHandler.deserializeNBT(nbt.getCompound("iron_stencil_table_inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.put("iron_stencil_table_inventory", itemHandler.serializeNBT());
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    //this is crafting logic for a FURNACE
    public void tick(Level level, BlockPos pos, BlockState state) {
        if (hasRecipe()) {
            craftItem();
        }
    }

    private void craftItem() {
        Optional<IronStencilShapedRecipe> recipe = getCurrentRecipe();
        ItemStack resultItem = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUT_1_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_2_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_3_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_4_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_5_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_6_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_7_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_8_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_9_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(resultItem.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + resultItem.getCount()));
    }

    private boolean hasRecipe() {
        Optional<IronStencilShapedRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack resultItem = recipe.get().getResultItem(null);

        return canInsertAmountIntoOutputSlot(resultItem.getCount()) && canInsertItemIntoOutputSlot(resultItem.getItem());
    }

    private Optional<IronStencilShapedRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(IronStencilShapedRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int amount) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + amount <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
