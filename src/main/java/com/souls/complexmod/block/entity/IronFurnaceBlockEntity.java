package com.souls.complexmod.block.entity;

import java.util.Optional;

import javax.annotation.Nullable;

import com.souls.complexmod.block.custom.IronFurnaceBlock;
import com.souls.complexmod.menu.IronFurnaceMenu;
import com.souls.complexmod.recipe.IronFurnaceRecipe;

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
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

public class IronFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private static final Component TITLE = Component.translatable("gui.complexmod.iron_furnace");

    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };
    
    private static final int INPUT_1_SLOT = 0;
    private static final int INPUT_2_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    private int burnTime = 0;
    private int burnTimeTotal = 180; //total burn time
    private int ticks = 0;

    private boolean isBurning = false;

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    public IronFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IRON_FURNACE_BLOCK_ENTITY.get(), pos, state);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(IronFurnaceBlock.LIT, isBurning), 3);

        if (hasRecipe()) {
            isBurning = true;
            this.ticks++;

            if(this.ticks >= burnTimeTotal) { //every 9 seconds
                craftItem();
                dropXP();
                this.ticks = 0;
            }

            //increment burn time and reset if necessary
            burnTime++;
            if (burnTime >= burnTimeTotal) {
                burnTime = 0; //reset burn time
            }

            data.set(0, burnTime); //update data container
            data.set(1, burnTimeTotal); //update data container with total burn time

            System.out.println("recipe found");
        } else {
            isBurning = false;
            this.ticks = 0;
        }
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("iron_furnace_inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.put("iron_furnace_inventory", itemHandler.serializeNBT());
    }

    //block and chunk update stuff
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this); //sends the block entity data to the client
    }

    //menu provider stuff
    @Override
    public Component getDisplayName() {
        return TITLE;
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

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new IronFurnaceMenu(id, playerInventory, this, this.data);
    }

    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> burnTime;
                case 1 -> burnTimeTotal;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> burnTime = value;
                case 1 -> burnTimeTotal = value;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    private void craftItem() {
        Optional<IronFurnaceRecipe> recipe = getCurrentRecipe();
        ItemStack resultItem = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUT_1_SLOT, 1, false);
        this.itemHandler.extractItem(INPUT_2_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(resultItem.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + resultItem.getCount()));
    }

    private boolean hasRecipe() {
        Optional<IronFurnaceRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack resultItem = recipe.get().getResultItem(null);

        return canInsertAmountIntoOutputSlot(resultItem.getCount()) && canInsertItemIntoOutputSlot(resultItem.getItem());
    }

    private Optional<IronFurnaceRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(IronFurnaceRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int amount) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + amount <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private void dropXP() {
        Optional<IronFurnaceRecipe> recipe = getCurrentRecipe();
        if (recipe.isPresent()) {
            float xp = recipe.get().getXP();
            if (xp > 0) {
                int xpToDrop = (int) Math.round(xp);
                while (xpToDrop > 0) {
                    int xpOrbValue = ExperienceOrb.getExperienceValue(xpToDrop);
                    xpToDrop -= xpOrbValue;
                    ExperienceOrb orb = new ExperienceOrb(level, worldPosition.getX() + 0.5, worldPosition.getY() + 1, worldPosition.getZ() + 0.5, xpOrbValue);
                    level.addFreshEntity(orb);
                }
            }
        }
    }
}
