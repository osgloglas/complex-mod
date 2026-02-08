package com.souls.complexmod.item;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.fluid.ModFluids;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.HoeItem;

import com.souls.complexmod.item.custom.FlintKnifeItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import com.souls.complexmod.item.custom.StoneMalletItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ComplexMod.MOD_ID);
    
    //items
    public static final RegistryObject<Item> TWINE = ITEMS.register("twine",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DRILL_HEAD = ITEMS.register("drill_head",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DRILL_BODY = ITEMS.register("drill_body",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HAND_DRILL = ITEMS.register("hand_drill",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WEDGE = ITEMS.register("wedge",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_IRON_BUCKET = ITEMS.register("molten_iron_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_COPPER_BUCKET = ITEMS.register("molten_copper_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_DIAMOND_BUCKET = ITEMS.register("molten_diamond_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_GOLD_BUCKET = ITEMS.register("molten_gold_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDER_ESSENCE_BUCKET = ITEMS.register("ender_essence_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLAZE_ESSENCE_BUCKET = ITEMS.register("blaze_essence_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_FRAME = ITEMS.register("diamond_frame",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_TUNGSTEN_BUCKET = ITEMS.register("molten_tungsten_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_AMETHYST_BUCKET = ITEMS.register("molten_amethyst_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_INGOT = ITEMS.register("amethine_ingot",
            () -> new Item(new Item.Properties()));

    //tools
    public static final RegistryObject<Item> FLINT_HATCHET = ITEMS.register("flint_hatchet",
            () -> new AxeItem(ModToolTiers.FLINT, 0, 1, new Item.Properties()));
    public static final RegistryObject<Item> FLINT_KNIFE = ITEMS.register("flint_knife",
            () -> new FlintKnifeItem(ModToolTiers.FLINT, 0, 1, new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_MALLET = ITEMS.register("wooden_mallet",
            () -> new PickaxeItem(Tiers.WOOD, 1, 2, new Item.Properties()));
    public static final RegistryObject<Item> STONE_MALLET = ITEMS.register("stone_mallet",
            () -> new StoneMalletItem(Tiers.STONE, 2, 3, new Item.Properties()));

    //amethine tier
    public static final RegistryObject<Item> AMETHINE_SWORD = ITEMS.register("amethine_sword",
            () -> new SwordItem(ModToolTiers.AMETHINE, 4, 2.4F, new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_PICKAXE = ITEMS.register("amethine_pickaxe",
            () -> new PickaxeItem(ModToolTiers.AMETHINE, 2, 1, new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_AXE = ITEMS.register("amethine_axe",
            () -> new AxeItem(ModToolTiers.AMETHINE, 6, 3, new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_SHOVEL = ITEMS.register("amethine_shovel",
            () -> new ShovelItem(ModToolTiers.AMETHINE, 1.5F, 1, new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_HOE = ITEMS.register("amethine_hoe",
            () -> new HoeItem(ModToolTiers.AMETHINE, 1, 1, new Item.Properties()));
        
    //bucket items
    public static final RegistryObject<Item> MIXED_SLAG_BUCKET = ITEMS.register("mixed_slag_bucket",
            () -> new BucketItem(ModFluids.MIXED_SLAG_SOURCE,
                new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> NETHERITE_SLAG_BUCKET = ITEMS.register("netherite_slag_bucket",
            () -> new BucketItem(ModFluids.NETHERITE_SLAG_SOURCE,
                new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
