package com.souls.complexmod.block.enchantment;

import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class VitalityEnchantment extends Enchantment {
    public VitalityEnchantment(Rarity rarity, EquipmentSlot... slots) {
        super(rarity, EnchantmentCategory.ARMOR, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 10 + (level * 7);
    }

    @Override
    public int getMaxCost(int level) {
        return 30;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers =
                HashMultimap.create(super.getAttributeModifiers(slot, stack));
        
        if (slot == this.type.getSlot()) {
            int vitalityLevel = EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.VITALITY.get(), stack);

            if (vitalityLevel > 0) {
                double healthBonus = vitalityLevel * 2.0D; // Each level of Vitality adds 2 health points (1 heart)

                modifiers.put(Attributes.MAX_HEALTH,
                        new AttributeModifier(
                                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee" + slot.getIndex()),
                                "Vitality Bonus",
                                healthBonus,
                                AttributeModifier.Operation.ADDITION));
            }
        }
        return modifiers;
    }
}
