package com.souls.complexmod.worldgen.tree;

import com.souls.complexmod.worldgen.ModConfiguredTrees;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class TimelessTreeGrower extends AbstractTreeGrower {
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
        return ModConfiguredTrees.TIMELESS_TREE;
    }
}
