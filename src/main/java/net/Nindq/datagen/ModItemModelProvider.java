package net.Nindq.datagen;

import net.Nindq.GingqMod;
import net.Nindq.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, GingqMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.FIREWOOD);
        simpleItem(ModItems.ORE_DETECTOR);

        simpleItem(ModItems.GEMS);
        simpleItem(ModItems.RAW_GEMS);
        simpleItem(ModItems.STRAWBERRY);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(GingqMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
