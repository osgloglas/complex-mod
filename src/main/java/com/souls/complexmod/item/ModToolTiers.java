package com.souls.complexmod.item;

import com.souls.complexmod.ComplexMod;

import com.souls.complexmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.common.ForgeTier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;

import java.util.List;

public class ModToolTiers {
    public static final Tier FLINT = TierSortingRegistry.registerTier (
        new ForgeTier(
            0, // Harvest Level
            20, // Durability
            1.5F, // Efficiency
            0.0F, // Attack Damage Bonus
            18, // Enchantability
            ModTags.Blocks.NEEDS_FLINT_TOOL, // Tag
            () -> Ingredient.of(Items.FLINT) // Repair Ingredient
        ),
        ResourceLocation.fromNamespaceAndPath(ComplexMod.MOD_ID, "flint"), List.of(), List.of(Tiers.WOOD)); // Name, Lower, Higher

    public static final Tier AMETHINE = TierSortingRegistry.registerTier (
        new ForgeTier(
            5, // Harvest Level
            2700, // Durability
            10.5F, // Efficiency
            4.0F, // Attack Damage Bonus
            18, // Enchantability
            ModTags.Blocks.NEEDS_AMETHINE_TOOL, // Tag
            () -> Ingredient.of(ModItems.AMETHINE_INGOT.get()) // Repair Ingredient
        ),
        ResourceLocation.fromNamespaceAndPath(ComplexMod.MOD_ID, "amethine"), List.of(Tiers.NETHERITE), List.of()); // Name, Lower, Higher

    public static final Tier PURPOSDALITE = TierSortingRegistry.registerTier (
        new ForgeTier(
            6, // Harvest Level
            3500, // Durability
            12.0F, // Efficiency
            5.0F, // Attack Damage Bonus
            24, // Enchantability
            ModTags.Blocks.NEEDS_PURPOSDALITE_TOOL, // Tag
            () -> Ingredient.of(ModItems.PURPOSDALITE_INGOT.get()) // Repair Ingredient
        ),
        ResourceLocation.fromNamespaceAndPath(ComplexMod.MOD_ID, "purposdalite"), List.of(AMETHINE), List.of()); // Name, Lower, Higher

    /*public static final Tier BEDROCK = TierSortingRegistry.registerTier (
        new ForgeTier(
            7, // Harvest Level
            5000, // Durability
            15.0F, // Efficiency
            6.0F, // Attack Damage Bonus
            30, // Enchantability
            ModTags.Blocks.NEEDS_BEDROCK_TOOL, // Tag
            () -> Ingredient.of(ModItems.BEDROCK_INGOT.get()) // Repair Ingredient
        ),
        ResourceLocation.fromNamespaceAndPath(ComplexMod.MOD_ID, "bedrock"), List.of(PURPOSDALITE), List.of()); // Name, Lower, Higher*/
}
