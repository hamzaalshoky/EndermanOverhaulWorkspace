package io.github.padlocks.endermanoverhaul.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.ExtendedGeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class BaseEndermanRenderer<T extends LivingEntity & IAnimatable> extends ExtendedGeoEntityRenderer<T> {

    public BaseEndermanRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel<T> modelProvider) {
        super(renderManager, modelProvider);
    }

    @Override
    protected boolean isArmorBone(GeoBone bone) {
        return false;
    }

    @Nullable
    @Override
    protected ResourceLocation getTextureForBone(String boneName, T currentEntity) {
        return null;
    }

    @Nullable
    @Override
    protected ItemStack getHeldItemForBone(String boneName, T currentEntity) {
        if (boneName.equals("block") && currentEntity instanceof EnderMan enderMan && enderMan.getCarriedBlock() != null) {
            return enderMan.getCarriedBlock().getBlock().asItem().getDefaultInstance().copy();
        }

        return null;
    }

    @Override
    protected ItemTransforms.TransformType getCameraTransformForItemAtBone(ItemStack boneItem, String boneName) {
        return ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND;
    }

    @Nullable
    @Override
    protected BlockState getHeldBlockForBone(String boneName, T currentEntity) {
        return null;
    }

    @Override
    protected void preRenderItem(PoseStack matrixStack, ItemStack item, String boneName, T currentEntity, IBone bone) {
        matrixStack.pushPose();
        matrixStack.scale(1.75F, 1.75F, 1.75F);
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(45.0F));
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(10.0F));
        matrixStack.translate(0.0D, 0.05D, -0.1D);
    }

    @Override
    protected void preRenderBlock(BlockState block, String boneName, T currentEntity) {

    }

    @Override
    protected void postRenderItem(PoseStack matrixStack, ItemStack item, String boneName, T currentEntity, IBone bone) {
        matrixStack.popPose();
    }

    @Override
    protected void postRenderBlock(BlockState block, String boneName, T currentEntity) {

    }
}
