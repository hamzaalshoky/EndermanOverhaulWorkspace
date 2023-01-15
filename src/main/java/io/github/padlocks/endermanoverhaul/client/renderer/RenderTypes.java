package io.github.padlocks.endermanoverhaul.client.renderer;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class RenderTypes extends RenderType {

    public RenderTypes(String string, VertexFormat vertexFormat, VertexFormat.Mode mode, int i, boolean bl, boolean bl2, Runnable runnable, Runnable runnable2) {
        super(string, vertexFormat, mode, i, bl, bl2, runnable, runnable2);
    }
    public static RenderType getTransparentEyes(ResourceLocation location) {
        return create("eyes",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS, 256,
                false, true,
                RenderType.CompositeState.builder()
                        .setShaderState(RENDERTYPE_EYES_SHADER)
                        .setTextureState(new RenderStateShard.TextureStateShard(location, false, false))
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setWriteMaskState(COLOR_WRITE)
                        .createCompositeState(false)
        );
    }
}
