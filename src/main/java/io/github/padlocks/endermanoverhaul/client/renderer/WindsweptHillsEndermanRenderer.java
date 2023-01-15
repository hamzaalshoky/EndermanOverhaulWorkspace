package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.client.model.WindsweptHillsEndermanModel;
import io.github.padlocks.endermanoverhaul.client.renderer.layer.BaseEndermanEyesLayer;
import io.github.padlocks.endermanoverhaul.entity.WindsweptHillsEndermanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class WindsweptHillsEndermanRenderer extends BaseHostileEndermanRenderer<WindsweptHillsEndermanEntity> {
    public WindsweptHillsEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WindsweptHillsEndermanModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new BaseEndermanEyesLayer<>(this, new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/windswept_hills/windswept_hills_enderman_glow.png")));
    }
}
