package io.github.padlocks.endermanoverhaul.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.padlocks.endermanoverhaul.entity.custom.SwampEndermanEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@OnlyIn(Dist.CLIENT)
public class SwampEndermanEyesLayer<E extends SwampEndermanEntity> extends GeoLayerRenderer<E> {

    public SwampEndermanEyesLayer(IGeoRenderer<E> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource buffer, int packedLightIn, E creeper, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        GeoModel normalModel = this.getEntityModel().getModel(SwampEndermanModel.MODEL);
        VertexConsumer glowConsumer = buffer.getBuffer(RenderTypes.getTransparentEyes(SwampEndermanModel.GLOW_TEXTURE));
            getRenderer().render(normalModel, creeper, partialTicks,
                    null, stack, null, glowConsumer,
                    packedLightIn, OverlayTexture.NO_OVERLAY,
                    1f, 1f, 1f, 1f);

    }
}