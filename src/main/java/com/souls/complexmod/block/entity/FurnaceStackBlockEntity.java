package com.souls.complexmod.block.entity;

import javax.annotation.Nullable;

import com.souls.complexmod.menu.FurnaceStackMenu;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class FurnaceStackBlockEntity extends BlockEntity {
    public FurnaceStackBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FURNACE_STACK_BLOCK_ENTITY.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FurnaceStackBlockEntity blockEntity) {
        if (level.isClientSide) return;

        boolean furnaceLit = isFurnaceLit(level, pos);

        if (furnaceLit != blockEntity.furnaceLit) {
            blockEntity.furnaceLit = furnaceLit;
            blockEntity.setChanged();
        }

        if (furnaceLit) {
            System.out.println("FurnaceStack at " + pos + " detects lit furnace below.");
        }
    }

    private boolean furnaceLit = false;

    public boolean isFurnaceLit() {
        return furnaceLit;
    }

    private static boolean isFurnaceLit(Level level, BlockPos pos) {
        BlockPos below = pos.below();
        BlockState belowState = level.getBlockState(below);

        if (!(belowState.getBlock() instanceof FurnaceBlock)) {
            return false;
        }

        return belowState.getValue(FurnaceBlock.LIT);
    }
}