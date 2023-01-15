package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.client.model.WarpedForestEndermanModel;
import io.github.padlocks.endermanoverhaul.client.renderer.layer.BaseEndermanEyesLayer;
import io.github.padlocks.endermanoverhaul.entity.WarpedForestEndermanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class WarpedForestEndermanRenderer extends BaseHostileEndermanRenderer<WarpedForestEndermanEntity> {
    public WarpedForestEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WarpedForestEndermanModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new BaseEndermanEyesLayer<>(this, new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/warped_forest/warped_forest_enderman_glow.png")));
    }
}
