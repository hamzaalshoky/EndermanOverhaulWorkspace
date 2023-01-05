package io.github.padlocks.endermanoverhaul.entity.client;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.custom.CaveEndermanEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CaveEndermanModel extends AnimatedGeoModel<CaveEndermanEntity> {

    public static final ResourceLocation MODEL = new ResourceLocation("endermanoverhaul:geo/cave_enderman.geo.json");
    public static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("endermanoverhaul:textures/entity/cave/cave_enderman_glow.png");

    @Override
    public ResourceLocation getModelLocation(CaveEndermanEntity object) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "geo/cave_enderman.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CaveEndermanEntity object) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/cave/cave_enderman.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CaveEndermanEntity animatable) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "animations/enderman.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(CaveEndermanEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}