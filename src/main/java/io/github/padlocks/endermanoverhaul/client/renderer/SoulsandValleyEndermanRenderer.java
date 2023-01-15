package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.client.model.SoulsandValleyEndermanModel;
import io.github.padlocks.endermanoverhaul.client.renderer.layer.BaseEndermanEyesLayer;
import io.github.padlocks.endermanoverhaul.entity.SoulsandValleyEndermanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SoulsandValleyEndermanRenderer extends BaseHostileEndermanRenderer<SoulsandValleyEndermanEntity> {
    public SoulsandValleyEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SoulsandValleyEndermanModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new BaseEndermanEyesLayer<>(this, new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/soulsand_valley/soulsand_valley_enderman_glow.png")));
    }
}
