package online.magicksaddon.magicsaddonmod.magic;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import online.kingdomkeys.kingdomkeys.capability.IGlobalCapabilities;
import online.kingdomkeys.kingdomkeys.capability.IPlayerCapabilities;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.lib.Party;
import online.kingdomkeys.kingdomkeys.magic.Magic;
import online.kingdomkeys.kingdomkeys.network.PacketHandler;
import online.magicksaddon.magicsaddonmod.capabilities.IGlobalCapabilitiesRM;
import online.magicksaddon.magicsaddonmod.capabilities.ModCapabilitiesRM;
import online.magicksaddon.magicsaddonmod.client.sound.ModSoundsRM;
import online.magicksaddon.magicsaddonmod.network.PacketHandlerRM;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.List;
import java.util.Vector;

import static online.kingdomkeys.kingdomkeys.handler.InputHandler.getMouseOverExtended;
import static online.kingdomkeys.kingdomkeys.handler.InputHandler.lockOn;

public class magicDispel extends Magic {

	public magicDispel(ResourceLocation registryName, boolean hasToSelect, int maxLevel) {
		super(registryName, hasToSelect, maxLevel, null);
	}

	@Override
	public void magicUse(Player player, Player caster, int level, float fullMPBlastMult, LivingEntity lockOnEntity) {

		if (lockOnEntity != null) {
			IGlobalCapabilitiesRM globalData = ModCapabilitiesRM.getGlobal(lockOnEntity);
			IGlobalCapabilities globalData2 = ModCapabilities.getGlobal(lockOnEntity);

			// If target is locked and magic lock on ability is on
			for (MobEffectInstance e : lockOnEntity.getActiveEffects()) {
				if (e.getEffect().getCategory() == MobEffectCategory.BENEFICIAL) {
					lockOnEntity.removeEffect(e.getEffect());
				}
			}
			globalData2.setAeroTicks(1, level);
			if (globalData.getHasteTicks() > 1) {
				globalData.setHasteTicks(1, level);
			}
			globalData.setBerserkTicks(1, level);

			PacketHandlerRM.syncGlobalToAllAround(lockOnEntity, globalData);
			PacketHandler.syncToAllAround(lockOnEntity, globalData2);
		} else {
			// IDK do some area of effect or something like slow or haste
			float radius = 16;
			List<Entity> list = player.level().getEntities(player, player.getBoundingBox().inflate(radius));
			Party casterParty = ModCapabilities.getWorld(player.level()).getPartyFromMember(player.getUUID());

			if (casterParty != null && !casterParty.getFriendlyFire()) {
				for (Party.Member m : casterParty.getMembers()) {
					list.remove(player.level().getPlayerByUUID(m.getUUID()));
				}
			}

			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Entity e = list.get(i);
					if (e instanceof LivingEntity lEntity) {
						IGlobalCapabilitiesRM globalData = ModCapabilitiesRM.getGlobal(lEntity);
						IGlobalCapabilities globalData2 = ModCapabilities.getGlobal(lEntity);
						lEntity.removeEffect(MobEffects.DAMAGE_BOOST);
						lEntity.removeEffect(MobEffects.MOVEMENT_SPEED);
						lEntity.removeEffect(MobEffects.DAMAGE_RESISTANCE);
						lEntity.removeEffect(MobEffects.FIRE_RESISTANCE);

						globalData2.setAeroTicks(1, level);
						if (globalData.getHasteTicks() > 1) {
							globalData.setHasteTicks(1, level);
						}
						globalData.setBerserkTicks(1, level);
						globalData.setAutoLifeActive(0);

						PacketHandlerRM.syncGlobalToAllAround(lEntity, globalData);
						PacketHandler.syncToAllAround(lEntity, globalData2);
					}
				}
			}

		}
	}

	@Override
	protected void playMagicCastSound(Player player, Player caster, int level) {
		 player.level().playSound(null, player.blockPosition(), ModSoundsRM.DISPEL.get(),
		 SoundSource.PLAYERS, 1F, 1F);
	}
}