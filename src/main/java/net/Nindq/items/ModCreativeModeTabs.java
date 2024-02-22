package net.Nindq.items;

import net.Nindq.GingqMod;
import net.Nindq.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GingqMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MATERIALS = CREATIVE_MODE_TABS.register("gingq_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GEMS.get()))
                    .title(Component.translatable("creativetab.gingq_tab"))
                    .displayItems((itemDisplayParameters, output) -> {


                        output.accept(ModItems.GEMS.get());
                        output.accept(ModItems.RAW_GEMS.get());

                        output.accept(ModItems.ORE_DETECTOR.get());

                        output.accept(ModItems.STRAWBERRY.get());

                        output.accept(ModItems.FIREWOOD.get());

                        output.accept(ModBlocks.GEMS_BLOCKS.get());
                        output.accept(ModBlocks.RAW_GEMS_BLOCK.get());

                        output.accept(ModBlocks.GEMS_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_GEMS_ORE.get());
                        output.accept(ModBlocks.SOUND_BLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }


}
