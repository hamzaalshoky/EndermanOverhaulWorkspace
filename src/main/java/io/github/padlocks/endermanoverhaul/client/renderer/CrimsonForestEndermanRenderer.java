package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.client.model.CrimsonForestEndermanModel;
import io.github.padlocks.endermanoverhaul.client.renderer.layer.BaseEndermanEyesLayer;
import io.github.padlocks.endermanoverhaul.entity.CrimsonForestEndermanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class CrimsonForestEndermanRenderer extends BaseHostileEndermanRenderer<CrimsonForestEndermanEntity> {
    public CrimsonForestEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CrimsonForestEndermanModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new BaseEndermanEyesLayer<>(this, new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/crimson_forest/crimson_forest_enderman_glow.png")));
    }
}
