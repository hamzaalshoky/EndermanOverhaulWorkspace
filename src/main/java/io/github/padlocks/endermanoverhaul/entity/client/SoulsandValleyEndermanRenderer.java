package io.github.padlocks.endermanoverhaul.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.entity.custom.SoulsandValleyEndermanEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Random;

public class SoulsandValleyEndermanRenderer extends GeoEntityRenderer<SoulsandValleyEndermanEntity> {

    private final Random random = new Random();

    public SoulsandValleyEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SoulsandValleyEndermanModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new SoulsandValleyEndermanEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SoulsandValleyEndermanEntity instance) {
        return new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/soulsand_valley/soulsand_valley_enderman.png");
    }


    @Override
    public RenderType getRenderType(SoulsandValleyEndermanEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }


    public Vec3 getRenderOffset(SoulsandValleyEndermanEntity p_114336_, float p_114337_) {
        if (p_114336_.isCreepy()) {
            double d0 = 0.02D;
            return new Vec3(this.random.nextGaussian() * 0.02D, 0.0D, this.random.nextGaussian() * 0.02D);
        } else {
            return super.getRenderOffset(p_114336_, p_114337_);
        }
    }
}
