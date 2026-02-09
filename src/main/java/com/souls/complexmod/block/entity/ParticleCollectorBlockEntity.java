package com.souls.complexmod.block.entity;

import javax.annotation.Nullable;

import com.souls.complexmod.item.ModItems;
import com.souls.complexmod.menu.ParticleCollectorMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

public class ParticleCollectorBlockEntity extends BlockEntity implements MenuProvider {
    private static final Component TITLE = Component.translatable("gui.complexmod.particle_collector");

    private final ItemStackHandler itemHandler = new ItemStackHandler(1);
    private static final int OUTPUT_SLOT = 0;

    private int burnTime = 0;
    private int burnTimeTotal = 6000; //total burn time
    private int ticks = 0;

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    public ParticleCollectorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PARTICLE_COLLECTOR_BLOCK_ENTITY.get(), pos, state);
    }

    public void tick() {
        BlockEntity bentity = level.getBlockEntity(worldPosition);
        boolean hasSky = level.canSeeSkyFromBelowWater(worldPosition.above());

        if (!(bentity instanceof ParticleCollectorBlockEntity)) {
            return; //not a furnace block
        }

        if (!hasSky) {
            return; //can't operate without sky access
        }

        this.ticks++;

        if(this.ticks >= burnTimeTotal) { //every 5 minutes
            ItemStack outputStack = new ItemStack(ModItems.NEUTRON_CLUSTER.get());
            ItemStack current = itemHandler.getStackInSlot(OUTPUT_SLOT);

            if (current.isEmpty()) {
                itemHandler.setStackInSlot(OUTPUT_SLOT, outputStack);
            } else if (ItemStack.isSameItemSameTags(current, outputStack) && current.getCount() < current.getMaxStackSize()) {
                current.grow(1);
                itemHandler.setStackInSlot(OUTPUT_SLOT, current);
            }

            setChanged();

            this.ticks = 0;
        }

        //increment burn time and reset if necessary
        burnTime++;
        if (burnTime >= burnTimeTotal) {
            burnTime = 0; //reset burn time
        }
        data.set(0, burnTime); //update data container
        data.set(1, burnTimeTotal); //update data container with total burn time

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.ENCHANT, worldPosition.getX() + 0.5, worldPosition.getY() + 1.0, worldPosition.getZ() + 0.5, 3, 0.5, 0.5, 0.5, 0);
        }
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
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

    //menu provider stuff
    @Override
    public Component getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new ParticleCollectorMenu(id, playerInventory, this, this.data);
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
}
