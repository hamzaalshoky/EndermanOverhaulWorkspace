package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.DarkOakEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class DarkOakEndermanModel extends BaseEndermanModel<DarkOakEndermanEntity> {
    public DarkOakEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/dark_oak_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/dark_oak/dark_oak_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
