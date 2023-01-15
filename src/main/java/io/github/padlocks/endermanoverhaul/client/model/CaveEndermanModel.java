package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.CaveEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class CaveEndermanModel extends BaseEndermanModel<CaveEndermanEntity> {
    public CaveEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/cave_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/cave/cave_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
