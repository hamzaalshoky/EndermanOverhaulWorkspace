package io.github.padlocks.endermanoverhaul.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.padlocks.endermanoverhaul.entity.custom.BadlandsEndermanEntity;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@OnlyIn(Dist.CLIENT)
public class EndermanEyesLayer<E extends BadlandsEndermanEntity> extends GeoLayerRenderer<E> {

    public EndermanEyesLayer(IGeoRenderer<E> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource buffer, int packedLightIn, E creeper, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        GeoModel normalModel = this.getEntityModel().getModel(BadlandsEndermanModel.MODEL);
        VertexConsumer glowConsumer = buffer.getBuffer(RenderTypes.getTransparentEyes(BadlandsEndermanModel.GLOW_TEXTURE));
            getRenderer().render(normalModel, creeper, partialTicks,
                    null, stack, null, glowConsumer,
                    packedLightIn, OverlayTexture.NO_OVERLAY,
                    1f, 1f, 1f, 1f);

    }
}
