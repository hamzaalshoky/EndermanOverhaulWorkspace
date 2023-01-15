package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.CrimsonForestEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class CrimsonForestEndermanModel extends BaseEndermanModel<CrimsonForestEndermanEntity> {
    public CrimsonForestEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/crimson_forest_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/crimson_forest/crimson_forest_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
