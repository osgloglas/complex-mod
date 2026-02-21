package com.souls.complexmod.block.custom;

import javax.annotation.Nullable;

import com.souls.complexmod.block.entity.DiamondStencilTableBlockEntity;
import com.souls.complexmod.block.entity.EmeraldStencilTableBlockEntity;
import com.souls.complexmod.block.entity.ModBlockEntities;
import com.souls.complexmod.block.entity.StencilTableBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class EmeraldStencilTableBlock extends Block implements EntityBlock {
    public EmeraldStencilTableBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EmeraldStencilTableBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        return blockEntity instanceof MenuProvider ? (MenuProvider)blockEntity : null;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) {
            if (player.isCrouching()) {
                return InteractionResult.PASS;
            }

            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof EmeraldStencilTableBlockEntity bentity) {
                NetworkHooks.openScreen((ServerPlayer) player, (EmeraldStencilTableBlockEntity)bentity, pos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }

        return createTickerHelper(type, ModBlockEntities.EMERALD_STENCIL_TABLE_BLOCK_ENTITY.get(),
                (level1, pos1, state1, entity) -> entity.tick(level1, pos1, state1));
    }

    private static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<E> createTickerHelper(
        BlockEntityType<E> givenType,
        BlockEntityType<A> expectedType,
        BlockEntityTicker<? super A> ticker) {
        // TODO Auto-generated method stub
        return givenType == expectedType ? (BlockEntityTicker<E>) ticker : null;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof EmeraldStencilTableBlockEntity be) {
                var inventory = be.getItemHandler();

                for (int i = 0; i < inventory.getSlots(); i++) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i));
                }
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }
}
