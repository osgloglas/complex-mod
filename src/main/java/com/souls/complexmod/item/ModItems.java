package com.souls.complexmod.item;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.fluid.ModFluids;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.HoeItem;

import com.souls.complexmod.item.custom.FlintKnifeItem;
import com.souls.complexmod.item.custom.MoltenAmethystBucketItem;
import com.souls.complexmod.item.custom.MoltenCopperBucketItem;
import com.souls.complexmod.item.custom.MoltenDiamondBucketItem;
import com.souls.complexmod.item.custom.MoltenGoldBucketItem;
import com.souls.complexmod.item.custom.MoltenIronBucketItem;
import com.souls.complexmod.item.custom.MoltenTungstenBucketItem;

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
    public static final RegistryObject<Item> MOLTEN_IRON_BUCKET_A = ITEMS.register("molten_iron_bucket_2086",
            () -> new MoltenIronBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_IRON_BUCKET_B = ITEMS.register("molten_iron_bucket_2712",
            () -> new MoltenIronBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_IRON_BUCKET_C = ITEMS.register("molten_iron_bucket_3380",
            () -> new MoltenIronBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_IRON_BUCKET_D = ITEMS.register("molten_iron_bucket_4290",
            () -> new MoltenIronBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_COPPER_BUCKET = ITEMS.register("molten_copper_bucket",
            () -> new MoltenCopperBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_COPPER_BUCKET_A = ITEMS.register("molten_copper_bucket_1477",
            () -> new MoltenCopperBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_COPPER_BUCKET_B = ITEMS.register("molten_copper_bucket_2150",
            () -> new MoltenCopperBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_COPPER_BUCKET_C = ITEMS.register("molten_copper_bucket_2709",
            () -> new MoltenCopperBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_COPPER_BUCKET_D = ITEMS.register("molten_copper_bucket_3600",
            () -> new MoltenCopperBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_DIAMOND_BUCKET = ITEMS.register("molten_diamond_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_DIAMOND_BUCKET_A = ITEMS.register("molten_diamond_bucket_3900",
            () -> new MoltenDiamondBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_DIAMOND_BUCKET_B = ITEMS.register("molten_diamond_bucket_4550",
            () -> new MoltenDiamondBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_DIAMOND_BUCKET_C = ITEMS.register("molten_diamond_bucket_5100",
            () -> new MoltenDiamondBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_DIAMOND_BUCKET_D = ITEMS.register("molten_diamond_bucket_6000",
            () -> new MoltenDiamondBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_GOLD_BUCKET = ITEMS.register("molten_gold_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_GOLD_BUCKET_A = ITEMS.register("molten_gold_bucket_1456",
            () -> new MoltenGoldBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_GOLD_BUCKET_B = ITEMS.register("molten_gold_bucket_2131",
            () -> new MoltenGoldBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_GOLD_BUCKET_C = ITEMS.register("molten_gold_bucket_2692",
            () -> new MoltenGoldBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_GOLD_BUCKET_D = ITEMS.register("molten_gold_bucket_3580",
            () -> new MoltenGoldBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> ENDER_ESSENCE_BUCKET = ITEMS.register("ender_essence_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLAZE_ESSENCE_BUCKET = ITEMS.register("blaze_essence_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_FRAME = ITEMS.register("diamond_frame",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_TUNGSTEN_BUCKET = ITEMS.register("molten_tungsten_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_TUNGSTEN_BUCKET_A = ITEMS.register("molten_tungsten_bucket_3877",
            () -> new MoltenTungstenBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_TUNGSTEN_BUCKET_B = ITEMS.register("molten_tungsten_bucket_4710",
            () -> new MoltenTungstenBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_TUNGSTEN_BUCKET_C = ITEMS.register("molten_tungsten_bucket_5365",
            () -> new MoltenTungstenBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_TUNGSTEN_BUCKET_D = ITEMS.register("molten_tungsten_bucket_6260",
            () -> new MoltenTungstenBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_AMETHYST_BUCKET = ITEMS.register("molten_amethyst_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_AMETHYST_BUCKET_A = ITEMS.register("molten_amethyst_bucket_2100",
            () -> new MoltenAmethystBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_AMETHYST_BUCKET_B = ITEMS.register("molten_amethyst_bucket_2750",
            () -> new MoltenAmethystBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_AMETHYST_BUCKET_C = ITEMS.register("molten_amethyst_bucket_3299",
            () -> new MoltenAmethystBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_AMETHYST_BUCKET_D = ITEMS.register("molten_amethyst_bucket_4167",
            () -> new MoltenAmethystBucketItem(new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_INGOT = ITEMS.register("amethine_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_TEMPLATE = ITEMS.register("amethine_template",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SUPERHEATED_AMETHINE_BUCKET = ITEMS.register("superheated_amethine_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SUPERHEATED_DIAMOND_BUCKET = ITEMS.register("superheated_diamond_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_AMETHINE_CRYSTAL = ITEMS.register("diamond_amethine_crystal",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_FRAME = ITEMS.register("netherite_frame",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SUPERHEATED_DIAMOND_AMETHINE_BUCKET = ITEMS.register("superheated_diamond_amethine_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PURPOSDALITE_INGOT = ITEMS.register("purposdalite_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PURPOSDALITE_TEMPLATE = ITEMS.register("purposdalite_template",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NEUTRON_CLUSTER = ITEMS.register("neutron_cluster",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_INGOT = ITEMS.register("bedrock_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_TEMPLATE = ITEMS.register("bedrock_template",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SUPERHEATED_PURPOSDALITE_BUCKET = ITEMS.register("superheated_purposdalite_bucket",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SUPERHEATED_BLAZE_ESSENCE_BUCKET = ITEMS.register("superheated_blaze_essence_bucket",
            () -> new Item(new Item.Properties()));

    //tools
    public static final RegistryObject<Item> FLINT_HATCHET = ITEMS.register("flint_hatchet",
            () -> new AxeItem(ModToolTiers.FLINT, 0, 1, new Item.Properties()));
    public static final RegistryObject<Item> FLINT_KNIFE = ITEMS.register("flint_knife",
            () -> new FlintKnifeItem(ModToolTiers.FLINT, 0, 1, new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_MALLET = ITEMS.register("wooden_mallet",
            () -> new PickaxeItem(Tiers.WOOD, 1, 0, new Item.Properties()));
    public static final RegistryObject<Item> STONE_MALLET = ITEMS.register("stone_mallet",
            () -> new StoneMalletItem(Tiers.STONE, 2, 0, new Item.Properties()));

    //amethine tier
    public static final RegistryObject<Item> AMETHINE_SWORD = ITEMS.register("amethine_sword",
            () -> new SwordItem(ModToolTiers.AMETHINE, 4, 0, new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_PICKAXE = ITEMS.register("amethine_pickaxe",
            () -> new PickaxeItem(ModToolTiers.AMETHINE, 2, 0, new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_AXE = ITEMS.register("amethine_axe",
            () -> new AxeItem(ModToolTiers.AMETHINE, 5, 0, new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_SHOVEL = ITEMS.register("amethine_shovel",
            () -> new ShovelItem(ModToolTiers.AMETHINE, 2.5F, 0, new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_HOE = ITEMS.register("amethine_hoe",
            () -> new HoeItem(ModToolTiers.AMETHINE, 0, 1, new Item.Properties()));

    public static final RegistryObject<Item> AMETHINE_HELMET = ITEMS.register("amethine_helmet",
            () -> new ArmorItem(ModArmorMaterials.AMETHINE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_CHESTPLATE = ITEMS.register("amethine_chestplate",
            () -> new ArmorItem(ModArmorMaterials.AMETHINE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_LEGGINGS = ITEMS.register("amethine_leggings",
            () -> new ArmorItem(ModArmorMaterials.AMETHINE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> AMETHINE_BOOTS = ITEMS.register("amethine_boots",
            () -> new ArmorItem(ModArmorMaterials.AMETHINE, ArmorItem.Type.BOOTS, new Item.Properties()));

    //purposdalite tier
    public static final RegistryObject<Item> PURPOSDALITE_SWORD = ITEMS.register("purposdalite_sword",
            () -> new SwordItem(ModToolTiers.PURPOSDALITE, 4, 0, new Item.Properties()));
    public static final RegistryObject<Item> PURPOSDALITE_PICKAXE = ITEMS.register("purposdalite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.PURPOSDALITE, 2, 0, new Item.Properties()));
    public static final RegistryObject<Item> PURPOSDALITE_AXE = ITEMS.register("purposdalite_axe",
            () -> new AxeItem(ModToolTiers.PURPOSDALITE, 5, 0, new Item.Properties()));
    public static final RegistryObject<Item> PURPOSDALITE_SHOVEL = ITEMS.register("purposdalite_shovel",
            () -> new ShovelItem(ModToolTiers.PURPOSDALITE, 2.5F, 0, new Item.Properties()));
    public static final RegistryObject<Item> PURPOSDALITE_HOE = ITEMS.register("purposdalite_hoe",
            () -> new HoeItem(ModToolTiers.PURPOSDALITE, 0, 1, new Item.Properties()));

    public static final RegistryObject<Item> PURPOSDALITE_HELMET = ITEMS.register("purposdalite_helmet",
            () -> new ArmorItem(ModArmorMaterials.PURPOSDALITE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> PURPOSDALITE_CHESTPLATE = ITEMS.register("purposdalite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.PURPOSDALITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> PURPOSDALITE_LEGGINGS = ITEMS.register("purposdalite_leggings",
            () -> new ArmorItem(ModArmorMaterials.PURPOSDALITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> PURPOSDALITE_BOOTS = ITEMS.register("purposdalite_boots",
            () -> new ArmorItem(ModArmorMaterials.PURPOSDALITE, ArmorItem.Type.BOOTS, new Item.Properties()));

    //bedrock tier
    public static final RegistryObject<Item> BEDROCK_SWORD = ITEMS.register("bedrock_sword",
            () -> new SwordItem(ModToolTiers.BEDROCK, 4, 0, new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_PICKAXE = ITEMS.register("bedrock_pickaxe",
            () -> new PickaxeItem(ModToolTiers.BEDROCK, 2, 0, new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_AXE = ITEMS.register("bedrock_axe",
            () -> new AxeItem(ModToolTiers.BEDROCK, 5, 0, new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_SHOVEL = ITEMS.register("bedrock_shovel",
            () -> new ShovelItem(ModToolTiers.BEDROCK, 2.5F, 0, new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_HOE = ITEMS.register("bedrock_hoe",
            () -> new HoeItem(ModToolTiers.BEDROCK, 0, 1, new Item.Properties()));

    public static final RegistryObject<Item> BEDROCK_HELMET = ITEMS.register("bedrock_helmet",
            () -> new ArmorItem(ModArmorMaterials.BEDROCK, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_CHESTPLATE = ITEMS.register("bedrock_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BEDROCK, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_LEGGINGS = ITEMS.register("bedrock_leggings",
            () -> new ArmorItem(ModArmorMaterials.BEDROCK, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_BOOTS = ITEMS.register("bedrock_boots",
            () -> new ArmorItem(ModArmorMaterials.BEDROCK, ArmorItem.Type.BOOTS, new Item.Properties()));

    //bucket items
    public static final RegistryObject<Item> MIXED_SLAG_BUCKET = ITEMS.register("mixed_slag_bucket",
            () -> new BucketItem(ModFluids.MIXED_SLAG_SOURCE,
                new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> NETHERITE_SLAG_BUCKET = ITEMS.register("netherite_slag_bucket",
            () -> new BucketItem(ModFluids.NETHERITE_SLAG_SOURCE,
                new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> BEDROCK_SLAG_BUCKET = ITEMS.register("bedrock_slag_bucket",
            () -> new BucketItem(ModFluids.BEDROCK_SLAG_SOURCE,
                new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
