package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.SoulsandValleyEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class SoulsandValleyEndermanModel extends BaseEndermanModel<SoulsandValleyEndermanEntity> {
    public SoulsandValleyEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/soulsand_valley_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/soulsand_valley/soulsand_valley_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/soulsand_valley_enderman.animation.json"));
    }
}
