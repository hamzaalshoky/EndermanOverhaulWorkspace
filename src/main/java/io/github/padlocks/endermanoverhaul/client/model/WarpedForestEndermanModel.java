package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.WarpedForestEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class WarpedForestEndermanModel extends BaseEndermanModel<WarpedForestEndermanEntity> {
    public WarpedForestEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/warped_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/warped/warped_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/warped_enderman.animation.json"));
    }
}
