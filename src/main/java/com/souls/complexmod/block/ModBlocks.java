package com.souls.complexmod.block;

import com.souls.complexmod.ComplexMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;

import com.souls.complexmod.block.custom.DiamondFurnaceBlock;
import com.souls.complexmod.block.custom.DiamondStencilTableBlock;
import com.souls.complexmod.block.custom.EmeraldFurnaceBlock;
import com.souls.complexmod.block.custom.EmeraldFurnaceStackBlock;
import com.souls.complexmod.block.custom.FurnaceStackBlock;
import com.souls.complexmod.block.custom.IronFurnaceBlock;
import com.souls.complexmod.block.custom.IronFurnaceStackBlock;
import com.souls.complexmod.block.custom.IronStencilTableBlock;
import com.souls.complexmod.block.custom.StencilTableBlock;
import com.souls.complexmod.fluid.ModFluids;
import com.souls.complexmod.item.ModItems;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ComplexMod.MOD_ID);

    //normal blocks

    //block entities
    public static final RegistryObject<FurnaceStackBlock> FURNACE_STACK = registerBlock("furnace_stack",
            () -> new FurnaceStackBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<StencilTableBlock> STENCIL_TABLE = registerBlock("stencil_table",
            () -> new StencilTableBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<IronFurnaceBlock> IRON_FURNACE = registerBlock("iron_furnace",
            () -> new IronFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<IronFurnaceStackBlock> IRON_FURNACE_STACK = registerBlock("iron_furnace_stack",
            () -> new IronFurnaceStackBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<IronStencilTableBlock> IRON_STENCIL_TABLE = registerBlock("iron_stencil_table",
            () -> new IronStencilTableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<DiamondFurnaceBlock> DIAMOND_FURNACE = registerBlock("diamond_furnace",
            () -> new DiamondFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<DiamondStencilTableBlock> DIAMOND_STENCIL_TABLE = registerBlock("diamond_stencil_table",
            () -> new DiamondStencilTableBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<EmeraldFurnaceBlock> EMERALD_FURNACE = registerBlock("emerald_furnace",
            () -> new EmeraldFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<EmeraldFurnaceStackBlock> EMERALD_FURNACE_STACK = registerBlock("emerald_furnace_stack",
            () -> new EmeraldFurnaceStackBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).requiresCorrectToolForDrops()));

    //fluids
    public static final RegistryObject<LiquidBlock> MIXED_SLAG_BLOCK = BLOCKS.register("mixed_slag_block",
            () -> new LiquidBlock(ModFluids.MIXED_SLAG_SOURCE, BlockBehaviour.Properties.copy(Blocks.LAVA))); //must be SOURCE
    public static final RegistryObject<LiquidBlock> NETHERITE_SLAG_BLOCK = BLOCKS.register("netherite_slag_block",
            () -> new LiquidBlock(ModFluids.NETHERITE_SLAG_SOURCE, BlockBehaviour.Properties.copy(Blocks.LAVA))); //must be SOURCE

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
