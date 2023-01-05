package io.github.padlocks.endermanoverhaul.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.padlocks.endermanoverhaul.entity.custom.SpiritEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SpiritRenderer extends GeoEntityRenderer<SpiritEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("endermanoverhaul:textures/entity/entities/projectile_1.png");
    
    public SpiritRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SpiritModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new SpiritGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SpiritEntity instance) {
        return TEXTURE;
    }


    @Override
    public RenderType getRenderType(SpiritEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
