package com.souls.complexmod.menu;

import com.souls.complexmod.ComplexMod;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ComplexMod.MOD_ID);

    public static final RegistryObject<MenuType<FurnaceStackMenu>> FURNACE_STACK_MENU =
            MENUS.register("furnace_stack_menu", () -> IForgeMenuType.create(FurnaceStackMenu::new));

    public static void register (IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
