package net.Nindq.blocks;

import net.Nindq.GingqMod;
import net.Nindq.items.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GingqMod.MOD_ID);

    public static final RegistryObject<Block> GEMS_BLOCKS = registerBlock("gems_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RAW_GEMS_BLOCK = registerBlock("raw_gems_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK).sound(SoundType.AMETHYST_CLUSTER)));

    public static final RegistryObject<Block> GEMS_ORE = registerBlock("gems_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f)
                    .requiresCorrectToolForDrops(), UniformInt.of(3,6)));

    public static final RegistryObject<Block> DEEPSLATE_GEMS_ORE = registerBlock("deepslate_gems_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.5f)
                    .requiresCorrectToolForDrops(), UniformInt.of(4,7)));


    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toreturn = BLOCKS.register(name, block);
        registerBlockItem(name, toreturn);
        return toreturn;
    }
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventbus){
        BLOCKS.register(eventbus);
    }
}
