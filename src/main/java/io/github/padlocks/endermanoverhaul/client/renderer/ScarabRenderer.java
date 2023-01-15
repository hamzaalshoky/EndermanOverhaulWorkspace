package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.client.model.ScarabModel;
import io.github.padlocks.endermanoverhaul.entity.ScarabEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ScarabRenderer extends GeoEntityRenderer<ScarabEntity> {
    public ScarabRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ScarabModel());
        this.shadowRadius = 0.3f;
    }
}
