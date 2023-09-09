package online.magicksaddon.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.leveling.Stat;

public interface IPlayerCapabilitiesMA  extends INBTSerializable<CompoundTag> {

    // Status Effect Spell Values
    int getHasteLevel();
    void setHasteLevel(int level);
    void setHasteTicks(int ticks);
    void remHasteTicks(int ticks);
    int getHasteTicks();
    void setHasteActive(boolean active);
    boolean getHasteActive();

}
