package io.github.padlocks.endermanoverhaul.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.padlocks.endermanoverhaul.client.renderer.RenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@OnlyIn(Dist.CLIENT)
public class BaseEndermanEyesLayer<T extends LivingEntity & IAnimatable> extends GeoLayerRenderer<T> {
    private final ResourceLocation GLOW_TEXTURE;

    public BaseEndermanEyesLayer(IGeoRenderer<T> entityRendererIn, ResourceLocation glowLocation) {
        super(entityRendererIn);
        this.GLOW_TEXTURE = glowLocation;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        GeoModel model = this.getEntityModel().getModel(this.getEntityModel().getModelLocation(entityLivingBaseIn));
        RenderType renderType = RenderTypes.getTransparentEyes(GLOW_TEXTURE);

        this.getRenderer().render(model,
                entityLivingBaseIn,
                partialTicks,
                renderType,
                matrixStackIn,
                bufferIn,
                bufferIn.getBuffer(renderType),
                packedLightIn,
                LivingEntityRenderer.getOverlayCoords(entityLivingBaseIn, 0.0F),
                1f, 1f, 1f, 1f);
    }
}
