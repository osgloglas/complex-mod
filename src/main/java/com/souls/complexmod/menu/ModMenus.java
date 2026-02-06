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
    public static final RegistryObject<MenuType<StencilTableMenu>> STENCIL_TABLE_MENU =
            MENUS.register("stencil_table_menu", () -> IForgeMenuType.create(StencilTableMenu::new));
        public static final RegistryObject<MenuType<IronFurnaceMenu>> IRON_FURNACE_MENU =
            MENUS.register("iron_furnace_menu", () -> IForgeMenuType.create(IronFurnaceMenu::new));
        public static final RegistryObject<MenuType<IronFurnaceStackMenu>> IRON_FURNACE_STACK_MENU =
            MENUS.register("iron_furnace_stack_menu", () -> IForgeMenuType.create(IronFurnaceStackMenu::new));
        public static final RegistryObject<MenuType<IronStencilTableMenu>> IRON_STENCIL_TABLE_MENU =
            MENUS.register("iron_stencil_table_menu", () -> IForgeMenuType.create(IronStencilTableMenu::new));

    public static void register (IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
