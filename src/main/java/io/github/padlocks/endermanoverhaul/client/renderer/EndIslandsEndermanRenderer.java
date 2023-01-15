package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.client.model.EndIslandsEndermanModel;
import io.github.padlocks.endermanoverhaul.entity.EndIslandsEndermanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class EndIslandsEndermanRenderer extends BaseHostileEndermanRenderer<EndIslandsEndermanEntity> {
    public EndIslandsEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EndIslandsEndermanModel());
        this.shadowRadius = 0.3f;
    }
}
