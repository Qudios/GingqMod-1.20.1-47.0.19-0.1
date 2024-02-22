package net.Nindq.datagen;

import net.Nindq.GingqMod;
import net.Nindq.blocks.ModBlocks;
import net.Nindq.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, GingqMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.ORE_DETECTOR_BLOCKS).add(ModBlocks.DEEPSLATE_GEMS_ORE.get())
                .add(ModBlocks.GEMS_ORE.get()).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GEMS_ORE.get(),
                        ModBlocks.DEEPSLATE_GEMS_ORE.get(),
                        ModBlocks.GEMS_BLOCKS.get(),
                        ModBlocks.SOUND_BLOCK.get(),
                        ModBlocks.RAW_GEMS_BLOCK.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);
        this.tag(BlockTags.NEEDS_IRON_TOOL);
        this.tag(BlockTags.NEEDS_STONE_TOOL);
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);
        this.tag(Tags.Blocks.NEEDS_GOLD_TOOL);
        this.tag(Tags.Blocks.NEEDS_WOOD_TOOL);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.DEEPSLATE_GEMS_ORE.get())
                .add(ModBlocks.GEMS_ORE.get()).add(ModBlocks.GEMS_BLOCKS.get()).add(ModBlocks.RAW_GEMS_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE);
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL);
        this.tag(BlockTags.MINEABLE_WITH_HOE);
    }
}
