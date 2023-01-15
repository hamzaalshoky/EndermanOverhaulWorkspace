package io.github.padlocks.endermanoverhaul.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class BaseHostileEndermanRenderer<T extends EnderMan & IAnimatable> extends BaseEndermanRenderer<T> {
    private final Random random = new Random();

    public BaseHostileEndermanRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel<T> modelProvider) {
        super(renderManager, modelProvider);
    }

    @Override
    public Vec3 getRenderOffset(T enderman, float partialTicks) {
        if (enderman.isCreepy()) {
            double offset = 0.02D;
            return new Vec3(this.random.nextGaussian() * offset, 0.0D, this.random.nextGaussian() * offset);
        } else {
            return super.getRenderOffset(enderman, partialTicks);
        }
    }
}
