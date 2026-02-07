package com.souls.complexmod.item;

import com.souls.complexmod.ComplexMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.network.chat.Component;
import com.souls.complexmod.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ComplexMod.MOD_ID);


    public static final RegistryObject<CreativeModeTab> COMPLEXMOD_TAB = CREATIVE_MODE_TABS.register("complexmod_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> ModItems.TWINE.get().getDefaultInstance())
                    .title(Component.translatable("creativetab.complexmod_tab"))
                    .displayItems((parameters, output) -> {
                        //items
                        output.accept(ModItems.TWINE.get());
                        output.accept(ModItems.DRILL_HEAD.get());
                        output.accept(ModItems.DRILL_BODY.get());
                        output.accept(ModItems.HAND_DRILL.get());
                        output.accept(ModItems.WEDGE.get());
                        output.accept(ModItems.MOLTEN_IRON_BUCKET.get());
                        output.accept(ModItems.MOLTEN_COPPER_BUCKET.get());
                        output.accept(ModItems.MOLTEN_DIAMOND_BUCKET.get());
                        output.accept(ModItems.MOLTEN_GOLD_BUCKET.get());
                        output.accept(ModItems.ENDER_ESSENCE_BUCKET.get());
                        output.accept(ModItems.BLAZE_ESSENCE_BUCKET.get());
                        output.accept(ModItems.DIAMOND_FRAME.get());
                        output.accept(ModItems.MOLTEN_TUNGSTEN_BUCKET.get());
                        output.accept(ModItems.MOLTEN_AMETHYST_BUCKET.get());

                        //tools
                        output.accept(ModItems.FLINT_HATCHET.get());
                        output.accept(ModItems.FLINT_KNIFE.get());
                        output.accept(ModItems.WOODEN_MALLET.get());
                        output.accept(ModItems.STONE_MALLET.get());

                        //buckets
                        output.accept(ModItems.MIXED_SLAG_BUCKET.get());
                        output.accept(ModItems.NETHERITE_SLAG_BUCKET.get());

                        //blocks
                        output.accept(ModBlocks.FURNACE_STACK.get());
                        output.accept(ModBlocks.STENCIL_TABLE.get());
                        output.accept(ModBlocks.IRON_FURNACE.get());
                        output.accept(ModBlocks.IRON_FURNACE_STACK.get());
                        output.accept(ModBlocks.IRON_STENCIL_TABLE.get());
                        output.accept(ModBlocks.DIAMOND_FURNACE.get());
                        output.accept(ModBlocks.DIAMOND_STENCIL_TABLE.get());
                        output.accept(ModBlocks.EMERALD_FURNACE.get());
                        output.accept(ModBlocks.EMERALD_FURNACE_STACK.get());
                        //output.accept(ModBlocks.EMERALD_STENCIL_TABLE.get());
                    })
                    .build());
    
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
