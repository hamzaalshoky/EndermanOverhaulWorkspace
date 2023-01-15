package io.github.padlocks.endermanoverhaul.entity;

import io.github.padlocks.endermanoverhaul.entity.ai.ApplyBiteSoundMeleeAttackGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class SoulsandValleyEndermanEntity extends EnderMan implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final UUID SPEED_MODIFIER_ATTACKING_UUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier SPEED_MODIFIER_ATTACKING = new AttributeModifier(SPEED_MODIFIER_ATTACKING_UUID, "Attacking speed boost", (double)0.1F, AttributeModifier.Operation.ADDITION);
    private static final int DELAY_BETWEEN_CREEPY_STARE_SOUND = 400;
    private static final int MIN_DEAGGRESSION_TIME = 600;
    private static final EntityDataAccessor<Boolean> DATA_CREEPY = SynchedEntityData.defineId(SoulsandValleyEndermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_STARED_AT = SynchedEntityData.defineId(SoulsandValleyEndermanEntity.class, EntityDataSerializers.BOOLEAN);
    private int lastStareSound = Integer.MIN_VALUE;
    private int targetChangeTime;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @Nullable
    private UUID persistentAngerTarget;

    public SoulsandValleyEndermanEntity(EntityType<? extends SoulsandValleyEndermanEntity> p_32485_, Level p_32486_) {
        super(p_32485_, p_32486_);
        this.maxUpStep = 1.0F;
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new ApplyBiteSoundMeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Endermite.class, true, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }

    public void setTarget(@Nullable LivingEntity p_32537_) {
        if (p_32537_ == null) {
            this.targetChangeTime = 0;
            this.entityData.set(DATA_CREEPY, false);
            this.entityData.set(DATA_STARED_AT, false);
        } else {
            this.targetChangeTime = this.tickCount;
            this.entityData.set(DATA_CREEPY, true);
        }

        super.setTarget(p_32537_); //Forge: Moved down to allow event handlers to write data manager values.
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_CREEPY, false);
        this.entityData.define(DATA_STARED_AT, false);
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public void setRemainingPersistentAngerTime(int p_32515_) {
        this.remainingPersistentAngerTime = p_32515_;
    }

    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    public void setPersistentAngerTarget(@Nullable UUID p_32509_) {
        this.persistentAngerTarget = p_32509_;
    }

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_32513_) {
        if (DATA_CREEPY.equals(p_32513_) && this.hasBeenStaredAt() && this.level.isClientSide) {
            this.playStareSound();
        }

        super.onSyncedDataUpdated(p_32513_);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source == DamageSource.LAVA || source == DamageSource.ON_FIRE || source == DamageSource.IN_FIRE || super.isInvulnerableTo(source);
    }

    @Override
    public void aiStep() {
        if (this.level.isClientSide) {
            for(int i = 0; i < 2; ++i) {
                this.level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.2D) * 1.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.2D) * 1.0D);
            }
        }

        this.jumping = false;
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level, true);
        }

        super.aiStep();
    }

    public boolean isSensitiveToWater() {
        return true;
    }

    protected void customServerAiStep() {
        if (this.level.isDay() && this.tickCount >= this.targetChangeTime + 600) {
            float f = this.getBrightness();
            if (f > 0.5F && this.level.canSeeSky(this.blockPosition()) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.setTarget((LivingEntity)null);
                this.teleport();
            }
        }

        super.customServerAiStep();
    }

    protected boolean teleport() {
        if (!this.level.isClientSide() && this.isAlive()) {
            double d0 = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
            double d1 = this.getY() + (double)(this.random.nextInt(64) - 32);
            double d2 = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
            return this.teleport(d0, d1, d2);
        } else {
            return false;
        }
    }

    boolean teleportTowards(Entity p_32501_) {
        Vec3 vec3 = new Vec3(this.getX() - p_32501_.getX(), this.getY(0.5D) - p_32501_.getEyeY(), this.getZ() - p_32501_.getZ());
        vec3 = vec3.normalize();
        double d0 = 16.0D;
        double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3.x * 16.0D;
        double d2 = this.getY() + (double)(this.random.nextInt(16) - 8) - vec3.y * 16.0D;
        double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3.z * 16.0D;
        return this.teleport(d1, d2, d3);
    }

    private boolean teleport(double p_32544_, double p_32545_, double p_32546_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_32544_, p_32545_, p_32546_);

        while(blockpos$mutableblockpos.getY() > this.level.getMinBuildHeight() && !this.level.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMotion()) {
            blockpos$mutableblockpos.move(Direction.DOWN);
        }

        BlockState blockstate = this.level.getBlockState(blockpos$mutableblockpos);
        boolean flag = blockstate.getMaterial().blocksMotion();
        boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
        if (flag && !flag1) {
            net.minecraftforge.event.entity.EntityTeleportEvent.EnderEntity event = net.minecraftforge.event.ForgeEventFactory.onEnderTeleport(this, p_32544_, p_32545_, p_32546_);
            if (event.isCanceled()) return false;
            boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
            if (flag2 && !this.isSilent()) {
                this.level.playSound((Player)null, this.xo, this.yo, this.zo, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
                this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }

            return flag2;
        } else {
            return false;
        }
    }

    protected SoundEvent getAmbientSound() {
        return this.isCreepy() ? SoundEvents.ENDERMAN_SCREAM : SoundEvents.ENDERMAN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_32527_) {
        return SoundEvents.ENDERMAN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENDERMAN_DEATH;
    }

    public boolean hurt(DamageSource p_32494_, float p_32495_) {
        if (this.isInvulnerableTo(p_32494_)) {
            return false;
        } else if (p_32494_ instanceof IndirectEntityDamageSource) {
            Entity entity = p_32494_.getDirectEntity();
            boolean flag1;
            if (entity instanceof ThrownPotion) {
                flag1 = this.hurtWithCleanWater(p_32494_, (ThrownPotion)entity, p_32495_);
            } else {
                flag1 = false;
            }

            for(int i = 0; i < 64; ++i) {
                if (this.teleport()) {
                    return true;
                }
            }

            return flag1;
        } else {
            boolean flag = super.hurt(p_32494_, p_32495_);
            if (!this.level.isClientSide() && !(p_32494_.getEntity() instanceof LivingEntity) && this.random.nextInt(10) != 0) {
                this.teleport();
            }

            return flag;
        }
    }

    private boolean hurtWithCleanWater(DamageSource p_186273_, ThrownPotion p_186274_, float p_186275_) {
        ItemStack itemstack = p_186274_.getItem();
        Potion potion = PotionUtils.getPotion(itemstack);
        List<MobEffectInstance> list = PotionUtils.getMobEffects(itemstack);
        boolean flag = potion == Potions.WATER && list.isEmpty();
        return flag ? super.hurt(p_186273_, p_186275_) : false;
    }

    public boolean isCreepy() {
        return this.entityData.get(DATA_CREEPY);
    }

    public boolean hasBeenStaredAt() {
        return this.entityData.get(DATA_STARED_AT);
    }

    public void setBeingStaredAt() {
        this.entityData.set(DATA_STARED_AT, true);
    }
    
    
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("soulsand_valley_enderman_walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("soulsand_valley_enderman_idle", true));
        return PlayState.CONTINUE;
    }
    
    
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }


    @Override
    public void die(DamageSource p_21014_) {
        spawnSouls();
    }

    public void spawnSouls(){
        SpiritEntity minion = ModEntityCreator.SPIRIT.get().create(level);
        minion.setPos(this.getX(), this.getY() + 2, this.getZ());
        level.addFreshEntity(minion);
        level.addFreshEntity(minion);
        level.addFreshEntity(minion);
        level.addFreshEntity(minion);
        level.addFreshEntity(minion);
    }
}
