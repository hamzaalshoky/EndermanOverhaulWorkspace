package io.github.padlocks.endermanoverhaul.entity.client;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.custom.SoulsandValleyEndermanEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SoulsandValleyEndermanModel extends AnimatedGeoModel<SoulsandValleyEndermanEntity> {

    public static final ResourceLocation MODEL = new ResourceLocation("endermanoverhaul:geo/soulsand_valley_enderman.geo.json");
    public static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("endermanoverhaul:textures/entity/soulsand_valley/soulsand_valley_enderman_glow.png");

    @Override
    public ResourceLocation getModelLocation(SoulsandValleyEndermanEntity object) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/soulsand_valley_enderman.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SoulsandValleyEndermanEntity object) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/soulsand_valley/soulsand_valley_enderman.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SoulsandValleyEndermanEntity animatable) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/soulsand_valley_enderman.animation.json");
    }
}
