package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.OceanEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class OceanEndermanModel extends BaseEndermanModel<OceanEndermanEntity> {
    public OceanEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/ocean_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/ocean/ocean_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/ocean_enderman.animation.json"));
    }
}
