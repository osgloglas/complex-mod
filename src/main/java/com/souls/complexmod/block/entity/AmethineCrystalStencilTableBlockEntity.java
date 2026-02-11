package com.souls.complexmod.block.entity;

import java.util.Optional;

import javax.annotation.Nullable;

import com.souls.complexmod.menu.AmethineCrystalStencilTableMenu;
import com.souls.complexmod.menu.DiamondStencilTableMenu;
import com.souls.complexmod.menu.EmeraldStencilTableMenu;
import com.souls.complexmod.menu.IronStencilTableMenu;
import com.souls.complexmod.menu.NetheriteStencilTableMenu;
import com.souls.complexmod.recipe.AmethineCrystalStencilShapedRecipe;
import com.souls.complexmod.recipe.DiamondStencilShapedRecipe;
import com.souls.complexmod.recipe.EmeraldStencilShapedRecipe;
import com.souls.complexmod.recipe.IronStencilShapedRecipe;
import com.souls.complexmod.recipe.NetheriteStencilShapedRecipe;

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

public class AmethineCrystalStencilTableBlockEntity extends BlockEntity implements MenuProvider {
    private static final Component TITLE = Component.translatable("gui.complexmod.amethine_crystal_stencil_table");

    private final ItemStackHandler itemHandler = new ItemStackHandler(50) {
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
    private static final int INPUT_10_SLOT = 9;
    private static final int INPUT_11_SLOT = 10;
    private static final int INPUT_12_SLOT = 11;
    private static final int INPUT_13_SLOT = 12;
    private static final int INPUT_14_SLOT = 13;
    private static final int INPUT_15_SLOT = 14;
    private static final int INPUT_16_SLOT = 15;
    private static final int INPUT_17_SLOT = 16;
    private static final int INPUT_18_SLOT = 17;
    private static final int INPUT_19_SLOT = 18;
    private static final int INPUT_20_SLOT = 19;
    private static final int INPUT_21_SLOT = 20;
    private static final int INPUT_22_SLOT = 21;
    private static final int INPUT_23_SLOT = 22;
    private static final int INPUT_24_SLOT = 23;
    private static final int INPUT_25_SLOT = 24;
    private static final int INPUT_26_SLOT = 25;
    private static final int INPUT_27_SLOT = 26;
    private static final int INPUT_28_SLOT = 27;
    private static final int INPUT_29_SLOT = 28;
    private static final int INPUT_30_SLOT = 29;
    private static final int INPUT_31_SLOT = 30;
    private static final int INPUT_32_SLOT = 31;
    private static final int INPUT_33_SLOT = 32;
    private static final int INPUT_34_SLOT = 33;
    private static final int INPUT_35_SLOT = 34;
    private static final int INPUT_36_SLOT = 35;
    private static final int INPUT_37_SLOT = 36;
    private static final int INPUT_38_SLOT = 37;
    private static final int INPUT_39_SLOT = 38;
    private static final int INPUT_40_SLOT = 39;
    private static final int INPUT_41_SLOT = 40;
    private static final int INPUT_42_SLOT = 41;
    private static final int INPUT_43_SLOT = 42;
    private static final int INPUT_44_SLOT = 43;
    private static final int INPUT_45_SLOT = 44;
    private static final int INPUT_46_SLOT = 45;
    private static final int INPUT_47_SLOT = 46;
    private static final int INPUT_48_SLOT = 47;
    private static final int INPUT_49_SLOT = 48;
    private static final int OUTPUT_SLOT = 49;
    

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    public AmethineCrystalStencilTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AMETHINE_CRYSTAL_STENCIL_TABLE_BLOCK_ENTITY.get(), pos, state);
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
        return new AmethineCrystalStencilTableMenu(id, playerInventory, this);
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
        itemHandler.deserializeNBT(nbt.getCompound("amethine_crystal_stencil_table_inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.put("amethine_crystal_stencil_table_inventory", itemHandler.serializeNBT());
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
        Optional<AmethineCrystalStencilShapedRecipe> recipe = getCurrentRecipe();
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
        this.itemHandler.extractItem(INPUT_10_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_11_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_12_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_13_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_14_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_15_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_16_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_17_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_18_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_19_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_20_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_21_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_22_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_23_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_24_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_25_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_26_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_27_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_28_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_29_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_30_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_31_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_32_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_33_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_34_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_35_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_36_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_37_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_38_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_39_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_40_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_41_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_42_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_43_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_44_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_45_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_46_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_47_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_48_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_49_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(resultItem.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + resultItem.getCount()));
    }

    private boolean hasRecipe() {
        Optional<AmethineCrystalStencilShapedRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack resultItem = recipe.get().getResultItem(null);

        return canInsertAmountIntoOutputSlot(resultItem.getCount()) && canInsertItemIntoOutputSlot(resultItem.getItem());
    }

    private Optional<AmethineCrystalStencilShapedRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(AmethineCrystalStencilShapedRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int amount) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + amount <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
