package com.souls.complexmod.block.entity;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.block.ModBlocks;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = 
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ComplexMod.MOD_ID);


    public static final RegistryObject<BlockEntityType<FurnaceStackBlockEntity>> FURNACE_STACK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("furnace_stack_block_entity",
                    () -> BlockEntityType.Builder.of(FurnaceStackBlockEntity::new,
                            ModBlocks.FURNACE_STACK.get()).build(null));
        public static final RegistryObject<BlockEntityType<StencilTableBlockEntity>> STENCIL_TABLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("stencil_table_block_entity",
                    () -> BlockEntityType.Builder.of(StencilTableBlockEntity::new,
                            ModBlocks.STENCIL_TABLE.get()).build(null));
        public static final RegistryObject<BlockEntityType<IronFurnaceBlockEntity>> IRON_FURNACE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("iron_furnace_block_entity",
                    () -> BlockEntityType.Builder.of(IronFurnaceBlockEntity::new,
                            ModBlocks.IRON_FURNACE.get()).build(null));
        public static final RegistryObject<BlockEntityType<IronFurnaceStackBlockEntity>> IRON_FURNACE_STACK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("iron_furnace_stack_block_entity",
                    () -> BlockEntityType.Builder.of(IronFurnaceStackBlockEntity::new,
                            ModBlocks.IRON_FURNACE_STACK.get()).build(null));
        public static final RegistryObject<BlockEntityType<IronStencilTableBlockEntity>> IRON_STENCIL_TABLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("iron_stencil_table_block_entity",
                    () -> BlockEntityType.Builder.of(IronStencilTableBlockEntity::new,
                            ModBlocks.IRON_STENCIL_TABLE.get()).build(null));
        public static final RegistryObject<BlockEntityType<DiamondFurnaceBlockEntity>> DIAMOND_FURNACE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("diamond_furnace_block_entity",
                    () -> BlockEntityType.Builder.of(DiamondFurnaceBlockEntity::new,
                            ModBlocks.DIAMOND_FURNACE.get()).build(null));
        public static final RegistryObject<BlockEntityType<DiamondStencilTableBlockEntity>> DIAMOND_STENCIL_TABLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("diamond_stencil_table_block_entity",
                    () -> BlockEntityType.Builder.of(DiamondStencilTableBlockEntity::new,
                            ModBlocks.DIAMOND_STENCIL_TABLE.get()).build(null));
        public static final RegistryObject<BlockEntityType<EmeraldFurnaceBlockEntity>> EMERALD_FURNACE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("emerald_furnace_block_entity",
                    () -> BlockEntityType.Builder.of(EmeraldFurnaceBlockEntity::new,
                            ModBlocks.EMERALD_FURNACE.get()).build(null));
        public static final RegistryObject<BlockEntityType<EmeraldFurnaceStackBlockEntity>> EMERALD_FURNACE_STACK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("emerald_furnace_stack_block_entity",
                    () -> BlockEntityType.Builder.of(EmeraldFurnaceStackBlockEntity::new,
                            ModBlocks.EMERALD_FURNACE_STACK.get()).build(null));
        public static final RegistryObject<BlockEntityType<EmeraldStencilTableBlockEntity>> EMERALD_STENCIL_TABLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("emerald_stencil_table_block_entity",
                    () -> BlockEntityType.Builder.of(EmeraldStencilTableBlockEntity::new,
                            ModBlocks.EMERALD_STENCIL_TABLE.get()).build(null));
        public static final RegistryObject<BlockEntityType<NetheriteFurnaceBlockEntity>> NETHERITE_FURNACE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("netherite_furnace_block_entity",
                    () -> BlockEntityType.Builder.of(NetheriteFurnaceBlockEntity::new,
                            ModBlocks.NETHERITE_FURNACE.get()).build(null));
        public static final RegistryObject<BlockEntityType<NetheriteFurnaceStackBlockEntity>> NETHERITE_FURNACE_STACK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("netherite_furnace_stack_block_entity",
                    () -> BlockEntityType.Builder.of(NetheriteFurnaceStackBlockEntity::new,
                            ModBlocks.NETHERITE_FURNACE_STACK.get()).build(null));
    
    public static void register (IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
