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
    
    public static void register (IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
