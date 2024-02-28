package net.Nindq.items;

import net.Nindq.GingqMod;
import net.Nindq.items.custom.FuelItem;
import net.Nindq.items.custom.OreDetecterItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GingqMod.MOD_ID);

    public static final RegistryObject<Item> GEMS = ITEMS.register("gems",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_GEMS = ITEMS.register("raw_gems",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEM_STAFF = ITEMS.register("gem_staff",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModsFoods.STRAWBERRY)));

    public static final RegistryObject<Item> FIREWOOD = ITEMS.register("firewood",
            () -> new FuelItem(new Item.Properties(), 6000));
    public static final RegistryObject<Item> ORE_DETECTOR = ITEMS.register("ore_detector",
            () -> new OreDetecterItem(new Item.Properties().durability(20)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
