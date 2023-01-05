package io.github.padlocks.endermanoverhaul.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class EndermanOverhaulCommonConfigs {

    public final ForgeConfigSpec.BooleanValue shouldSavannaEndermanSpawn;
    public final ForgeConfigSpec.BooleanValue shouldBadlandsEndermanSpawn;
    public final ForgeConfigSpec.BooleanValue shouldCaveEndermanSpawn;
    public final ForgeConfigSpec.BooleanValue shouldCrimsonEndermanSpawn;
    public final ForgeConfigSpec.BooleanValue shouldDarkOakEndermanSpawn;
    public final ForgeConfigSpec.BooleanValue shouldDesertEndermanSpawn;
    public final ForgeConfigSpec.BooleanValue shouldIceSpikesEndermanSpawn;
    public final ForgeConfigSpec.BooleanValue shouldNetherWastesEndermanSpawn;
    public final ForgeConfigSpec.BooleanValue shouldSnowyEndermanSpawn;
    public final ForgeConfigSpec.BooleanValue shouldSwampEndermanSpawn;
    public final ForgeConfigSpec.BooleanValue shouldWarpedForestEndermanSpawn;
    public final ForgeConfigSpec.BooleanValue shouldWindsweptHillsEndermanSpawn;
    public final ForgeConfigSpec.BooleanValue shouldSoulsandValleyEndermanSpawn;

    public EndermanOverhaulCommonConfigs(final ForgeConfigSpec.Builder builder){
        shouldSavannaEndermanSpawn = buildBoolean(builder, "shouldSavannaEndermanSpawn", "all", true, "Whether Savanna Endermen Spawn or Not.");
        shouldBadlandsEndermanSpawn = buildBoolean(builder, "shouldBadlandsEndermanSpawn", "all", true, "Whether Badlands Endermen Spawn or Not.");
        shouldCaveEndermanSpawn = buildBoolean(builder, "shouldCaveEndermanSpawn", "all", true, "Whether Cave Endermen Spawn or Not.");
        shouldCrimsonEndermanSpawn = buildBoolean(builder, "shouldCrimsonEndermanSpawn", "all", true, "Whether Crimson Forest Endermen Spawn or Not.");
        shouldDarkOakEndermanSpawn = buildBoolean(builder, "shouldDarkOakEndermanSpawn", "all", true, "Whether Dark Oak Forest Endermen Spawn or Not.");
        shouldDesertEndermanSpawn = buildBoolean(builder, "shouldDesertEndermanSpawn", "all", true, "Whether Desert Endermen Spawn or Not.");
        shouldIceSpikesEndermanSpawn = buildBoolean(builder, "shouldIceSpikesEndermanSpawn", "all", true, "Whether Ice Spikes Endermen Spawn or Not.");
        shouldNetherWastesEndermanSpawn = buildBoolean(builder, "shouldNetherWastesEndermanSpawn", "all", true, "Whether Nether Wastes Endermen Spawn or Not.");
        shouldSnowyEndermanSpawn = buildBoolean(builder, "shouldSnowyEndermanSpawn", "all", true, "Whether Snowy Endermen Spawn or Not.");
        shouldSwampEndermanSpawn = buildBoolean(builder, "shouldSwampEndermanSpawn", "all", true, "Whether Swamp Endermen Spawn or Not.");
        shouldWarpedForestEndermanSpawn = buildBoolean(builder, "shouldWarpedForestEndermanSpawn", "all", true, "Whether Warped Forest Endermen Spawn or Not.");
        shouldWindsweptHillsEndermanSpawn = buildBoolean(builder, "shouldWindsweptHillsEndermanSpawn", "all", true, "Whether Windswept Hills Endermen Spawn or Not.");
        shouldSoulsandValleyEndermanSpawn = buildBoolean(builder, "shouldSoulsandValleyEndermanSpawn", "all", true, "Whether Soul Sand Valley Endermen Spawn or Not.");
    }

    private static ForgeConfigSpec.BooleanValue buildBoolean(ForgeConfigSpec.Builder builder, String name, String catagory, boolean defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }
}
