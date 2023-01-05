package io.github.padlocks.endermanoverhaul.entity.custom;

import io.github.padlocks.endermanoverhaul.entity.custom.ai.ApplySlownessEffectMeleeAttackGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.Tags;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public class SnowyEndermanEntity extends EnderMan implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final UUID SPEED_MODIFIER_ATTACKING_UUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier SPEED_MODIFIER_ATTACKING = new AttributeModifier(SPEED_MODIFIER_ATTACKING_UUID, "Attacking speed boost", (double)0.1F, AttributeModifier.Operation.ADDITION);
    private static final int DELAY_BETWEEN_CREEPY_STARE_SOUND = 400;
    private static final int MIN_DEAGGRESSION_TIME = 600;
    private static final EntityDataAccessor<Optional<BlockState>> DATA_CARRY_STATE = SynchedEntityData.defineId(SnowyEndermanEntity.class, EntityDataSerializers.BLOCK_STATE);
    private static final EntityDataAccessor<Boolean> DATA_CREEPY = SynchedEntityData.defineId(SnowyEndermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_STARED_AT = SynchedEntityData.defineId(SnowyEndermanEntity.class, EntityDataSerializers.BOOLEAN);
    private int lastStareSound = Integer.MIN_VALUE;
    private int targetChangeTime;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @Nullable
    private UUID persistentAngerTarget;

    public SnowyEndermanEntity(EntityType<? extends SnowyEndermanEntity> p_32485_, Level p_32486_) {
        super(p_32485_, p_32486_);
        this.maxUpStep = 1.0F;
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SnowyEndermanEntity.SnowyEndermanEntityFreezeWhenLookedAt(this));
        this.goalSelector.addGoal(2, new ApplySlownessEffectMeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(10, new SnowyEndermanEntity.SnowyEndermanEntityLeaveBlockGoal(this));
        this.goalSelector.addGoal(11, new SnowyEndermanEntity.SnowyEndermanEntityTakeBlockGoal(this));
        this.targetSelector.addGoal(1, new SnowyEndermanEntity.SnowyEndermanEntityLookForPlayerGoal(this, this::isAngryAt));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Endermite.class, true, false));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 35.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }

    public void setTarget(@Nullable LivingEntity p_32537_) {
        AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (p_32537_ == null) {
            this.targetChangeTime = 0;
            this.entityData.set(DATA_CREEPY, false);
            this.entityData.set(DATA_STARED_AT, false);
            attributeinstance.removeModifier(SPEED_MODIFIER_ATTACKING);
        } else {
            this.targetChangeTime = this.tickCount;
            this.entityData.set(DATA_CREEPY, true);
            if (!attributeinstance.hasModifier(SPEED_MODIFIER_ATTACKING)) {
                attributeinstance.addTransientModifier(SPEED_MODIFIER_ATTACKING);
            }
        }

        super.setTarget(p_32537_); //Forge: Moved down to allow event handlers to write data manager values.
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_CARRY_STATE, Optional.empty());
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

    public void playStareSound() {
        if (this.tickCount >= this.lastStareSound + 400) {
            this.lastStareSound = this.tickCount;
            if (!this.isSilent()) {
                this.level.playLocalSound(this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENDERMAN_STARE, this.getSoundSource(), 2.5F, 1.0F, false);
            }
        }

    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_32513_) {
        if (DATA_CREEPY.equals(p_32513_) && this.hasBeenStaredAt() && this.level.isClientSide) {
            this.playStareSound();
        }

        super.onSyncedDataUpdated(p_32513_);
    }

    public void addAdditionalSaveData(CompoundTag p_32520_) {
        super.addAdditionalSaveData(p_32520_);
        BlockState blockstate = this.getCarriedBlock();
        if (blockstate != null) {
            p_32520_.put("carriedBlockState", NbtUtils.writeBlockState(blockstate));
        }

        this.addPersistentAngerSaveData(p_32520_);
    }

    public void readAdditionalSaveData(CompoundTag p_32511_) {
        super.readAdditionalSaveData(p_32511_);
        BlockState blockstate = null;
        if (p_32511_.contains("carriedBlockState", 10)) {
            blockstate = NbtUtils.readBlockState(p_32511_.getCompound("carriedBlockState"));
            if (blockstate.isAir()) {
                blockstate = null;
            }
        }

        this.setCarriedBlock(blockstate);
        this.readPersistentAngerSaveData(this.level, p_32511_);
    }

    boolean isLookingAtMe(Player p_32535_) {
        ItemStack itemstack = p_32535_.getInventory().armor.get(3);
        if (net.minecraftforge.common.ForgeHooks.shouldSuppressEnderManAnger(this, p_32535_, itemstack)) {
            return false;
        } else {
            Vec3 vec3 = p_32535_.getViewVector(1.0F).normalize();
            Vec3 vec31 = new Vec3(this.getX() - p_32535_.getX(), this.getEyeY() - p_32535_.getEyeY(), this.getZ() - p_32535_.getZ());
            double d0 = vec31.length();
            vec31 = vec31.normalize();
            double d1 = vec3.dot(vec31);
            return d1 > 1.0D - 0.025D / d0 ? p_32535_.hasLineOfSight(this) : false;
        }
    }

    protected float getStandingEyeHeight(Pose p_32517_, EntityDimensions p_32518_) {
        return 2.7F;
    }

    public void aiStep() {
        if (this.level.isClientSide) {
            for(int i = 0; i < 2; ++i) {
                this.level.addParticle(ParticleTypes.SNOWFLAKE, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.2D) * 1.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.2D) * 1.0D);
            }
        }

        this.jumping = false;
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level, true);
        }
        BlockPos blockpos = this.blockPosition();
        BlockPos blockpos1 = blockpos.below();
        if (!this.level.isClientSide && this.level.getBlockState(blockpos1).is(Blocks.WATER)) {
            int i = Mth.floor(this.getX());
            int j = Mth.floor(this.getY() - 1);
            int k = Mth.floor(this.getZ());
            BlockPos blockpos2 = new BlockPos(i, j - 1, k);
            Biome biome = this.level.getBiome(blockpos2).value();

            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
                return;
            }

            BlockState blockstate = Blocks.ICE.defaultBlockState();

            for(int l = 0; l < 4; ++l) {
                i = Mth.floor(this.getX() + (double)((float)(l % 2 * 2 - 1) * 0.25F));
                j = Mth.floor(this.getY());
                k = Mth.floor(this.getZ() + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockpos3 = this.blockPosition().below();
                if (this.level.isEmptyBlock(blockpos) && blockstate.canSurvive(this.level, blockpos)) {
                    this.level.setBlockAndUpdate(blockpos3, blockstate);
                }
            }
        }

        super.aiStep();
    }

    public boolean isSensitiveToWater() {
        return true;
    }

    @Override
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

    protected void dropCustomDeathLoot(DamageSource p_32497_, int p_32498_, boolean p_32499_) {
        super.dropCustomDeathLoot(p_32497_, p_32498_, p_32499_);
        BlockState blockstate = this.getCarriedBlock();
        if (blockstate != null) {
            this.spawnAtLocation(blockstate.getBlock());
        }

    }

    public void setCarriedBlock(@Nullable BlockState p_32522_) {
        this.entityData.set(DATA_CARRY_STATE, Optional.ofNullable(p_32522_));
    }

    @Nullable
    public BlockState getCarriedBlock() {
        return this.entityData.get(DATA_CARRY_STATE).orElse((BlockState)null);
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

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.getCarriedBlock() != null;
    }

    static class SnowyEndermanEntityFreezeWhenLookedAt extends Goal {
        private final SnowyEndermanEntity SnowyEndermanEntity;
        @Nullable
        private LivingEntity target;

        public SnowyEndermanEntityFreezeWhenLookedAt(SnowyEndermanEntity p_32550_) {
            this.SnowyEndermanEntity = p_32550_;
            this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
        }

        public boolean canUse() {
            this.target = this.SnowyEndermanEntity.getTarget();
            if (!(this.target instanceof Player)) {
                return false;
            } else {
                double d0 = this.target.distanceToSqr(this.SnowyEndermanEntity);
                return d0 > 256.0D ? false : this.SnowyEndermanEntity.isLookingAtMe((Player)this.target);
            }
        }

        public void start() {
            this.SnowyEndermanEntity.getNavigation().stop();
        }

        public void tick() {
            this.SnowyEndermanEntity.getLookControl().setLookAt(this.target.getX(), this.target.getEyeY(), this.target.getZ());
        }
    }

    static class SnowyEndermanEntityLeaveBlockGoal extends Goal {
        private final SnowyEndermanEntity SnowyEndermanEntity;

        public SnowyEndermanEntityLeaveBlockGoal(SnowyEndermanEntity p_32556_) {
            this.SnowyEndermanEntity = p_32556_;
        }

        public boolean canUse() {
            if (this.SnowyEndermanEntity.getCarriedBlock() == null) {
                return false;
            } else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.SnowyEndermanEntity.level, this.SnowyEndermanEntity)) {
                return false;
            } else {
                return this.SnowyEndermanEntity.getRandom().nextInt(reducedTickDelay(2000)) == 0;
            }
        }

        public void tick() {
            Random random = this.SnowyEndermanEntity.getRandom();
            Level level = this.SnowyEndermanEntity.level;
            int i = Mth.floor(this.SnowyEndermanEntity.getX() - 1.0D + random.nextDouble() * 2.0D);
            int j = Mth.floor(this.SnowyEndermanEntity.getY() + random.nextDouble() * 2.0D);
            int k = Mth.floor(this.SnowyEndermanEntity.getZ() - 1.0D + random.nextDouble() * 2.0D);
            BlockPos blockpos = new BlockPos(i, j, k);
            BlockState blockstate = level.getBlockState(blockpos);
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate1 = level.getBlockState(blockpos1);
            BlockState blockstate2 = this.SnowyEndermanEntity.getCarriedBlock();
            if (blockstate2 != null) {
                blockstate2 = Block.updateFromNeighbourShapes(blockstate2, this.SnowyEndermanEntity.level, blockpos);
                if (this.canPlaceBlock(level, blockpos, blockstate2, blockstate, blockstate1, blockpos1) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(SnowyEndermanEntity, net.minecraftforge.common.util.BlockSnapshot.create(level.dimension(), level, blockpos1), Direction.UP)) {
                    level.setBlock(blockpos, blockstate2, 3);
                    level.gameEvent(this.SnowyEndermanEntity, GameEvent.BLOCK_PLACE, blockpos);
                    this.SnowyEndermanEntity.setCarriedBlock((BlockState)null);
                }

            }
        }

        private boolean canPlaceBlock(Level p_32559_, BlockPos p_32560_, BlockState p_32561_, BlockState p_32562_, BlockState p_32563_, BlockPos p_32564_) {
            return p_32562_.isAir() && !p_32563_.isAir() && !p_32563_.is(Blocks.BEDROCK) && !p_32563_.is(Tags.Blocks.ENDERMAN_PLACE_ON_BLACKLIST) && p_32563_.isCollisionShapeFullBlock(p_32559_, p_32564_) && p_32561_.canSurvive(p_32559_, p_32560_) && p_32559_.getEntities(this.SnowyEndermanEntity, AABB.unitCubeFromLowerCorner(Vec3.atLowerCornerOf(p_32560_))).isEmpty();
        }
    }

    static class SnowyEndermanEntityLookForPlayerGoal extends NearestAttackableTargetGoal<Player> {
        private final SnowyEndermanEntity SnowyEndermanEntity;
        @Nullable
        private Player pendingTarget;
        private int aggroTime;
        private int teleportTime;
        private final TargetingConditions startAggroTargetConditions;
        private final TargetingConditions continueAggroTargetConditions = TargetingConditions.forCombat().ignoreLineOfSight();

        public SnowyEndermanEntityLookForPlayerGoal(SnowyEndermanEntity p_32573_, @Nullable Predicate<LivingEntity> p_32574_) {
            super(p_32573_, Player.class, 10, false, false, p_32574_);
            this.SnowyEndermanEntity = p_32573_;
            this.startAggroTargetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector((p_32578_) -> {
                return p_32573_.isLookingAtMe((Player)p_32578_);
            });
        }

        public boolean canUse() {
            this.pendingTarget = this.SnowyEndermanEntity.level.getNearestPlayer(this.startAggroTargetConditions, this.SnowyEndermanEntity);
            return this.pendingTarget != null;
        }

        public void start() {
            this.aggroTime = this.adjustedTickDelay(5);
            this.teleportTime = 0;
            this.SnowyEndermanEntity.setBeingStaredAt();
        }

        public void stop() {
            this.pendingTarget = null;
            super.stop();
        }

        public boolean canContinueToUse() {
            if (this.pendingTarget != null) {
                if (!this.SnowyEndermanEntity.isLookingAtMe(this.pendingTarget)) {
                    return false;
                } else {
                    this.SnowyEndermanEntity.lookAt(this.pendingTarget, 10.0F, 10.0F);
                    return true;
                }
            } else {
                return this.target != null && this.continueAggroTargetConditions.test(this.SnowyEndermanEntity, this.target) ? true : super.canContinueToUse();
            }
        }

        public void tick() {
            if (this.SnowyEndermanEntity.getTarget() == null) {
                super.setTarget((LivingEntity)null);
            }

            if (this.pendingTarget != null) {
                if (--this.aggroTime <= 0) {
                    this.target = this.pendingTarget;
                    this.pendingTarget = null;
                    super.start();
                }
            } else {
                if (this.target != null && !this.SnowyEndermanEntity.isPassenger()) {
                    if (this.SnowyEndermanEntity.isLookingAtMe((Player)this.target)) {
                        if (this.target.distanceToSqr(this.SnowyEndermanEntity) < 16.0D) {
                            this.SnowyEndermanEntity.teleport();
                        }

                        this.teleportTime = 0;
                    } else if (this.target.distanceToSqr(this.SnowyEndermanEntity) > 256.0D && this.teleportTime++ >= this.adjustedTickDelay(30) && this.SnowyEndermanEntity.teleportTowards(this.target)) {
                        this.teleportTime = 0;
                    }
                }

                super.tick();
            }

        }
    }

    static class SnowyEndermanEntityTakeBlockGoal extends Goal {
        private final SnowyEndermanEntity SnowyEndermanEntity;

        public SnowyEndermanEntityTakeBlockGoal(SnowyEndermanEntity p_32585_) {
            this.SnowyEndermanEntity = p_32585_;
        }

        public boolean canUse() {
            if (this.SnowyEndermanEntity.getCarriedBlock() != null) {
                return false;
            } else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.SnowyEndermanEntity.level, this.SnowyEndermanEntity)) {
                return false;
            } else {
                return this.SnowyEndermanEntity.getRandom().nextInt(reducedTickDelay(20)) == 0;
            }
        }

        public void tick() {
            Random random = this.SnowyEndermanEntity.getRandom();
            Level level = this.SnowyEndermanEntity.level;
            int i = Mth.floor(this.SnowyEndermanEntity.getX() - 2.0D + random.nextDouble() * 4.0D);
            int j = Mth.floor(this.SnowyEndermanEntity.getY() + random.nextDouble() * 3.0D);
            int k = Mth.floor(this.SnowyEndermanEntity.getZ() - 2.0D + random.nextDouble() * 4.0D);
            BlockPos blockpos = new BlockPos(i, j, k);
            BlockState blockstate = level.getBlockState(blockpos);
            Vec3 vec3 = new Vec3((double)this.SnowyEndermanEntity.getBlockX() + 0.5D, (double)j + 0.5D, (double)this.SnowyEndermanEntity.getBlockZ() + 0.5D);
            Vec3 vec31 = new Vec3((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D);
            BlockHitResult blockhitresult = level.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, this.SnowyEndermanEntity));
            boolean flag = blockhitresult.getBlockPos().equals(blockpos);
            if (blockstate.is(BlockTags.ENDERMAN_HOLDABLE) && flag) {
                level.removeBlock(blockpos, false);
                level.gameEvent(this.SnowyEndermanEntity, GameEvent.BLOCK_DESTROY, blockpos);
                this.SnowyEndermanEntity.setCarriedBlock(blockstate.getBlock().defaultBlockState());
            }

        }
    }
    
    
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving() && !this.hasBeenStaredAt()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.enderman.walk", true));
            return PlayState.CONTINUE;
        }

        if (this.hasBeenStaredAt() || this.isCreepy()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.enderman.run", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.enderman.idle", true));
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

    public void tick(Level p_45020_, BlockPos p_45021_, int p_45022_){
        super.tick();
        if (this.isOnGround()) {
            BlockState blockstate = Blocks.FROSTED_ICE.defaultBlockState();
            float f = (float)Math.min(16, 2 + p_45022_);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(BlockPos blockpos : BlockPos.betweenClosed(p_45021_.offset((double)(-f), -1.0D, (double)(-f)), p_45021_.offset((double)f, -1.0D, (double)f))) {
                if (blockpos.closerToCenterThan(this.position(), (double)f)) {
                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = p_45020_.getBlockState(blockpos$mutableblockpos);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = p_45020_.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.WATER && blockstate2.getValue(LiquidBlock.LEVEL) == 0; //TODO: Forge, modded waters?
                        if (blockstate2.getMaterial() == Material.WATER && isFull && blockstate.canSurvive(p_45020_, blockpos) && p_45020_.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(this, net.minecraftforge.common.util.BlockSnapshot.create(p_45020_.dimension(), p_45020_, blockpos), net.minecraft.core.Direction.UP)) {
                            p_45020_.setBlockAndUpdate(blockpos, blockstate);
                            p_45020_.scheduleTick(blockpos, Blocks.FROSTED_ICE, Mth.nextInt(this.getRandom(), 60, 120));
                        }
                    }
                }
            }

        }
    }
}
