package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.client.model.FlowerFieldsEndermanModel;
import io.github.padlocks.endermanoverhaul.client.renderer.layer.BaseEndermanEyesLayer;
import io.github.padlocks.endermanoverhaul.entity.FlowerFieldsEndermanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FlowerFieldsEndermanRenderer extends BaseEndermanRenderer<FlowerFieldsEndermanEntity> {
    public FlowerFieldsEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FlowerFieldsEndermanModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new BaseEndermanEyesLayer<>(this, new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/flower_fields/flower_fields_enderman_glow.png")));
    }
}
