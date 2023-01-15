package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.FlowerFieldsEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class FlowerFieldsEndermanModel extends BaseEndermanModel<FlowerFieldsEndermanEntity> {
    public FlowerFieldsEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/flower_fields_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/flower_fields/flower_fields_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
