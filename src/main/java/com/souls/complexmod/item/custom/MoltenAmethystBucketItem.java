package com.souls.complexmod.item.custom;

import java.util.List;

import com.souls.complexmod.item.ModItems;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MoltenAmethystBucketItem extends Item {
    public MoltenAmethystBucketItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        if (stack.getItem() == ModItems.MOLTEN_AMETHYST_BUCKET.get()) {
            tooltip.add(Component.literal("Temperature: 1650°C").withStyle(ChatFormatting.GRAY));
        } else if (stack.getItem() == ModItems.MOLTEN_AMETHYST_BUCKET_A.get()) {
            tooltip.add(Component.literal("Temperature: 2100°C").withStyle(ChatFormatting.GOLD));
        } else if (stack.getItem() == ModItems.MOLTEN_AMETHYST_BUCKET_B.get()) {
            tooltip.add(Component.literal("Temperature: 2750°C").withStyle(ChatFormatting.YELLOW));
        } else if (stack.getItem() == ModItems.MOLTEN_AMETHYST_BUCKET_C.get()) {
            tooltip.add(Component.literal("Temperature: 3299°C").withStyle(ChatFormatting.WHITE));
        } else if (stack.getItem() == ModItems.MOLTEN_AMETHYST_BUCKET_D.get()) {
            tooltip.add(Component.literal("Temperature: 4167°C").withStyle(ChatFormatting.RED));
        }
    }
}
