package com.souls.complexmod.block.enchantment;

import com.souls.complexmod.ComplexMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(Registries.ENCHANTMENT, ComplexMod.MOD_ID);

    public static final RegistryObject<Enchantment> VITALITY =
            ENCHANTMENTS.register("vitality", VitalityEnchantment::new);

    public static void register (IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
