package com.souls.complexmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TimelessLeavesBlock extends LeavesBlock {
    public TimelessLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(PERSISTENT, false)
                .setValue(DISTANCE, 1));
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < 0.8F) {
            double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 2;
            double y = pos.getY();
            double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 2;

            double vx = (random.nextDouble() - 0.5) * 0.02;
            double vy = -0.003 + (random.nextDouble() * 0.006);
            double vz = (random.nextDouble() - 0.5) * 0.02;

            level.addParticle(ParticleTypes.ENCHANT, x, y, z, vx, vy, vz);
        }
    }
}
