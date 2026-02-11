package com.souls.complexmod.loot;

import com.mojang.serialization.Codec;
import com.souls.complexmod.ComplexMod;

import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ComplexMod.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", WoodlandMansionModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> END_CITY =
            LOOT_MODIFIER_SERIALIZERS.register("end_city", EndCityModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ANCIENT_CITY =
            LOOT_MODIFIER_SERIALIZERS.register("ancient_city", AncientCityModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
