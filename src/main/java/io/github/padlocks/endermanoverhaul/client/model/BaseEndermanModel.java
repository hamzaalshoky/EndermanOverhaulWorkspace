package io.github.padlocks.endermanoverhaul.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class BaseEndermanModel<T extends LivingEntity & IAnimatable> extends AnimatedGeoModel<T> {
    private final ResourceLocation MODEL;
    private final ResourceLocation TEXTURE;
    private final ResourceLocation ANIMATION;

    public BaseEndermanModel(ResourceLocation model, ResourceLocation texture, ResourceLocation animation) {
        this.MODEL = model;
        this.TEXTURE = texture;
        this.ANIMATION = animation;
    }

    @Override
    public ResourceLocation getModelLocation(T object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureLocation(T object) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(T animatable) {
        return ANIMATION;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(T entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
