package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.DesertEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class DesertEndermanModel extends BaseEndermanModel<DesertEndermanEntity> {
    public DesertEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/desert_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/desert/desert_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
