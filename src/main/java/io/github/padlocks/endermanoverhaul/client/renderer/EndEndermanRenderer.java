package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.client.model.EndEndermanModel;
import io.github.padlocks.endermanoverhaul.client.renderer.layer.BaseEndermanEyesLayer;
import io.github.padlocks.endermanoverhaul.entity.EndEndermanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EndEndermanRenderer extends BaseEndermanRenderer<EndEndermanEntity> {
    public EndEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EndEndermanModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new BaseEndermanEyesLayer<>(this, new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/end/end_enderman_glow.png")));
    }
}
