package net.Nindq.datagen;

import net.Nindq.GingqMod;
import net.Nindq.blocks.ModBlocks;
import net.Nindq.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> GEMS_SMETABLE = List.of(ModItems.RAW_GEMS.get(),
            ModBlocks.GEMS_BLOCKS.get(), ModBlocks.DEEPSLATE_GEMS_ORE.get());
    private static final List<ItemLike> GEMS_BLOCK_SMETABLE = List.of(ModBlocks.RAW_GEMS_BLOCK.get());
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreBlasting(consumer, GEMS_SMETABLE, RecipeCategory.MISC, ModItems.GEMS.get(), 0.5f, 100, "gems");
        oreSmelting(consumer, GEMS_SMETABLE, RecipeCategory.MISC, ModItems.GEMS.get(), 0.5f, 500, "gems");
        oreBlasting(consumer, GEMS_BLOCK_SMETABLE, RecipeCategory.MISC, ModBlocks.GEMS_BLOCKS.get(), 0.75f, 500, "gems");
        oreSmelting(consumer, GEMS_BLOCK_SMETABLE, RecipeCategory.MISC, ModBlocks.GEMS_BLOCKS.get(), 0.75f, 2000, "gems");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ORE_DETECTOR.get())
                .pattern(" ##")
                .pattern("X# ")
                .pattern("XX ")
                .define('#', Items.STICK)
                .define('X', ModItems.GEMS.get())
                .unlockedBy(getHasName(ModItems.GEMS.get()), has(ModItems.GEMS.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GEMS_BLOCKS.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.GEMS.get())
                .unlockedBy(getHasName(ModItems.GEMS.get()), has(ModItems.GEMS.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_GEMS_BLOCK.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.RAW_GEMS.get())
                .unlockedBy(getHasName(ModItems.GEMS.get()), has(ModItems.GEMS.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GEMS.get(), 9)
                .requires(ModBlocks.GEMS_BLOCKS.get())
                .unlockedBy(getHasName(ModBlocks.GEMS_BLOCKS.get()), has(ModBlocks.GEMS_BLOCKS.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_GEMS.get(), 9)
                .requires(ModBlocks.RAW_GEMS_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_GEMS_BLOCK.get()), has(ModBlocks.RAW_GEMS_BLOCK.get()))
                .save(consumer);
    }
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  GingqMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
