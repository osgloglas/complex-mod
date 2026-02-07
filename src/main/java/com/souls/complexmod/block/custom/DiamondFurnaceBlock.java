package com.souls.complexmod.block.custom;

import javax.annotation.Nullable;

import com.souls.complexmod.block.entity.DiamondFurnaceBlockEntity;
import com.souls.complexmod.block.entity.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class DiamondFurnaceBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public DiamondFurnaceBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.DIAMOND_FURNACE_BLOCK_ENTITY.get().create(pos, state);
    }

    //right click interaction stuff
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) {
            if (player.isCrouching()) {
                return InteractionResult.PASS;
            }

            BlockEntity entity = level.getBlockEntity(pos);

            if (entity instanceof DiamondFurnaceBlockEntity blockEntity) {

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

    //facing property stuff
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    //ticking
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }

        return createTickerHelper(type, ModBlockEntities.DIAMOND_FURNACE_BLOCK_ENTITY.get(),
                (level1, pos1, state1, entity) -> entity.tick(level1, pos1, state1));
    }

    private static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<E> createTickerHelper(
        BlockEntityType<E> givenType,
        BlockEntityType<A> expectedType,
        BlockEntityTicker<? super A> ticker) {
        // TODO Auto-generated method stub
        return givenType == expectedType ? (BlockEntityTicker<E>) ticker : null;
    }
}
