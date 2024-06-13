package online.remind.remind.magic;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import online.kingdomkeys.kingdomkeys.capability.IWorldCapabilities;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.config.ModConfigs;
import online.kingdomkeys.kingdomkeys.lib.Party;
import online.kingdomkeys.kingdomkeys.magic.Magic;
import online.kingdomkeys.kingdomkeys.util.Utils;
import online.remind.remind.capabilities.IGlobalCapabilitiesRM;
import online.remind.remind.capabilities.ModCapabilitiesRM;
import online.remind.remind.client.sound.ModSoundsRM;

import java.util.List;

public class magicHaste extends Magic {

	public magicHaste(ResourceLocation registryName, boolean hasToSelect, int maxLevel) {
		super(registryName, hasToSelect, maxLevel, null);
	}

	@Override
	public void magicUse(Player player, Player caster, int level, float fullMPBlastMult, LivingEntity lockOnTarget) {
		IGlobalCapabilitiesRM globalData = ModCapabilitiesRM.getGlobal(player);
		IWorldCapabilities worldData = ModCapabilities.getWorld(player.level());
		if (globalData != null) {
			int time = (int) (ModCapabilities.getPlayer(caster).getMaxMP() * ((level * 0.75) + 5) + 5);
			caster.swing(InteractionHand.MAIN_HAND);
			// Effect and Level Modifier

			if (globalData.getHasteTicks() <= 0) {
				switch (level) {
				case 0:
					player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(new AttributeModifier("Haste", 0.25 + (0.25 * level), AttributeModifier.Operation.MULTIPLY_BASE));
					player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(new AttributeModifier("Haste", 0.25 + (0.25 * level), AttributeModifier.Operation.MULTIPLY_BASE));

					break;

				case 1:
					player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(new AttributeModifier("Haste", 0.25 + (0.25 * level), AttributeModifier.Operation.MULTIPLY_BASE));
					player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(new AttributeModifier("Haste", 0.25 + (0.25 * level), AttributeModifier.Operation.MULTIPLY_BASE));

					break;
				case 2:
					player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(new AttributeModifier("Haste", 0.25 + (0.25 * level), AttributeModifier.Operation.MULTIPLY_BASE));
					player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(new AttributeModifier("Haste", 0.25 + (0.25 * level), AttributeModifier.Operation.MULTIPLY_BASE));

					if (worldData.getPartyFromMember(player.getUUID()) != null) {
						Party party = worldData.getPartyFromMember(player.getUUID());
						List<Party.Member> list = party.getMembers();
						if (!list.isEmpty()) { // Haste everyone in the party within reach
							for (int i = 0; i < list.size(); i++) {
								if (player.level().getPlayerByUUID(list.get(i).getUUID()) != null && player.distanceTo(player.level().getPlayerByUUID(list.get(i).getUUID())) < ModConfigs.partyRangeLimit) {
									LivingEntity e = player.level().getPlayerByUUID(list.get(i).getUUID());
									if (e != null && Utils.isEntityInParty(party, e) && e != player) {
										IGlobalCapabilitiesRM globalData2 = ModCapabilitiesRM.getGlobal(e);
										e.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(new AttributeModifier("Haste", 0.25 + (0.25 * level), AttributeModifier.Operation.MULTIPLY_BASE));
										e.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(new AttributeModifier("Haste", 0.25 + (0.25 * level), AttributeModifier.Operation.MULTIPLY_BASE));
										globalData2.setHasteTicks(time, level);
									}
								}
							}
						}
					}
					break;
				}
				globalData.setHasteTicks(time, level);
			}
		}
	}

	@Override
	protected void playMagicCastSound(Player player, Player caster, int level) {
		player.level().playSound(null, player.blockPosition(), ModSoundsRM.HASTE.get(), SoundSource.PLAYERS, 1F, 1F);
	}

}