package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.IceSpikesEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class IceSpikesEndermanModel extends BaseEndermanModel<IceSpikesEndermanEntity> {
    public IceSpikesEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/ice_spikes_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/ice_spikes/ice_spikes_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
