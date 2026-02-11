package com.souls.complexmod;

import com.mojang.logging.LogUtils;

import com.souls.complexmod.block.ModBlocks;
import com.souls.complexmod.block.enchantment.ModEnchantments;
import com.souls.complexmod.block.entity.ModBlockEntities;
import com.souls.complexmod.fluid.ModFluidTypes;
import com.souls.complexmod.fluid.ModFluids;
import com.souls.complexmod.item.ModCreativeModTabs;
import com.souls.complexmod.item.ModItems;
import com.souls.complexmod.loot.ModLootModifiers;
import com.souls.complexmod.menu.ModMenus;
import com.souls.complexmod.util.ModEvents;
import com.souls.complexmod.util.ModRecipes;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ComplexMod.MOD_ID)
public class ComplexMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "complexmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ComplexMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();   

        //registers
        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenus.register(modEventBus);

        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModEnchantments.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.register(ModEvents.class);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            //menus
            //ItemBlockRenderTypes.setRenderLayer(ModFluids.MIXED_SLAG_SOURCE.get(), RenderType.cutout());
            //ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_MIXED_SLAG.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TIMELESS_SAPLING.get(), RenderType.cutout());
        }
    }
}
