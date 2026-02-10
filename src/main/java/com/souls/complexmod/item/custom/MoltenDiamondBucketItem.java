package com.souls.complexmod.item.custom;

import java.util.List;

import com.souls.complexmod.item.ModItems;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MoltenDiamondBucketItem extends Item {
    public MoltenDiamondBucketItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        if (stack.getItem() == ModItems.MOLTEN_DIAMOND_BUCKET.get()) {
            tooltip.add(Component.literal("Temperature: 3550°C").withStyle(ChatFormatting.GRAY));
        } else if (stack.getItem() == ModItems.MOLTEN_DIAMOND_BUCKET_A.get()) {
            tooltip.add(Component.literal("Temperature: 3900°C").withStyle(ChatFormatting.GOLD));
        } else if (stack.getItem() == ModItems.MOLTEN_DIAMOND_BUCKET_B.get()) {
            tooltip.add(Component.literal("Temperature: 4550°C").withStyle(ChatFormatting.YELLOW));
        } else if (stack.getItem() == ModItems.MOLTEN_DIAMOND_BUCKET_C.get()) {
            tooltip.add(Component.literal("Temperature: 5100°C").withStyle(ChatFormatting.WHITE));
        } else if (stack.getItem() == ModItems.MOLTEN_DIAMOND_BUCKET_D.get()) {
            tooltip.add(Component.literal("Temperature: 6000°C").withStyle(ChatFormatting.RED));
        }
    }
}
