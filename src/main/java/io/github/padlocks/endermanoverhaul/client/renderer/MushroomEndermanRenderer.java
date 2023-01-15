package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.client.model.MushroomEndermanModel;
import io.github.padlocks.endermanoverhaul.entity.MushroomEndermanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class MushroomEndermanRenderer extends BaseEndermanRenderer<MushroomEndermanEntity> {
    public MushroomEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MushroomEndermanModel());
        this.shadowRadius = 0.3f;
    }
}
