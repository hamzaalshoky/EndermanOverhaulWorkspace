package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.client.model.SavannaEndermanModel;
import io.github.padlocks.endermanoverhaul.client.renderer.layer.BaseEndermanEyesLayer;
import io.github.padlocks.endermanoverhaul.entity.SavannaEndermanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SavannaEndermanRenderer extends BaseHostileEndermanRenderer<SavannaEndermanEntity> {
    public SavannaEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SavannaEndermanModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new BaseEndermanEyesLayer<>(this, new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/savanna/savanna_enderman_glow.png")));
    }
}
