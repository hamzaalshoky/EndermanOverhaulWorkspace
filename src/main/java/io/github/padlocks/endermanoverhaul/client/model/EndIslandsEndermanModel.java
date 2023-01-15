package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.EndIslandsEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class EndIslandsEndermanModel extends BaseEndermanModel<EndIslandsEndermanEntity> {
    public EndIslandsEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/end_islands_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/end_islands/end_islands_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/end_islands_enderman.animation.json"));
    }
}
