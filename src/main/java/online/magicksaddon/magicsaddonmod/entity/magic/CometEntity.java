package online.magicksaddon.magicsaddonmod.entity.magic;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.damagesource.DarknessDamageSource;
import online.kingdomkeys.kingdomkeys.lib.DamageCalculation;
import online.kingdomkeys.kingdomkeys.lib.Party;
import online.kingdomkeys.kingdomkeys.util.Utils;
import online.magicksaddon.magicsaddonmod.entity.ModEntitiesMA;

import java.util.List;

public class CometEntity extends ThrowableProjectile {
        int maxTicks = 200, radius = 2;
        float dmgMult = 1;
        int index = 0;


        public CometEntity(EntityType<? extends ThrowableProjectile> type, Level world) {
                super(type, world);
                this.blocksBuilding = true;
        }

        public CometEntity(PlayMessages.SpawnEntity spawnEntity, Level world) {
                super(ModEntitiesMA.TYPE_COMET.get(), world);
        }

        public CometEntity(Level world) {
                super(ModEntitiesMA.TYPE_COMET.get(), world);
                this.blocksBuilding = true;
        }

        public CometEntity(Level world, LivingEntity player, float dmgMult, int radius, int index) {
                super(ModEntitiesMA.TYPE_COMET.get(), player, world);
                this.dmgMult = dmgMult;
                this.radius = radius;
                this.index = index;
        }
        @Override
        public Packet<ClientGamePacketListener> getAddEntityPacket() {
                return (Packet<ClientGamePacketListener>) NetworkHooks.getEntitySpawningPacket(this);
        }

        @Override
        protected float getGravity() {
                return 0F;
        }

        @Override
        public void tick() {
                if (this.tickCount > maxTicks) {
                        this.remove(RemovalReason.KILLED);
                }

                //world.addParticle(ParticleTypes.ENTITY_EFFECT, getPosX(), getPosY(), getPosZ(), 1, 1, 0);
                if(tickCount > 2)
                        level.addParticle(ParticleTypes.FIREWORK, getX(), getY(), getZ(), 0, 0, 0);

                super.tick();
        }

        @Override
        protected void onHit(HitResult rtRes) {
                if (!level.isClientSide && getOwner() != null) {
                        EntityHitResult ertResult = null;
                        BlockHitResult brtResult = null;

                        if (rtRes instanceof EntityHitResult) {
                                ertResult = (EntityHitResult) rtRes;
                        }

                        if (rtRes instanceof BlockHitResult) {
                                brtResult = (BlockHitResult) rtRes;
                        }

                        if (ertResult != null && ertResult.getEntity() instanceof LivingEntity) {
                                LivingEntity target = (LivingEntity) ertResult.getEntity();

                                if (target != getOwner()) {
                                        Party p = null;
                                        if (getOwner() != null) {
                                                p = ModCapabilities.getWorld(getOwner().level).getPartyFromMember(getOwner().getUUID());
                                        }
                                        if(p == null || (p.getMember(target.getUUID()) == null || p.getFriendlyFire())) { //If caster is not in a party || the party doesn't have the target in it || the party has FF on
                                                float dmg = this.getOwner() instanceof Player ? DamageCalculation.getMagicDamage((Player) this.getOwner()) * 0.2F : 2;
                                                //target.hurt(DarknessDamageSource.getDarknessDamage(this, this.getOwner()), dmg * dmgMult);
                                                if(this.getOwner() instanceof Player) {
                                                        List<LivingEntity> targetList = Utils.getLivingEntitiesInRadiusExcludingParty((Player) this.getOwner(), this, radius,radius,radius);
                                                        System.out.println(targetList);
                                                        for(LivingEntity e : targetList) {
                                                                e.hurt(DarknessDamageSource.getDarknessDamage(this, this.getOwner()), dmg * dmgMult);
                                                                e.invulnerableTime = 0;
                                                        }
                                                }
                                                this.level.explode(this, this.blockPosition().getX(), this.blockPosition().getY() + (double)(this.getBbHeight() / 16.0F), this.blockPosition().getZ(), radius, false, Explosion.BlockInteraction.NONE );
                                                remove(RemovalReason.KILLED);

                                        }
                                }
                        }

                        if (brtResult != null) {
                                if (this.getOwner() instanceof Player) {
                                        float dmg = this.getOwner() instanceof Player ? DamageCalculation.getMagicDamage((Player) this.getOwner()) * 0.2F : 2;
                                        List<LivingEntity> targetList = Utils.getLivingEntitiesInRadiusExcludingParty((Player) this.getOwner(), this, radius, radius, radius);
                                        System.out.println(targetList);
                                        for (LivingEntity e : targetList) {
                                                e.hurt(DarknessDamageSource.getDarknessDamage(this, this.getOwner()), dmg * dmgMult);
                                                e.invulnerableTime = 0;
                                        }
                                }
                                this.level.explode(this, this.blockPosition().getX(), this.blockPosition().getY() + (double) (this.getBbHeight() / 16.0F), this.blockPosition().getZ(), radius, false, Explosion.BlockInteraction.NONE);

                                remove(RemovalReason.KILLED);
                        }
                }

        }

        public int getMaxTicks() {
                return maxTicks;
        }

        public void setMaxTicks(int maxTicks) {
                this.maxTicks = maxTicks;
        }



        @Override
        protected void defineSynchedData() {
                // TODO Auto-generated method stub
        }
}

