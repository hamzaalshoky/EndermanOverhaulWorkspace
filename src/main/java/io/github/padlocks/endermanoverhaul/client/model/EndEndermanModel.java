package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.EndEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class EndEndermanModel extends BaseEndermanModel<EndEndermanEntity> {
    public EndEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/end_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/end/end_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/soulsand_valley_enderman.animation.json"));
    }
}
