package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.SnowyEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class SnowyEndermanModel extends BaseEndermanModel<SnowyEndermanEntity> {
    public SnowyEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/snowy_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/snowy/snowy_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
