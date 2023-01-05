package io.github.padlocks.endermanoverhaul.entity.client;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.custom.EndEndermanEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EndEndermanModel extends AnimatedGeoModel<EndEndermanEntity> {

    public static final ResourceLocation MODEL = new ResourceLocation("endermanoverhaul:geo/end_enderman.geo.json");
    public static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("endermanoverhaul:textures/entity/end/end_enderman_glow.png");
    
    @Override
    public ResourceLocation getModelLocation(EndEndermanEntity object) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/end_enderman.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EndEndermanEntity object) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/end/end_enderman.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EndEndermanEntity animatable) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/soulsand_valley_enderman.animation.json");
    }
}
