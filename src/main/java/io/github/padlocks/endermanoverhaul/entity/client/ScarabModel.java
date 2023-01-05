package io.github.padlocks.endermanoverhaul.entity.client;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.custom.ScarabEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ScarabModel extends AnimatedGeoModel<ScarabEntity> {
    @Override
    public ResourceLocation getModelLocation(ScarabEntity object) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/scarab.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ScarabEntity object) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/scarab/scarab.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ScarabEntity animatable) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/scarab.animation.json");
    }
}
