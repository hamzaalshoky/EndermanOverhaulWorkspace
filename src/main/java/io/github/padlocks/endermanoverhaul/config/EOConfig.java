package io.github.padlocks.endermanoverhaul.config;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import net.minecraftforge.fml.config.ModConfig;

public class EOConfig {
    public static boolean shouldSavannaEndermanSpawn = true;
    public static boolean shouldBadlandsEndermanSpawn = true;
    public static boolean shouldCaveEndermanSpawn = true;
    public static boolean shouldCrimsonEndermanSpawn = true;
    public static boolean shouldDarkOakEndermanSpawn = true;
    public static boolean shouldDesertEndermanSpawn = true;
    public static boolean shouldIceSpikesEndermanSpawn = true;
    public static boolean shouldNetherWastesEndermanSpawn = true;
    public static boolean shouldSnowyEndermanSpawn = true;
    public static boolean shouldSwampEndermanSpawn = true;
    public static boolean shouldWarpedForestEndermanSpawn = true;
    public static boolean shouldWindsweptHillsEndermanSpawn = true;
    public static boolean shouldSoulsandValleyEndermanSpawn = true;

    public static void bake(ModConfig config){
        try{
            shouldSavannaEndermanSpawn = ConfigHolder.COMMON.shouldSavannaEndermanSpawn.get();
            shouldBadlandsEndermanSpawn = ConfigHolder.COMMON.shouldBadlandsEndermanSpawn.get();
            shouldCaveEndermanSpawn = ConfigHolder.COMMON.shouldCaveEndermanSpawn.get();
            shouldCrimsonEndermanSpawn = ConfigHolder.COMMON.shouldCrimsonEndermanSpawn.get();
            shouldDarkOakEndermanSpawn = ConfigHolder.COMMON.shouldDarkOakEndermanSpawn.get();
            shouldDesertEndermanSpawn = ConfigHolder.COMMON.shouldDesertEndermanSpawn.get();
            shouldIceSpikesEndermanSpawn = ConfigHolder.COMMON.shouldIceSpikesEndermanSpawn.get();
            shouldNetherWastesEndermanSpawn = ConfigHolder.COMMON.shouldNetherWastesEndermanSpawn.get();
            shouldSnowyEndermanSpawn = ConfigHolder.COMMON.shouldSnowyEndermanSpawn.get();
            shouldSwampEndermanSpawn = ConfigHolder.COMMON.shouldSwampEndermanSpawn.get();
            shouldWarpedForestEndermanSpawn = ConfigHolder.COMMON.shouldWarpedForestEndermanSpawn.get();
            shouldWindsweptHillsEndermanSpawn = ConfigHolder.COMMON.shouldWindsweptHillsEndermanSpawn.get();
            shouldSoulsandValleyEndermanSpawn = ConfigHolder.COMMON.shouldSoulsandValleyEndermanSpawn.get();
        } catch (Exception e) {
            EndermanOverhaul.LOGGER.warn("An exception was caused trying to load the config for Enderman Overhaul.");
            e.printStackTrace();
        }
    }
}
