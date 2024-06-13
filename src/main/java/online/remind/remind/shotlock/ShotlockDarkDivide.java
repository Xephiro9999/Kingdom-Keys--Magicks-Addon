package online.remind.remind.shotlock;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.lib.Strings;
import online.kingdomkeys.kingdomkeys.shotlock.Shotlock;
import online.remind.remind.entity.shotlock.DarkDivideCoreEntity;
import online.remind.remind.entity.shotlock.DarkFiragaCoreEntity;
import online.remind.remind.lib.StringsRM;

import java.util.List;

public class ShotlockDarkDivide extends Shotlock {

    public ShotlockDarkDivide(String registeryName, int order, int cooldown, int max){
        super(registeryName,order,cooldown,max);
    }

    @Override
    public void doPartialShotlock(Player player, List<Entity> targetList) {
        float damage = getDamage(player) + ((ModCapabilities.getPlayer(player).getNumberOfAbilitiesEquipped(StringsRM.darknessBoost) * 0.2F)  + (ModCapabilities.getPlayer(player).getNumberOfAbilitiesEquipped(Strings.fireBoost) * 0.2F));
        DarkFiragaCoreEntity core = new DarkFiragaCoreEntity(player.level(), player, targetList, getDamage(player));
        core.setPos(player.getX(), player.getY(), player.getZ());
        player.level().addFreshEntity(core);
    }

    @Override
    public void doFullShotlock(Player player, List<Entity> targetList) {
        float damage = getDamage(player) +  ((ModCapabilities.getPlayer(player).getNumberOfAbilitiesEquipped(StringsRM.darknessBoost) * 0.35F) + (ModCapabilities.getPlayer(player).getNumberOfAbilitiesEquipped(Strings.fireBoost) * 0.35F));
        DarkDivideCoreEntity core = new DarkDivideCoreEntity(player.level(), player, targetList, getDamage(player));
        core.setPos(player.getX(), player.getY(), player.getZ());
        player.level().addFreshEntity(core);
    }
}

