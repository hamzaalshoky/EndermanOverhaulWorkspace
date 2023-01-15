package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.WindsweptHillsEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class WindsweptHillsEndermanModel extends BaseEndermanModel<WindsweptHillsEndermanEntity> {
    public WindsweptHillsEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/windswept_hills_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/windswept_hills/windswept_hills_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
