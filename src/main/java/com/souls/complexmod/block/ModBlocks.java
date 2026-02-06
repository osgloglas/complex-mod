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

import com.souls.complexmod.block.custom.FurnaceStackBlock;
import com.souls.complexmod.block.custom.IronFurnaceBlock;
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

    //fluids
    public static final RegistryObject<LiquidBlock> MIXED_SLAG_BLOCK = BLOCKS.register("mixed_slag_block",
            () -> new LiquidBlock(ModFluids.MIXED_SLAG_SOURCE, BlockBehaviour.Properties.copy(Blocks.LAVA))); //must be SOURCE

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
