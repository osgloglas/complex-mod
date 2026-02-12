package com.souls.complexmod.util;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.AxeItem;

import java.util.UUID;

import com.souls.complexmod.block.ModBlocks;
import com.souls.complexmod.block.enchantment.ModEnchantments;
import com.souls.complexmod.item.ModItems;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class ModEvents {
    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        BlockState state = event.getState();

        //check if logs are being punched
        if (state.is(BlockTags.LOGS)) {
            //player must be holding a tool to punch the log blocks
            ItemStack tool = player.getMainHandItem();
            //if tool empty, punching is not allowed
            if (!(tool.getItem() instanceof AxeItem)) {
                //set break speed to 0 if no tool is held
                event.setNewSpeed(0.0F);
            }
        }

        //check if stone is being worked on
        if  (state.is(Blocks.STONE)) {
            boolean hasDrill = player.getInventory().contains(new ItemStack(ModItems.HAND_DRILL.get()));
            boolean hasWedge = player.getInventory().contains(new ItemStack(ModItems.WEDGE.get()));

            boolean holdingMallet = player.getMainHandItem().is(ModItems.WOODEN_MALLET.get())
                    || player.getOffhandItem().is(ModItems.WOODEN_MALLET.get())
                    || player.getMainHandItem().is(ModItems.STONE_MALLET.get())
                    || player.getOffhandItem().is(ModItems.STONE_MALLET.get())
                    || player.getMainHandItem().is(Items.IRON_PICKAXE)
                    || player.getOffhandItem().is(Items.IRON_PICKAXE)
                    || player.getMainHandItem().is(Items.DIAMOND_PICKAXE)
                    || player.getOffhandItem().is(Items.DIAMOND_PICKAXE)
                    || player.getMainHandItem().is(Items.NETHERITE_PICKAXE)
                    || player.getOffhandItem().is(Items.NETHERITE_PICKAXE)
                    || player.getMainHandItem().is(ModItems.AMETHINE_PICKAXE.get())
                    || player.getOffhandItem().is(ModItems.AMETHINE_PICKAXE.get())
                    || player.getMainHandItem().is(ModItems.PURPOSDALITE_PICKAXE.get())
                    || player.getOffhandItem().is(ModItems.PURPOSDALITE_PICKAXE.get())
                    || player.getMainHandItem().is(ModItems.BEDROCK_PICKAXE.get())
                    || player.getOffhandItem().is(ModItems.BEDROCK_PICKAXE.get());

            if (!hasDrill || !hasWedge || !holdingMallet) {
                event.setNewSpeed(0.0F);
            }
        }

        //check if low ores are being worked on
        if (state.is(Blocks.COPPER_ORE) || state.is(Blocks.DEEPSLATE_COPPER_ORE) || state.is(Blocks.IRON_ORE) || state.is(Blocks.DEEPSLATE_IRON_ORE)) {
            boolean hasDrill = player.getInventory().contains(new ItemStack(ModItems.HAND_DRILL.get()));
            boolean hasWedge = player.getInventory().contains(new ItemStack(ModItems.WEDGE.get()));

            boolean holdingMallet = player.getMainHandItem().is(ModItems.STONE_MALLET.get())
                    || player.getOffhandItem().is(ModItems.STONE_MALLET.get())
                    || player.getMainHandItem().is(Items.IRON_PICKAXE)
                    || player.getOffhandItem().is(Items.IRON_PICKAXE)
                    || player.getMainHandItem().is(Items.DIAMOND_PICKAXE)
                    || player.getOffhandItem().is(Items.DIAMOND_PICKAXE)
                    || player.getMainHandItem().is(Items.NETHERITE_PICKAXE)
                    || player.getOffhandItem().is(Items.NETHERITE_PICKAXE)
                    || player.getMainHandItem().is(ModItems.AMETHINE_PICKAXE.get())
                    || player.getOffhandItem().is(ModItems.AMETHINE_PICKAXE.get())
                    || player.getMainHandItem().is(ModItems.PURPOSDALITE_PICKAXE.get())
                    || player.getOffhandItem().is(ModItems.PURPOSDALITE_PICKAXE.get())
                    || player.getMainHandItem().is(ModItems.BEDROCK_PICKAXE.get())
                    || player.getOffhandItem().is(ModItems.BEDROCK_PICKAXE.get());

            if (!hasDrill || !hasWedge || !holdingMallet) {
                event.setNewSpeed(0.0F);
            }
        }

        //check if high ores are being worked on
        if (state.is(Blocks.GOLD_ORE) || state.is(Blocks.DEEPSLATE_GOLD_ORE) || state.is(Blocks.REDSTONE_ORE) || state.is(Blocks.DEEPSLATE_REDSTONE_ORE)
                || state.is(Blocks.LAPIS_ORE) || state.is(Blocks.DEEPSLATE_LAPIS_ORE) || state.is(Blocks.DIAMOND_ORE) || state.is(Blocks.DEEPSLATE_DIAMOND_ORE)
                || state.is(Blocks.EMERALD_ORE) || state.is(Blocks.DEEPSLATE_EMERALD_ORE)) {
            boolean hasDrill = player.getInventory().contains(new ItemStack(ModItems.HAND_DRILL.get()));
            boolean hasWedge = player.getInventory().contains(new ItemStack(ModItems.WEDGE.get()));

            boolean holdingMallet = player.getMainHandItem().is(Items.IRON_PICKAXE)
                || player.getOffhandItem().is(Items.IRON_PICKAXE)
                || player.getMainHandItem().is(Items.DIAMOND_PICKAXE)
                || player.getOffhandItem().is(Items.DIAMOND_PICKAXE)
                || player.getMainHandItem().is(Items.NETHERITE_PICKAXE)
                || player.getOffhandItem().is(Items.NETHERITE_PICKAXE)
                || player.getMainHandItem().is(ModItems.AMETHINE_PICKAXE.get())
                || player.getOffhandItem().is(ModItems.AMETHINE_PICKAXE.get())
                || player.getMainHandItem().is(ModItems.PURPOSDALITE_PICKAXE.get())
                || player.getOffhandItem().is(ModItems.PURPOSDALITE_PICKAXE.get())
                || player.getMainHandItem().is(ModItems.BEDROCK_PICKAXE.get())
                || player.getOffhandItem().is(ModItems.BEDROCK_PICKAXE.get());

            if (!hasDrill || !hasWedge || !holdingMallet) {
                event.setNewSpeed(0.0F);
            }
        }

        if (state.is(Blocks.ANCIENT_DEBRIS)) {
            boolean hasDrill = player.getInventory().contains(new ItemStack(ModItems.HAND_DRILL.get()));
            boolean hasWedge = player.getInventory().contains(new ItemStack(ModItems.WEDGE.get()));

            boolean holdingMallet = player.getMainHandItem().is(Items.DIAMOND_PICKAXE)
                || player.getOffhandItem().is(Items.DIAMOND_PICKAXE)
                || player.getMainHandItem().is(Items.NETHERITE_PICKAXE)
                || player.getOffhandItem().is(Items.NETHERITE_PICKAXE)
                || player.getMainHandItem().is(ModItems.AMETHINE_PICKAXE.get())
                || player.getOffhandItem().is(ModItems.AMETHINE_PICKAXE.get())
                || player.getMainHandItem().is(ModItems.PURPOSDALITE_PICKAXE.get())
                || player.getOffhandItem().is(ModItems.PURPOSDALITE_PICKAXE.get())
                || player.getMainHandItem().is(ModItems.BEDROCK_PICKAXE.get())
                || player.getOffhandItem().is(ModItems.BEDROCK_PICKAXE.get());

            if (!hasDrill || !hasWedge || !holdingMallet) {
                event.setNewSpeed(0.0F);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = "complexmod", bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {
        private static final UUID VITALITY_UUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        @SubscribeEvent
        public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
            if (event.getEntity() instanceof Player player) {
                int totalLevel = 0;
                for (ItemStack stack : player.getArmorSlots()) {
                    totalLevel += EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.VITALITY.get(), stack);
                }
            
                AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
                if (maxHealth != null) {
                    // 1. Remove the old modifier using the UUID
                    maxHealth.removeModifier(VITALITY_UUID);
                
                    // 2. Re-apply only if level is > 0
                    if (totalLevel > 0) {
                        maxHealth.addTransientModifier(new AttributeModifier(
                            VITALITY_UUID, 
                            "Vitality Health Boost", 
                            (double) totalLevel * 4.0, 
                            AttributeModifier.Operation.ADDITION));
                    }
                
                    // 3. THE FIX: Force the player's health to sync with the new max
                    // This prevents "ghost hearts" from staying on the screen
                    if (player.getHealth() > player.getMaxHealth()) {
                        player.setHealth(player.getMaxHealth());
                    }
                }
            }
        }
    }
}
