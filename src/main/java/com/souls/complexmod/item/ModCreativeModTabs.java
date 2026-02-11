package com.souls.complexmod.item;

import com.souls.complexmod.ComplexMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.network.chat.Component;
import com.souls.complexmod.block.ModBlocks;
import com.souls.complexmod.block.enchantment.ModEnchantments;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ComplexMod.MOD_ID);


    public static final RegistryObject<CreativeModeTab> COMPLEXMOD_TAB = CREATIVE_MODE_TABS.register("complexmod_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> ModItems.AMETHINE_INGOT.get().getDefaultInstance())
                    .title(Component.translatable("creativetab.complexmod_tab"))
                    .displayItems((parameters, output) -> {
                        //items
                        output.accept(ModItems.TWINE.get());
                        output.accept(ModItems.DRILL_HEAD.get());
                        output.accept(ModItems.DRILL_BODY.get());
                        output.accept(ModItems.HAND_DRILL.get());
                        output.accept(ModItems.WEDGE.get());
                        output.accept(ModItems.MOLTEN_IRON_BUCKET.get());
                        output.accept(ModItems.MOLTEN_IRON_BUCKET_A.get());
                        output.accept(ModItems.MOLTEN_IRON_BUCKET_B.get());
                        output.accept(ModItems.MOLTEN_IRON_BUCKET_C.get());
                        output.accept(ModItems.MOLTEN_IRON_BUCKET_D.get());
                        output.accept(ModItems.MOLTEN_COPPER_BUCKET.get());
                        output.accept(ModItems.MOLTEN_COPPER_BUCKET_A.get());
                        output.accept(ModItems.MOLTEN_COPPER_BUCKET_B.get());
                        output.accept(ModItems.MOLTEN_COPPER_BUCKET_C.get());
                        output.accept(ModItems.MOLTEN_COPPER_BUCKET_D.get());
                        output.accept(ModItems.MOLTEN_DIAMOND_BUCKET.get());
                        output.accept(ModItems.MOLTEN_DIAMOND_BUCKET_A.get());
                        output.accept(ModItems.MOLTEN_DIAMOND_BUCKET_B.get());
                        output.accept(ModItems.MOLTEN_DIAMOND_BUCKET_C.get());
                        output.accept(ModItems.MOLTEN_DIAMOND_BUCKET_D.get());
                        output.accept(ModItems.MOLTEN_GOLD_BUCKET.get());
                        output.accept(ModItems.MOLTEN_GOLD_BUCKET_A.get());
                        output.accept(ModItems.MOLTEN_GOLD_BUCKET_B.get());
                        output.accept(ModItems.MOLTEN_GOLD_BUCKET_C.get());
                        output.accept(ModItems.MOLTEN_GOLD_BUCKET_D.get());
                        output.accept(ModItems.ENDER_ESSENCE_BUCKET.get());
                        output.accept(ModItems.BLAZE_ESSENCE_BUCKET.get());
                        output.accept(ModItems.DIAMOND_FRAME.get());
                        output.accept(ModItems.MOLTEN_TUNGSTEN_BUCKET.get());
                        output.accept(ModItems.MOLTEN_TUNGSTEN_BUCKET_A.get());
                        output.accept(ModItems.MOLTEN_TUNGSTEN_BUCKET_B.get());
                        output.accept(ModItems.MOLTEN_TUNGSTEN_BUCKET_C.get());
                        output.accept(ModItems.MOLTEN_TUNGSTEN_BUCKET_D.get());
                        output.accept(ModItems.MOLTEN_AMETHYST_BUCKET.get());
                        output.accept(ModItems.MOLTEN_AMETHYST_BUCKET_A.get());
                        output.accept(ModItems.MOLTEN_AMETHYST_BUCKET_B.get());
                        output.accept(ModItems.MOLTEN_AMETHYST_BUCKET_C.get());
                        output.accept(ModItems.MOLTEN_AMETHYST_BUCKET_D.get());
                        output.accept(ModItems.AMETHINE_INGOT.get());
                        output.accept(ModItems.AMETHINE_TEMPLATE.get());
                        output.accept(ModItems.SUPERHEATED_AMETHINE_BUCKET.get());
                        output.accept(ModItems.SUPERHEATED_DIAMOND_BUCKET.get());
                        output.accept(ModItems.DIAMOND_AMETHINE_CRYSTAL.get());
                        output.accept(ModItems.NETHERITE_FRAME.get());
                        output.accept(ModItems.SUPERHEATED_DIAMOND_AMETHINE_BUCKET.get());
                        output.accept(ModItems.PURPOSDALITE_INGOT.get());
                        output.accept(ModItems.PURPOSDALITE_TEMPLATE.get());
                        output.accept(ModItems.NEUTRON_CLUSTER.get());
                        output.accept(ModItems.BEDROCK_INGOT.get());
                        output.accept(ModItems.BEDROCK_TEMPLATE.get());
                        output.accept(ModItems.SUPERHEATED_PURPOSDALITE_INGOT.get());
                        output.accept(ModItems.SUPERHEATED_BLAZE_ESSENCE_BUCKET.get());
                        output.accept(ModItems.COOLED_PURPOSDALITE_INGOT.get());
                        output.accept(ModItems.CRUSHED_PURPOSDALITE.get());
                        output.accept(ModItems.SUPERHEATED_PURPOSDALITE_BUCKET.get());
                        output.accept(ModItems.SUPERHEATED_ENDER_ESSENCE_BUCKET.get());
                        output.accept(ModItems.MINI_SUN.get());

                        //tools
                        output.accept(ModItems.FLINT_HATCHET.get());
                        output.accept(ModItems.FLINT_KNIFE.get());
                        output.accept(ModItems.WOODEN_MALLET.get());
                        output.accept(ModItems.STONE_MALLET.get());
                        output.accept(ModItems.AMETHINE_SWORD.get());
                        output.accept(ModItems.AMETHINE_PICKAXE.get());
                        output.accept(ModItems.AMETHINE_AXE.get());
                        output.accept(ModItems.AMETHINE_SHOVEL.get());
                        output.accept(ModItems.AMETHINE_HOE.get());
                        output.accept(ModItems.PURPOSDALITE_SWORD.get());
                        output.accept(ModItems.PURPOSDALITE_PICKAXE.get());
                        output.accept(ModItems.PURPOSDALITE_AXE.get());
                        output.accept(ModItems.PURPOSDALITE_SHOVEL.get());
                        output.accept(ModItems.PURPOSDALITE_HOE.get());
                        output.accept(ModItems.BEDROCK_SWORD.get());
                        output.accept(ModItems.BEDROCK_PICKAXE.get());
                        output.accept(ModItems.BEDROCK_AXE.get());
                        output.accept(ModItems.BEDROCK_SHOVEL.get());
                        output.accept(ModItems.BEDROCK_HOE.get());

                        //armor
                        output.accept(ModItems.AMETHINE_HELMET.get());
                        output.accept(ModItems.AMETHINE_CHESTPLATE.get());
                        output.accept(ModItems.AMETHINE_LEGGINGS.get());
                        output.accept(ModItems.AMETHINE_BOOTS.get());
                        output.accept(ModItems.PURPOSDALITE_HELMET.get());
                        output.accept(ModItems.PURPOSDALITE_CHESTPLATE.get());
                        output.accept(ModItems.PURPOSDALITE_LEGGINGS.get());
                        output.accept(ModItems.PURPOSDALITE_BOOTS.get());
                        output.accept(ModItems.BEDROCK_HELMET.get());
                        output.accept(ModItems.BEDROCK_CHESTPLATE.get());
                        output.accept(ModItems.BEDROCK_LEGGINGS.get());
                        output.accept(ModItems.BEDROCK_BOOTS.get());

                        //buckets
                        output.accept(ModItems.MIXED_SLAG_BUCKET.get());
                        output.accept(ModItems.NETHERITE_SLAG_BUCKET.get());
                        output.accept(ModItems.BEDROCK_SLAG_BUCKET.get());

                        //blocks
                        output.accept(ModBlocks.AMETHINE_BLOCK.get());
                        output.accept(ModBlocks.PURPOSDALITE_BLOCK.get());
                        output.accept(ModBlocks.TIMELESS_LEAVES.get());
                        output.accept(ModBlocks.TIMELESS_SAPLING.get());
                        output.accept(ModBlocks.TIMELESS_LOG.get());
                        output.accept(ModBlocks.TIMELESS_STRIPPED_LOG.get());
                        output.accept(ModBlocks.TIMELESS_WOOD.get());
                        output.accept(ModBlocks.BEDROCK_BLOCK.get());

                        //blocks (tree)
                        output.accept(ModBlocks.TIMELESS_STAIRS.get());
                        output.accept(ModBlocks.TIMELESS_SLAB.get());
                        output.accept(ModBlocks.TIMELESS_BUTTON.get());
                        output.accept(ModBlocks.TIMELESS_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.TIMELESS_FENCE.get());
                        output.accept(ModBlocks.TIMELESS_FENCE_GATE.get());
                        output.accept(ModBlocks.TIMELESS_WALL.get());
                        output.accept(ModBlocks.TIMELESS_DOOR.get());
                        output.accept(ModBlocks.TIMELESS_TRAPDOOR.get());

                        //block entities
                        output.accept(ModBlocks.FURNACE_STACK.get());
                        output.accept(ModBlocks.STENCIL_TABLE.get());
                        output.accept(ModBlocks.IRON_FURNACE.get());
                        output.accept(ModBlocks.IRON_FURNACE_STACK.get());
                        output.accept(ModBlocks.IRON_STENCIL_TABLE.get());
                        output.accept(ModBlocks.DIAMOND_FURNACE.get());
                        output.accept(ModBlocks.DIAMOND_STENCIL_TABLE.get());
                        output.accept(ModBlocks.EMERALD_FURNACE.get());
                        output.accept(ModBlocks.EMERALD_FURNACE_STACK.get());
                        output.accept(ModBlocks.EMERALD_STENCIL_TABLE.get());
                        output.accept(ModBlocks.NETHERITE_FURNACE.get());
                        output.accept(ModBlocks.NETHERITE_FURNACE_STACK.get());
                        output.accept(ModBlocks.NETHERITE_STENCIL_TABLE.get());
                        output.accept(ModBlocks.PARTICLE_COLLECTOR.get());
                        output.accept(ModBlocks.AMETHINE_CRYSTAL_FURNACE.get());
                        output.accept(ModBlocks.AMETHINE_CRYSTAL_FURNACE_STACK.get());
                        output.accept(ModBlocks.AMETHINE_CRYSTAL_STENCIL_TABLE.get());
                        output.accept(ModBlocks.STAR_FACTORY.get());
                    })
                    .build());

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) { // Books are usually here
            for (int level = 1; level <= 5; level++) {
                ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
                EnchantedBookItem.addEnchantment(book, new EnchantmentInstance(ModEnchantments.VITALITY.get(), level));
                event.accept(book);
            }
        }
    }

    
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
