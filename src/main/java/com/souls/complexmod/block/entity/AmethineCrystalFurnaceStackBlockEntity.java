package com.souls.complexmod.block.entity;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.souls.complexmod.block.custom.AmethineCrystalFurnaceBlock;
import com.souls.complexmod.block.custom.EmeraldFurnaceBlock;
import com.souls.complexmod.block.custom.IronFurnaceBlock;
import com.souls.complexmod.block.custom.NetheriteFurnaceBlock;
import com.souls.complexmod.fluid.ModFluids;
import com.souls.complexmod.menu.AmethineCrystalFurnaceStackMenu;
import com.souls.complexmod.menu.EmeraldFurnaceStackMenu;
import com.souls.complexmod.menu.FurnaceStackMenu;
import com.souls.complexmod.menu.IronFurnaceStackMenu;
import com.souls.complexmod.menu.NetheriteFurnaceStackMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class AmethineCrystalFurnaceStackBlockEntity extends BlockEntity implements MenuProvider {
    private static final Component TITLE = Component.translatable("gui.complexmod.amethine_crystal_furnace_stack");
    private int burnTime = 0;
    private int burnTimeTotal = 200; //total burn time
    private int ticks = 0;
    private final FluidTank slagTank = new FluidTank(16000); //14 buckets capacity

    public AmethineCrystalFurnaceStackBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AMETHINE_CRYSTAL_FURNACE_STACK_BLOCK_ENTITY.get(), pos, state);
    }

    public void tick() {
        BlockPos belowPos = this.worldPosition.below();
        BlockState belowState = this.level.getBlockState(belowPos);
        BlockEntity bentity = level.getBlockEntity(belowPos);

        if (!(bentity instanceof AmethineCrystalFurnaceBlockEntity amethineCrystalFurnaceBlock)) {
            return; //not a furnace block
        }

        boolean lit = belowState.getValue(AmethineCrystalFurnaceBlock.LIT);
        this.ticks++;

        if (lit) {
            if (level.random.nextFloat() < 0.1) { //10% chance every tick to spawn particles
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
                        worldPosition.getX() + 0.5,
                        worldPosition.getY() + 0.8,
                        worldPosition.getZ() + 0.5,
                        0, 0.0, 0.07, 0.0, 0.5);
                }
            }
        }

        if(this.ticks >= burnTimeTotal && lit) { //every 10 seconds
            FluidStack lava = new FluidStack(ModFluids.BEDROCK_SLAG_SOURCE.get(), 100);
            slagTank.fill(lava, IFluidHandler.FluidAction.EXECUTE);

            System.out.println("Generating 100mb of bedrock slag. Current slag amount: " + slagTank.getFluidAmount() + "mb");
            setChanged();
            this.ticks = 0;
        }
        this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL); //sync to client

        //increment burn time and reset if necessary
        burnTime++;
        if (burnTime >= burnTimeTotal) {
            burnTime = 0; //reset burn time
        }
        data.set(0, burnTime); //update data container
        data.set(1, burnTimeTotal); //update data container with total burn time
    }

    //fluid tank capability
    private final LazyOptional<IFluidHandler> slagTankOptional = LazyOptional.of(() -> slagTank);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            return slagTankOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        slagTank.readFromNBT(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        slagTank.writeToNBT(nbt);
    }

    //fluid getter
    public FluidTank getTank() {
        return this.slagTank;
    }

    public int getSlagAmount() {
        return this.slagTank.getFluidAmount();
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

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new AmethineCrystalFurnaceStackMenu(id, playerInventory, this, this.data);
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
