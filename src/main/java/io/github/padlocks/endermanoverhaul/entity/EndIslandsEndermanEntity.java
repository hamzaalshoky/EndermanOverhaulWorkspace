package io.github.padlocks.endermanoverhaul.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public class EndIslandsEndermanEntity extends EnderMan implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public EndIslandsEndermanEntity(EntityType<? extends EndIslandsEndermanEntity> p_32485_, Level p_32486_) {
        super(p_32485_, p_32486_);
        this.maxUpStep = 1.0F;
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(EnderMan.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(SavannaEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(BadlandsEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(CaveEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(CrimsonForestEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(DarkOakEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(DesertEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(EndEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(FlowerFieldsEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(IceSpikesEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(MushroomEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(NetherWastesEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(OceanEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(SnowyEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(SoulsandValleyEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(SwampEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(WarpedForestEndermanEntity.class));
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers(WindsweptHillsEndermanEntity.class));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Endermite.class, true, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
    }

    @Override
    public void setTarget(@javax.annotation.Nullable LivingEntity p_32537_) {
        super.setTarget(p_32537_); //Forge: Moved down to allow event handlers to write data manager values.
    }

    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.2f).build();
    }

    @Override
    public void aiStep() {
        if (this.level.isClientSide) {
            for(int i = 0; i < 2; ++i) {
                this.level.addParticle(ParticleTypes.PORTAL, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.2D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.2D) * 2.0D);
            }
        }

        this.jumping = false;
        super.aiStep();
    }

    public boolean isSensitiveToWater() {
        return true;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENDERMAN_SCREAM;
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
        }else if(this.getRandom().nextInt(5) == 0){
            boolean flag2 = super.hurt(p_32494_, p_32495_);
            double range = 30;
            List<? extends EnderMan> list = this.level.getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(range, range/2, range));
            for(EnderMan gaz : list){
                if(this.getTarget() != null){
                    gaz.isAggressive();
                    gaz.setTarget(this.getTarget());
                    gaz.setBeingStaredAt();
                    gaz.isAngryAtAllPlayers(level);
                    gaz.isAngryAt(this.getTarget());
                }else{
                    return flag2;
                }
            }
            return flag2;
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
    
    
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("enderman_walk", true));
            return PlayState.CONTINUE;
        }

        if (this.hurt(DamageSource.GENERIC, 1f)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("end_islands_enderman_possess", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("enderman_idle", true));
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

}
