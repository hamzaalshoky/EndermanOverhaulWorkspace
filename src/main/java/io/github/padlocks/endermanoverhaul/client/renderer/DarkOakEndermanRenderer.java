package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.client.model.DarkOakEndermanModel;
import io.github.padlocks.endermanoverhaul.client.renderer.layer.BaseEndermanEyesLayer;
import io.github.padlocks.endermanoverhaul.entity.DarkOakEndermanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class DarkOakEndermanRenderer extends BaseHostileEndermanRenderer<DarkOakEndermanEntity> {
    public DarkOakEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DarkOakEndermanModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new BaseEndermanEyesLayer<>(this, new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/dark_oak/dark_oak_enderman_glow.png")));
    }
}
