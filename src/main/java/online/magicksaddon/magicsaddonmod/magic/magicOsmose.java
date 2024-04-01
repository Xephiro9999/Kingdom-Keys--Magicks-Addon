package online.magicksaddon.magicsaddonmod.magic;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.magic.Magic;
import online.magicksaddon.magicsaddonmod.entity.magic.OsmoseEntity;
import online.magicksaddon.magicsaddonmod.lib.StringsRM;

public class magicOsmose extends Magic {

    public magicOsmose(ResourceLocation registryName, boolean hasToSelect, int maxLevel) {
        super(registryName, hasToSelect, maxLevel, null);
    }

    @Override
    protected void magicUse(Player player, Player caster, int level, float fullMPBlastMult, LivingEntity lockOnEntity) {
        float mpTaken = getDamageMult(level);
        mpTaken *= fullMPBlastMult;

        lockOnEntity = getMagicLockOn(level) ? lockOnEntity : null;
        caster.swing(InteractionHand.MAIN_HAND);
        switch(level) {
            case 0:
                ThrowableProjectile osmose = new OsmoseEntity(player.level(), player, mpTaken,lockOnEntity);
                player.level().addFreshEntity(osmose);
                osmose.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 2F, 0);
                break;
            case 1:
                ThrowableProjectile osmosera = new OsmoseEntity(player.level(), player, mpTaken,lockOnEntity);
                player.level().addFreshEntity(osmosera);
                osmosera.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 2.5F, 0);
                break;
            case 2:
                ThrowableProjectile osmosega = new OsmoseEntity(player.level(), player, mpTaken,lockOnEntity);
                player.level().addFreshEntity(osmosega);
                osmosega.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 3F, 0);
                break;
        }


    }
}
