package com.souls.complexmod.item.custom;

import java.util.List;

import org.checkerframework.checker.units.qual.mol;

import com.souls.complexmod.item.ModItems;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MoltenIronBucketItem extends Item {
    public MoltenIronBucketItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        if (stack.getItem() == ModItems.MOLTEN_IRON_BUCKET.get()) {
            tooltip.add(Component.literal("Temperature: 1538°C").withStyle(ChatFormatting.GRAY));
        } else if (stack.getItem() == ModItems.MOLTEN_IRON_BUCKET_A.get()) {
            tooltip.add(Component.literal("Temperature: 2086°C").withStyle(ChatFormatting.GOLD));
        } else if (stack.getItem() == ModItems.MOLTEN_IRON_BUCKET_B.get()) {
            tooltip.add(Component.literal("Temperature: 2712°C").withStyle(ChatFormatting.YELLOW));
        } else if (stack.getItem() == ModItems.MOLTEN_IRON_BUCKET_C.get()) {
            tooltip.add(Component.literal("Temperature: 3380°C").withStyle(ChatFormatting.WHITE));
        } else if (stack.getItem() == ModItems.MOLTEN_IRON_BUCKET_D.get()) {
            tooltip.add(Component.literal("Temperature: 4290°C").withStyle(ChatFormatting.RED));
        }
    }
}
