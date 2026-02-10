package com.souls.complexmod.block;

import com.souls.complexmod.ComplexMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.PressurePlateBlock.Sensitivity;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.FlowingFluid;

import com.souls.complexmod.block.custom.DiamondFurnaceBlock;
import com.souls.complexmod.block.custom.DiamondStencilTableBlock;
import com.souls.complexmod.block.custom.EmeraldFurnaceBlock;
import com.souls.complexmod.block.custom.EmeraldFurnaceStackBlock;
import com.souls.complexmod.block.custom.EmeraldStencilTableBlock;
import com.souls.complexmod.block.custom.FurnaceStackBlock;
import com.souls.complexmod.block.custom.IronFurnaceBlock;
import com.souls.complexmod.block.custom.IronFurnaceStackBlock;
import com.souls.complexmod.block.custom.IronStencilTableBlock;
import com.souls.complexmod.block.custom.NetheriteFurnaceBlock;
import com.souls.complexmod.block.custom.NetheriteFurnaceStackBlock;
import com.souls.complexmod.block.custom.NetheriteStencilTableBlock;
import com.souls.complexmod.block.custom.ParticleCollectorBlock;
import com.souls.complexmod.block.custom.StencilTableBlock;
import com.souls.complexmod.block.custom.TimelessLeavesBlock;
import com.souls.complexmod.fluid.ModFluids;
import com.souls.complexmod.item.ModItems;
import com.souls.complexmod.worldgen.tree.TimelessTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ComplexMod.MOD_ID);

    //normal blocks
    public static final RegistryObject<Block> AMETHINE_BLOCK = registerBlock("amethine_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PURPOSDALITE_BLOCK = registerBlock("purposdalite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TIMELESS_LEAVES = registerBlock("timeless_leaves",
            () -> new TimelessLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TIMELESS_LOG = registerBlock("timeless_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TIMELESS_SAPLING = registerBlock("timeless_sapling",
            () -> new SaplingBlock(new TimelessTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> TIMELESS_PLANKS = registerBlock("timeless_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TIMELESS_STRIPPED_LOG = registerBlock("timeless_stripped_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TIMELESS_WOOD = registerBlock("timeless_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).requiresCorrectToolForDrops()));
    
    //blocks (tree)
    public static final RegistryObject<Block> TIMELESS_STAIRS = registerBlock("timeless_stairs",
            () -> new StairBlock(() -> ModBlocks.TIMELESS_PLANKS.get().defaultBlockState(),
                        BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TIMELESS_SLAB = registerBlock("timeless_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TIMELESS_BUTTON = registerBlock("timeless_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).requiresCorrectToolForDrops(),
                        BlockSetType.OAK, 10, true));
    public static final RegistryObject<Block> TIMELESS_PRESSURE_PLATE = registerBlock("timeless_pressure_plate",
            () -> new PressurePlateBlock(Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).requiresCorrectToolForDrops(),
                        BlockSetType.OAK));
    public static final RegistryObject<Block> TIMELESS_FENCE = registerBlock("timeless_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TIMELESS_FENCE_GATE = registerBlock("timeless_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).requiresCorrectToolForDrops(), SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE));
    public static final RegistryObject<Block> TIMELESS_WALL = registerBlock("timeless_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TIMELESS_DOOR = registerBlock("timeless_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).requiresCorrectToolForDrops(), BlockSetType.OAK));
    public static final RegistryObject<Block> TIMELESS_TRAPDOOR = registerBlock("timeless_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).requiresCorrectToolForDrops(), BlockSetType.OAK));

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
    public static final RegistryObject<EmeraldStencilTableBlock> EMERALD_STENCIL_TABLE = registerBlock("emerald_stencil_table",
            () -> new EmeraldStencilTableBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<NetheriteFurnaceBlock> NETHERITE_FURNACE = registerBlock("netherite_furnace",
            () -> new NetheriteFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<NetheriteFurnaceStackBlock> NETHERITE_FURNACE_STACK = registerBlock("netherite_furnace_stack",
            () -> new NetheriteFurnaceStackBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<NetheriteStencilTableBlock> NETHERITE_STENCIL_TABLE = registerBlock("netherite_stencil_table",
            () -> new NetheriteStencilTableBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<ParticleCollectorBlock> PARTICLE_COLLECTOR = registerBlock("particle_collector",
            () -> new ParticleCollectorBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops()));

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
