package com.souls.complexmod.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BucketItem;

import com.souls.complexmod.fluid.ModFluids;
import com.souls.complexmod.item.ModItems;
import com.souls.complexmod.item.custom.MoltenCopperBucketItem;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;

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
    }
}
