package com.souls.complexmod.block.custom;

import javax.annotation.Nullable;

import com.google.common.graph.Network;
import com.souls.complexmod.block.entity.FurnaceStackBlockEntity;
import com.souls.complexmod.block.entity.ModBlockEntities;
import com.souls.complexmod.fluid.ModFluids;
import com.souls.complexmod.item.ModItems;
import com.souls.complexmod.util.ClientHooks;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkHooks;

public class FurnaceStackBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public FurnaceStackBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.FURNACE_STACK_BLOCK_ENTITY.get().create(pos, state);
    }

    //facing property stuff
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    //ticking
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : (level0, pos0, state0, blockEntity) -> ((FurnaceStackBlockEntity) blockEntity).tick();
    }

    //right click interaction stuff
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            if (player.isCrouching()) {
                return InteractionResult.PASS;
            }

            ItemStack heldItem = player.getItemInHand(hand);

            BlockEntity entity = level.getBlockEntity(pos);

            //if player hold bucket
            if (heldItem.is(Items.BUCKET)) {
                if (entity instanceof FurnaceStackBlockEntity stack) {
                    if (stack.getTank().getFluidAmount() >= 1000 && stack.getTank().getFluid().getFluid() == (ModFluids.MIXED_SLAG_SOURCE.get())) {
                        //drain lava
                        stack.getTank().drain(1000, IFluidHandler.FluidAction.EXECUTE);

                        //replace bucket with lava bucket
                        if (!player.isCreative()) {
                            heldItem.shrink(1);
                            player.addItem(new ItemStack(ModItems.MIXED_SLAG_BUCKET.get()));
                        } else {
                            player.addItem(new ItemStack(ModItems.MIXED_SLAG_BUCKET.get()));
                        }
                        level.playSound(null, pos, SoundEvents.BUCKET_FILL_LAVA, SoundSource.BLOCKS, 1.0f, 1.0f);
                        return InteractionResult.sidedSuccess(level.isClientSide());
                    } else {
                        player.sendSystemMessage(Component.literal("Not enough slag in the tank to fill a bucket!"));
                        return InteractionResult.sidedSuccess(level.isClientSide());
                    }
                }
            }

            if (entity instanceof FurnaceStackBlockEntity blockEntity) {

                //open gui
                NetworkHooks.openScreen((ServerPlayer) player, blockEntity, blockEntity.getBlockPos());
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
