package io.github.padlocks.endermanoverhaul.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class ConfigHolder {

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final EndermanOverhaulCommonConfigs COMMON;

    static {
        {
            final Pair<EndermanOverhaulCommonConfigs, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(EndermanOverhaulCommonConfigs::new);
            COMMON = specPair.getLeft();
            COMMON_SPEC = specPair.getRight();
        }
    }
}
