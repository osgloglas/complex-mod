package com.souls.complexmod.fluid;

import org.joml.Vector3f;

import com.souls.complexmod.ComplexMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
    public static final ResourceLocation MIXED_SLAG_STILL_RL =
            new ResourceLocation(ComplexMod.MOD_ID, "block/mixed_slag");
    public static final ResourceLocation MIXED_SLAG_FLOWING_RL =
            new ResourceLocation(ComplexMod.MOD_ID, "block/flowing_mixed_slag");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ComplexMod.MOD_ID);

    public static final RegistryObject<FluidType> MIXED_SLAG_TYPE =
            register("mixed_slag_type", 
                    FluidType.Properties.create()
                            .density(3000)
                            .viscosity(3000)
                            .temperature(1300)
                            .lightLevel(8)
                            .canDrown(false)
                            .supportsBoating(false)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA) 
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA));

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(MIXED_SLAG_STILL_RL, MIXED_SLAG_FLOWING_RL, null, 0x685C34, new Vector3f(0.2f, 0.2f, 0.2f), properties));
    }

    public static void register (IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
