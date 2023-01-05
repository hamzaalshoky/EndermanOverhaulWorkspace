package io.github.padlocks.endermanoverhaul.world;

import io.github.padlocks.endermanoverhaul.config.EOConfig;
import io.github.padlocks.endermanoverhaul.entity.ModEntityCreator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModEntityGeneration {
    public static void onEntitySpawn(final BiomeLoadingEvent event) {

        if(doesBiomeMatch(event.getName(), Biomes.SAVANNA) && EOConfig.shouldSavannaEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.SAVANNA_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SAVANNA_PLATEAU) && EOConfig.shouldSavannaEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.SAVANNA_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.WINDSWEPT_SAVANNA) && EOConfig.shouldSavannaEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.SAVANNA_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.BADLANDS) && EOConfig.shouldBadlandsEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.BADLANDS_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.WOODED_BADLANDS) && EOConfig.shouldBadlandsEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.BADLANDS_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.ERODED_BADLANDS) && EOConfig.shouldBadlandsEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.BADLANDS_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SNOWY_TAIGA) && EOConfig.shouldSnowyEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.SNOWY_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SNOWY_PLAINS) && EOConfig.shouldSnowyEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.SNOWY_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SNOWY_BEACH) && EOConfig.shouldSnowyEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.SNOWY_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SNOWY_SLOPES) && EOConfig.shouldSnowyEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.SNOWY_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DESERT) && EOConfig.shouldDesertEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.DESERT_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DARK_FOREST) && EOConfig.shouldDarkOakEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.DARK_OAK_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.ICE_SPIKES) && EOConfig.shouldIceSpikesEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.ICE_SPIKES_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SWAMP) && EOConfig.shouldSwampEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.SWAMP_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.WARPED_FOREST) && EOConfig.shouldWarpedForestEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.WARPED_FOREST_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.CRIMSON_FOREST) && EOConfig.shouldCrimsonEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.CRIMSON_FOREST_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SOUL_SAND_VALLEY) && EOConfig.shouldSoulsandValleyEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.SOULSAND_VALLEY_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.NETHER_WASTES) && EOConfig.shouldNetherWastesEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.NETHER_WASTES_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DRIPSTONE_CAVES) && EOConfig.shouldCaveEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.CAVE_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.LUSH_CAVES) && EOConfig.shouldCaveEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.CAVE_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.PLAINS) && EOConfig.shouldCaveEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.CAVE_ENDERMAN.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.BADLANDS) && EOConfig.shouldCaveEndermanSpawn) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityCreator.CAVE_ENDERMAN.get(), 3, 1, 3));
        }
    }

    public static boolean doesBiomeMatch(ResourceLocation biomeNameIn, ResourceKey<Biome> biomeIn) {
        return biomeNameIn.getPath().matches(biomeIn.location().getPath());
    }
}
