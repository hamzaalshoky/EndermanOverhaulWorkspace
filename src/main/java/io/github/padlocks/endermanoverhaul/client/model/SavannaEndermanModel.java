package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.SavannaEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class SavannaEndermanModel extends BaseEndermanModel<SavannaEndermanEntity> {
    public SavannaEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/savanna_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/savanna/savanna_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
