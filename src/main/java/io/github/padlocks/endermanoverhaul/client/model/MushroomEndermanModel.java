package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.MushroomEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class MushroomEndermanModel extends BaseEndermanModel<MushroomEndermanEntity> {
    public MushroomEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/mushroom_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/mushroom/mushroom_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
