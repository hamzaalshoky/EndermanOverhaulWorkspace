package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.client.model.SpiritModel;
import io.github.padlocks.endermanoverhaul.client.renderer.layer.SpiritGlowLayer;
import io.github.padlocks.endermanoverhaul.entity.SpiritEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SpiritRenderer extends GeoEntityRenderer<SpiritEntity> {
    public SpiritRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SpiritModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new SpiritGlowLayer<>(this));
    }
}
