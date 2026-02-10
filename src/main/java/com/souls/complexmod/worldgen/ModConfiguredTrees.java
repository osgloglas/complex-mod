package com.souls.complexmod.worldgen;

import com.souls.complexmod.ComplexMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ModConfiguredTrees {
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIMELESS_TREE =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ComplexMod.MOD_ID, "timeless_tree"));

    public static void register() {}
}
