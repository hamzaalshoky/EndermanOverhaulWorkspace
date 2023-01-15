package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.client.model.IceSpikesEndermanModel;
import io.github.padlocks.endermanoverhaul.client.renderer.layer.BaseEndermanEyesLayer;
import io.github.padlocks.endermanoverhaul.entity.IceSpikesEndermanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class IceSpikesEndermanRenderer extends BaseHostileEndermanRenderer<IceSpikesEndermanEntity> {
    public IceSpikesEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new IceSpikesEndermanModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new BaseEndermanEyesLayer<>(this, new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/ice_spikes/ice_spikes_enderman_glow.png")));
    }
}
