package main.java.com.souls.complexmod.item;

import java.rmi.registry.Registry;

import com.souls.complexmod.ComplexMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.network.chat.Component;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ComplexMod.MOD_ID);


    public static final RegistryObject<CreativeModeTab> COMPLEXMOD_TAB = CREATIVE_MODE_TABS.register("complexmod_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> ModItems.TWINE.get().getDefaultInstance())
                    .title(Component.translatable("creativetab.complexmod_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.TWINE.get());
                        output.accept(ModItems.OAK_WOOD.get());
                    })
                    .build());
    
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
