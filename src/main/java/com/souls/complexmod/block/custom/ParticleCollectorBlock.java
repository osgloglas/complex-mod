package com.souls.complexmod.block.custom;

import javax.annotation.Nullable;

import com.souls.complexmod.block.entity.ModBlockEntities;
import com.souls.complexmod.block.entity.ParticleCollectorBlockEntity;
import com.souls.complexmod.block.entity.StencilTableBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ParticleCollectorBlock extends Block implements EntityBlock {
    public ParticleCollectorBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.PARTICLE_COLLECTOR_BLOCK_ENTITY.get().create(pos, state);
    }

    //ticking
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : (level0, pos0, state0, blockEntity) -> ((ParticleCollectorBlockEntity) blockEntity).tick();
    }

    //right click interaction stuff
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) {
            if (player.isCrouching()) {
                return InteractionResult.PASS;
            }

            BlockEntity entity = level.getBlockEntity(pos);

            if (entity instanceof ParticleCollectorBlockEntity blockEntity) {

                //open gui
                NetworkHooks.openScreen((ServerPlayer) player, blockEntity, blockEntity.getBlockPos());
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof ParticleCollectorBlockEntity be) {
                var inventory = be.getItemHandler();

                for (int i = 0; i < inventory.getSlots(); i++) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i));
                }
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }
}
