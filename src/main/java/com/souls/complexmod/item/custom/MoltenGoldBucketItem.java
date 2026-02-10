package com.souls.complexmod.item.custom;

import java.util.List;

import com.souls.complexmod.item.ModItems;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MoltenGoldBucketItem extends Item {
    public MoltenGoldBucketItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        if (stack.getItem() == ModItems.MOLTEN_GOLD_BUCKET.get()) {
            tooltip.add(Component.literal("Temperature: 1064°C").withStyle(ChatFormatting.GRAY));
        } else if (stack.getItem() == ModItems.MOLTEN_GOLD_BUCKET_A.get()) {
            tooltip.add(Component.literal("Temperature: 1456°C").withStyle(ChatFormatting.GOLD));
        } else if (stack.getItem() == ModItems.MOLTEN_GOLD_BUCKET_B.get()) {
            tooltip.add(Component.literal("Temperature: 2131°C").withStyle(ChatFormatting.YELLOW));
        } else if (stack.getItem() == ModItems.MOLTEN_GOLD_BUCKET_C.get()) {
            tooltip.add(Component.literal("Temperature: 2692°C").withStyle(ChatFormatting.WHITE));
        } else if (stack.getItem() == ModItems.MOLTEN_GOLD_BUCKET_D.get()) {
            tooltip.add(Component.literal("Temperature: 3580°C").withStyle(ChatFormatting.RED));
        }
    }
}
