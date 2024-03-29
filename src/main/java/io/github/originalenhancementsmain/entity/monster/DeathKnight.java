package io.github.originalenhancementsmain.entity.monster;

import io.github.originalenhancementsmain.OESounds;
import io.github.originalenhancementsmain.data.util.OEDamageSources;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

public class DeathKnight extends TamableAnimal {

    public DeathKnight(EntityType<? extends DeathKnight> type, Level world){
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, true));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Monster.class, true));
    }

    @Override
    public Animal getBreedOffspring(ServerLevel level, AgeableMob animal) {
        return null;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ARMOR, 8.0D)
                .add(Attributes.ATTACK_DAMAGE, 20.0D);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        boolean success = entity.hurt(DamageSource.mobAttack(this), 7.0F);

        if (success) {
            entity.push(0.0D, 0.2D, 0.0D);
        }

        return success;
    }

    @Override
    public void aiStep() {

        if (!this.getLevel().isClientSide() && this.getEffect(MobEffects.DAMAGE_BOOST) == null) {
            if(this.tickCount % 20 == 0) {
                this.hurt(new DamageSource(OEDamageSources.oeSource("expired")).bypassArmor().bypassMagic().bypassInvul().setMagic(), 30);
            }
        }

        super.aiStep();
    }

    @Override
    public InteractionResult interactAt(Player player, Vec3 vec3, InteractionHand hand) {
        if(this.getOwner() != null && this.getOwner().is(player) && player.getItemInHand(hand).is(Items.ROTTEN_FLESH)) {
            this.removeEffect(MobEffects.DAMAGE_BOOST);
            this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1));
            this.heal(1.0F);
            this.playSound(SoundEvents.ZOMBIE_INFECT, this.getSoundVolume(), this.getVoicePitch());
            if(!player.getAbilities().instabuild) player.getItemInHand(hand).shrink(1);
            return InteractionResult.sidedSuccess(this.getLevel().isClientSide());
        }

        return super.interactAt(player, vec3, hand);
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {
        if (!(target instanceof Creeper) && !(target instanceof Ghast)) {
            if (target instanceof DeathKnight zombie) {
                return !zombie.isTame() || zombie.getOwner() != owner;
            } else if (target instanceof Player pTarget && owner instanceof Player pOwner && !pOwner.canHarmPlayer(pTarget)) {
                return false;
            } else if (target instanceof AbstractHorse horse && horse.isTamed()) {
                return false;
            } else {
                return !(target instanceof TamableAnimal animal) || !animal.isTame();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return !this.isTame();
    }

    @Override
    public double getMyRidingOffset() {
        return -0.35D;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        Random ran = new Random();
        int co = ran.nextInt(3);
        if(co==0)
        {
            return OESounds.DEATH_KNIGHTAMBIENT1.get();
        }
        else if (co==1)
        {
            return OESounds.DEATH_KNIGHTAMBIENT2.get();
        }
        return OESounds.DEATH_KNIGHTAMBIENT3.get();

    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        Random ran = new Random();
        int co = ran.nextInt(3);
        if(co==0) {
            return OESounds.DEATH_KNIGHTHURT1.get();
        }
        return OESounds.DEATH_KNIGHTHURT2.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return OESounds.DEATH_KNIGHTDEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        Random ran = new Random();
        int co = ran.nextInt(5);
        if(co==0) {
            playSound(OESounds.DEATH_KNIGHTSTEP1, 0.15F, 1.0F);
        }
        else if (co==1) {
            playSound(OESounds.DEATH_KNIGHTSTEP2, 0.15F, 1.0F);
        }
        else if (co==2) {
            playSound(OESounds.DEATH_KNIGHTSTEP3, 0.15F, 1.0F);
        }
        else if (co==3){
            playSound(OESounds.DEATH_KNIGHTSTEP4, 0.15F, 1.0F);
        }
        playSound(OESounds.DEATH_KNIGHTSTEP5, 0.15F, 1.0F);
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    protected void dropExperience() {
    }

    public void playSound(RegistryObject<SoundEvent> loyalZombieSummon, float p_19939_, float voicePitch) {
    }


}
