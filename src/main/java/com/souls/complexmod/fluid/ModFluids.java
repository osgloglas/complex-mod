package com.souls.complexmod.fluid;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.block.ModBlocks;
import com.souls.complexmod.item.ModItems;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, ComplexMod.MOD_ID);

    //register fluids

    //mixed slag
    public static final RegistryObject<FlowingFluid> MIXED_SLAG_SOURCE = FLUIDS.register("mixed_slag_source",
            () -> new ForgeFlowingFluid.Source(ModFluids.MIXED_SLAG_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_MIXED_SLAG = FLUIDS.register("flowing_mixed_slag",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.MIXED_SLAG_PROPERTIES));

    public static final ForgeFlowingFluid.Properties MIXED_SLAG_PROPERTIES =
            new ForgeFlowingFluid.Properties(
                    ModFluidTypes.MIXED_SLAG_TYPE,
                    ModFluids.MIXED_SLAG_SOURCE,
                    ModFluids.FLOWING_MIXED_SLAG)
                    .slopeFindDistance(4)
                    .levelDecreasePerBlock(1)
                    .tickRate(30)
                    .block(() -> ModBlocks.MIXED_SLAG_BLOCK.get())
                    .bucket(() -> ModItems.MIXED_SLAG_BUCKET.get());

        //netherite slag
        public static final RegistryObject<FlowingFluid> NETHERITE_SLAG_SOURCE = FLUIDS.register("netherite_slag_source",
                () -> new ForgeFlowingFluid.Source(ModFluids.NETHERITE_SLAG_PROPERTIES));
        public static final RegistryObject<FlowingFluid> FLOWING_NETHERITE_SLAG = FLUIDS.register("flowing_netherite_slag",
                () -> new ForgeFlowingFluid.Flowing(ModFluids.NETHERITE_SLAG_PROPERTIES));

        public static final ForgeFlowingFluid.Properties NETHERITE_SLAG_PROPERTIES =
                new ForgeFlowingFluid.Properties(
                        ModFluidTypes.NETHERITE_SLAG_TYPE,
                        ModFluids.NETHERITE_SLAG_SOURCE,
                        ModFluids.FLOWING_NETHERITE_SLAG)
                        .slopeFindDistance(4)
                        .levelDecreasePerBlock(1)
                        .tickRate(30)
                        .block(() -> ModBlocks.NETHERITE_SLAG_BLOCK.get())
                        .bucket(() -> ModItems.NETHERITE_SLAG_BUCKET.get());

        //bedrock slag
        public static final RegistryObject<FlowingFluid> BEDROCK_SLAG_SOURCE = FLUIDS.register("bedrock_slag_source",
                () -> new ForgeFlowingFluid.Source(ModFluids.BEDROCK_SLAG_PROPERTIES));
        public static final RegistryObject<FlowingFluid> FLOWING_BEDROCK_SLAG = FLUIDS.register("flowing_bedrock_slag",
                () -> new ForgeFlowingFluid.Flowing(ModFluids.BEDROCK_SLAG_PROPERTIES));
        
        public static final ForgeFlowingFluid.Properties BEDROCK_SLAG_PROPERTIES =
                new ForgeFlowingFluid.Properties(
                        ModFluidTypes.BEDROCK_SLAG_TYPE,
                        ModFluids.BEDROCK_SLAG_SOURCE,
                        ModFluids.FLOWING_BEDROCK_SLAG)
                        .slopeFindDistance(4)
                        .levelDecreasePerBlock(1)
                        .tickRate(30)
                        .block(() -> ModBlocks.BEDROCK_SLAG_BLOCK.get())
                        .bucket(() -> ModItems.BEDROCK_SLAG_BUCKET.get());

    public static void register (IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
