package io.github.padlocks.endermanoverhaul.client.renderer;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.client.model.NetherWastesEndermanModel;
import io.github.padlocks.endermanoverhaul.client.renderer.layer.BaseEndermanEyesLayer;
import io.github.padlocks.endermanoverhaul.entity.NetherWastesEndermanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class NetherWastesEndermanRenderer extends BaseHostileEndermanRenderer<NetherWastesEndermanEntity> {
    public NetherWastesEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NetherWastesEndermanModel());
        this.shadowRadius = 0.3f;
        this.addLayer(new BaseEndermanEyesLayer<>(this, new ResourceLocation(EndermanOverhaul.MOD_ID, "textures/entity/nether_wastes/nether_wastes_enderman_glow.png")));
    }
}
