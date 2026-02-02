package main.java.com.souls.complexmod.item;

import com.souls.complexmod.ComplexMod;

import main.java.com.souls.complexmod.util.ModTags;
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
}
