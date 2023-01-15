package io.github.padlocks.endermanoverhaul.client.model;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.SpiritEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpiritModel extends AnimatedGeoModel<SpiritEntity> {
    public static final ResourceLocation MODEL = new ResourceLocation("endermanoverhaul:geo/projectile_1.geo.json");
    public static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("endermanoverhaul:textures/entity/entities/projectile_1_glow.png");

    @Override
    public ResourceLocation getModelLocation(SpiritEntity object) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/projectile_1.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpiritEntity object) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/entities/projectile_1.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpiritEntity animatable) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/projectile_1.animation.json");
    }
}
