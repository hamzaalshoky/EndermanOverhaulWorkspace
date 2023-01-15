package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.BadlandsEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class BadlandsEndermanModel extends BaseEndermanModel<BadlandsEndermanEntity> {
    public BadlandsEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/badlands_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/badlands/badlands_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
