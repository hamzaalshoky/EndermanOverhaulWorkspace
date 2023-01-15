package io.github.padlocks.endermanoverhaul.item;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.ModEntityCreator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EndermanOverhaul.MOD_ID);

    public static final RegistryObject<Item> SAVANNA_ENDERMAN_SPAWN_EGG = ITEMS.register("savanna_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.SAVANNA_ENDERMAN,14061139, 13553083,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> BADLANDS_ENDERMAN_SPAWN_EGG = ITEMS.register("badlands_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.BADLANDS_ENDERMAN,9453362, 5929568,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> SNOWY_ENDERMAN_SPAWN_EGG = ITEMS.register("snowy_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.SNOWY_ENDERMAN,2313577, 12308169,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> SCARAB_SPAWN_EGG = ITEMS.register("scarab_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.SCARAB,329484, 1054492,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> DESERT_ENDERMAN_SPAWN_EGG = ITEMS.register("desert_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.DESERT_ENDERMAN,14797710, 8348493,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> DARK_OAK_ENDERMAN_SPAWN_EGG = ITEMS.register("dark_oak_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.DARK_OAK_ENDERMAN,1315352, 4402726,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> ICE_SPIKES_ENDERMAN_SPAWN_EGG = ITEMS.register("ice_spikes_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.ICE_SPIKES_ENDERMAN,12574436, 14741735,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> SWAMP_ENDERMAN_SPAWN_EGG = ITEMS.register("swamp_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.SWAMP_ENDERMAN,2501922, 3947820,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> WINDSWEPT_HILLS_ENDERMAN_SPAWN_EGG = ITEMS.register("windswept_hills_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.WINDSWEPT_HILLS_ENDERMAN,6511201, 9339253,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> WARPED_FOREST_ENDERMAN_SPAWN_EGG = ITEMS.register("warped_forest_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.WARPED_FOREST_ENDERMAN,4532274, 1153925,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> CRIMSON_FOREST_ENDERMAN_SPAWN_EGG = ITEMS.register("crimson_forest_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.CRIMSON_FOREST_ENDERMAN,4991024, 9569846,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> SPIRIT_SPAWN_EGG = ITEMS.register("spirit_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.SPIRIT,14406054, 13958911,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> SOULSAND_VALLEY_ENDERMAN_SPAWN_EGG = ITEMS.register("soulsand_valley_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.SOULSAND_VALLEY_ENDERMAN,5981496, 8188661,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> NETHER_WASTES_ENDERMAN_SPAWN_EGG = ITEMS.register("nether_wastes_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.NETHER_WASTES_ENDERMAN,2830908, 6503478,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> CAVE_ENDERMAN_SPAWN_EGG = ITEMS.register("cave_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.CAVE_ENDERMAN,10000526, 13157814,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> FLOWER_FIELDS_ENDERMAN_SPAWN_EGG = ITEMS.register("flower_fields_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.FLOWER_FIELDS_ENDERMAN,1052943, 16768080,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> MUSHROOM_ENDERMAN_SPAWN_EGG = ITEMS.register("mushroom_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.MUSHROOM_ENDERMAN,15788488, 13719104,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> END_ENDERMAN_SPAWN_EGG = ITEMS.register("end_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.END_ENDERMAN,1052943, 1579555,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> OCEAN_ENDERMAN_SPAWN_EGG = ITEMS.register("ocean_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.OCEAN_ENDERMAN,3096921, 10721399,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));

    public static final RegistryObject<Item> END_ISLANDS_ENDERMAN_SPAWN_EGG = ITEMS.register("end_islands_enderman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityCreator.END_ISLANDS_ENDERMAN,1052943, 12150191,
                    new Item.Properties().tab(ModCreativeModeTabs.ENDERMAN_OVERHAUL_TAB)));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
