package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.NetherWastesEndermanEntity;
import net.minecraft.resources.ResourceLocation;

public class NetherWastesEndermanModel extends BaseEndermanModel<NetherWastesEndermanEntity> {
    public NetherWastesEndermanModel() {
        super(new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/nether_wastes_enderman.geo.json"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/nether_wastes/nether_wastes_enderman.png"),
                new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json"));
    }
}
