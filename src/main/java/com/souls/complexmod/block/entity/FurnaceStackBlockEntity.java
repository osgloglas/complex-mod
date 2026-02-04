package com.souls.complexmod.block.entity;

import javax.annotation.Nullable;

import org.antlr.v4.runtime.misc.NotNull;
import org.checkerframework.checker.units.qual.s;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.fluid.ModFluids;
import com.souls.complexmod.menu.FurnaceStackMenu;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class FurnaceStackBlockEntity extends BlockEntity implements MenuProvider {
    private static final Component TITLE = Component.translatable("gui.complexmod.furnace_stack");
    private int ticks = 0;
    private final FluidTank slagTank = new FluidTank(8000); //8 buckets capacity

    public FurnaceStackBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FURNACE_STACK_BLOCK_ENTITY.get(), pos, state);
    }

    public void tick() {
        BlockPos belowPos = this.worldPosition.below();
        BlockState belowState = this.level.getBlockState(belowPos);

        if (!(belowState.getBlock() instanceof FurnaceBlock)) {
            return; //no furncace, no ticking
        }

        boolean lit = belowState.getValue(FurnaceBlock.LIT);
        if (!lit) {
            return; //furnace not lit, no ticking
        }

        if(this.ticks++ % 200 == 0) { //every 10 seconds
            FluidStack lava = new FluidStack(ModFluids.MIXED_SLAG_SOURCE.get(), 100);
            slagTank.fill(lava, IFluidHandler.FluidAction.EXECUTE);

            System.out.println("Generating 100mb of mixed slag. Current slag amount: " + slagTank.getFluidAmount() + "mb");
            setChanged();
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL); //sync to client
        }

        double x = worldPosition.getX() + 0.5;
        double y = worldPosition.getY() + 1.0;
        double z = worldPosition.getZ() + 0.5;

        level.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, x, y, z, 0, 0.05, 0);


        //increment tick counter
        ticks++;
    }

    //fluid tank capability
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            return LazyOptional.of(() -> slagTank).cast();
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
        return new FurnaceStackMenu(id, playerInventory, this);
    }
}