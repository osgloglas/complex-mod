package com.souls.complexmod.util;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import com.souls.complexmod.ComplexMod;
import com.souls.complexmod.menu.ModMenus;
import com.souls.complexmod.screen.FurnaceStackScreen;
import com.souls.complexmod.screen.IronFurnaceScreen;
import com.souls.complexmod.screen.IronFurnaceStackScreen;
import com.souls.complexmod.screen.IronStencilTableScreen;
import com.souls.complexmod.screen.StencilTableScreen;

@Mod.EventBusSubscriber(modid = ComplexMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHooks {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenus.FURNACE_STACK_MENU.get(), FurnaceStackScreen::new);
            MenuScreens.register(ModMenus.STENCIL_TABLE_MENU.get(), StencilTableScreen::new);
            MenuScreens.register(ModMenus.IRON_FURNACE_MENU.get(), IronFurnaceScreen::new);
            MenuScreens.register(ModMenus.IRON_FURNACE_STACK_MENU.get(), IronFurnaceStackScreen::new);
            MenuScreens.register(ModMenus.IRON_STENCIL_TABLE_MENU.get(), IronStencilTableScreen::new);
            //TODO: add more screens here later
        });
    }
}
