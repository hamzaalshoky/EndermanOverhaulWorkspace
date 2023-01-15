package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.SwampEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class SwampEndermanModel extends BaseEndermanModel<SwampEndermanEntity> {
    public SwampEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/swamp_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/swamp/swamp_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
