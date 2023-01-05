package io.github.padlocks.endermanoverhaul.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.custom.FlowerFieldsEndermanEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Random;

public class FlowerFieldsEndermanRenderer extends GeoEntityRenderer<FlowerFieldsEndermanEntity> {

    private final Random random = new Random();

    public FlowerFieldsEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FlowerFieldsEndermanModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new FlowerFieldsEndermanEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(FlowerFieldsEndermanEntity instance) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/flower_fields/flower_fields_enderman.png");
    }


    @Override
    public RenderType getRenderType(FlowerFieldsEndermanEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
