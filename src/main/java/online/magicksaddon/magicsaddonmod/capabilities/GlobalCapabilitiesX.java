package online.magicksaddon.magicsaddonmod.capabilities;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import online.kingdomkeys.kingdomkeys.capability.IGlobalCapabilities;
import online.kingdomkeys.kingdomkeys.capability.IPlayerCapabilities;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.capability.PlayerCapabilities;
import online.magicksaddon.magicsaddonmod.lib.StringsX;


public class GlobalCapabilitiesX implements IGlobalCapabilitiesX {

	@Override
    public CompoundTag serializeNBT() {
        CompoundTag storage = new CompoundTag();
        storage.putInt("haste_ticks", this.getHasteTicks());
        storage.putInt("haste_level", this.getHasteLevel());
        storage.putInt("slow_ticks", this.getSlowTicks());
        storage.putInt("slow_level", this.getSlowLevel());
        storage.putInt("berserk_ticks",this.getBerserkTicks());
        storage.putInt("berserk_level", this.getBerserkLevel());
        storage.putInt("autolife_active", this.getAutoLifeActive());

        storage.putInt("dark_mode_exp", this.getDarkModeEXP());
        storage.putInt("light_form_exp", this.getLightFormEXP());
        storage.putInt("rage_form_exp", this.getRageFormEXP());


        // New Game Plus NBT
        storage.putInt("prestige_level", this.getPrestigeLvl());

        storage.putInt("NGPlus_STR_Bonus", this.getSTRBonus());
        storage.putInt("NGPlus_MAG_Bonus", this.getMAGBonus());
        storage.putInt("NGPlus_DEF_Bonus", this.getDEFBonus());

        storage.putInt("NGPlus_Warrior", this.getNGPWarriorCount());
        storage.putInt("NGPlus_Mystic", this.getNGPMysticCount());
        storage.putInt("NGPlus_Guardian", this.getNGPGuardianCount());


        return storage;
    }

	@Override
    public void deserializeNBT(CompoundTag nbt) {
        CompoundTag properties = (CompoundTag) nbt;
        this.setHasteTicks(properties.getInt("haste_ticks"), properties.getInt("haste_level"));
        this.setSlowTicks(properties.getInt("slow_ticks"), properties.getInt("slow_level"));
        this.setBerserkTicks(properties.getInt("berserk_ticks"), properties.getInt("berserk_level"));

        this.setDarkModeEXP(properties.getInt("dark_mode_exp"));
        this.setLightFormEXP(properties.getInt("light_form_exp"));
        this.setDarkModeLvl(properties.getInt("dark_mode_lvl"));
        this.setLightFormLvl(properties.getInt("light_form_lvl"));

        this.setPrestigeLvl(properties.getInt("prestige_level"));

        this.setSTRBonus(properties.getInt("NGPlus_STR_Bonus"));
        this.setMAGBonus(properties.getInt("NGPlus_MAG_Bonus"));
        this.setDEFBonus(properties.getInt("NGPlus_DEF_Bonus"));

        this.setNGPWarriorCount(properties.getInt("NGPlus_Warrior"));
        this.setNGPMysticCount(properties.getInt("NGPlus_Mystic"));
        this.setNGPGuardianCount(properties.getInt("NGPlus_Guardian"));
    }

    private int hasteTicks;
    private int hasteLevel;
    private int slowTicks;
    private int slowLevel;
    private int berserkLevel;
    private int berserkTicks;
    private int isAutoLifeActive;
    private int prestigeLvl;
    private int strBonus;
    private int magBonus;
    private int defBonus;
    private int NGPlusWarriorCount;
    private int NGPlusMysticCount;
    private int NGPlusGuardianCount;

    private int darkModeEXP;
    private int lightFormEXP;
    private int rageFormEXP;
    private int darkModeLvl;
    private int lightFormLvl;

    private int stepTicks;
    private byte stepType;

    //Haste
    public int getHasteLevel() {

        return hasteLevel;
    }
    @Override
    public void setHasteLevel(int level) {

        this.hasteLevel = level;
    }
    @Override
    public int getHasteTicks() {

        return hasteTicks;
    }
    @Override
    public void setHasteTicks(int i, int level) {
            hasteTicks = i;
            hasteLevel = level;
    }
    @Override
    public void remHasteTicks(int ticks) {

        hasteTicks -= ticks;
    }


    //Slow
    public int getSlowLevel() {

        return slowLevel;
    }

    @Override
    public void setSlowLevel(int level) {
        this.slowLevel = level;
    }

    @Override
    public int getSlowTicks() {

        return slowTicks;
    }
    @Override
    public void setSlowTicks(int i, int level) {
        slowTicks = i;
        slowLevel = level;
    }
    @Override
    public void remSlowTicks(int ticks) {

        slowTicks -= ticks;
    }

    @Override
    public void setSlowCaster(String name) {

    }
    // Berserk
    @Override
    public int getBerserkLevel() {

        return berserkLevel;
    }

    @Override
    public void setBerserkLevel(int level) {
        this.berserkLevel = level;
    }

    @Override
    public int getBerserkTicks() {

        return berserkTicks;
    }

    @Override
    public void setBerserkTicks(int i, int level) {
        berserkTicks = i;
        berserkLevel = level;

    }

    @Override
    public void remBerserkTicks(int ticks) {

        berserkTicks -= ticks;
    }

    // Auto-Life
    @Override
    public int setAutoLifeActive(int autoLifeActive) {
        isAutoLifeActive = autoLifeActive;
        return autoLifeActive;
    }

    //Forms

    @Override
    public int getDarkModeEXP(){
        return darkModeEXP;
    }
    @Override
    public int getLightFormEXP(){
        return lightFormEXP;
    }
    @Override
    public int getRageFormEXP(){
        return rageFormEXP;
    }

    @Override
    public void setDarkModeEXP(int i){
        darkModeEXP = i;
    }

    @Override
    public void setLightFormEXP(int i){
        lightFormEXP = i;

    }
    @Override
    public void setDarkModeLvl(int i){
        darkModeLvl = i;
    }

    @Override
    public void setLightFormLvl(int i){
        lightFormLvl = i;

    }

    @Override
    public void setStepTicks(int i, byte type) {
        stepTicks = i;
        stepType = type;
    }

    @Override
    public void remStepTicks(int ticks) {
        stepTicks -= ticks;
    }

    @Override
    public int getStepTicks(){
        return stepTicks;
    }
    
    @Override
    public byte getStepType(){
        return stepType;
    }

    @Override
    public int getDarkModeLvl(){
        return darkModeLvl;
    }
    @Override
    public int getLightFormLvl(){
        return lightFormLvl;
    }



    @Override
    public int getAutoLifeActive() {
        return isAutoLifeActive;
    }

    @Override
    public void remAutoLifeActive(int use) {
        isAutoLifeActive -= use;
    }

    @Override
    public int getPrestigeLvl() {
        return prestigeLvl;
    }

    @Override
    public void addPrestigeLvl(int i) {
        prestigeLvl += i;
    }

    @Override
    public void setPrestigeLvl(int i) {
        prestigeLvl = i;
    }

    @Override
    public int getSTRBonus() {
        return strBonus;
    }

    @Override
    public int getMAGBonus() {
        return magBonus;
    }

    @Override
    public int getDEFBonus() {
        return defBonus;
    }

    @Override
    public void setSTRBonus(int i) {
        strBonus = i;
    }

    @Override
    public void setMAGBonus(int i) {
        magBonus = i;
    }

    @Override
    public void setDEFBonus(int i) {
        defBonus = i;
    }

    @Override
    public void addSTRBonus(int i) {
        strBonus += i;
    }

    @Override
    public void addMAGBonus(int i) {
        magBonus += i;
    }

    @Override
    public void addDEFBonus(int i) {
        defBonus += i;
    }

    @Override
    public int getNGPWarriorCount() {
        return NGPlusWarriorCount;
    }

    @Override
    public int getNGPMysticCount() {
        return NGPlusMysticCount;
    }

    @Override
    public int getNGPGuardianCount() {
        return NGPlusGuardianCount;
    }

    @Override
    public void setNGPWarriorCount(int i) {
        NGPlusWarriorCount = i;
    }

    @Override
    public void setNGPMysticCount(int i) {
        NGPlusMysticCount = i;
    }

    @Override
    public void setNGPGuardianCount(int i) {
        NGPlusGuardianCount = i;
    }

    @Override
    public void addNGPWarriorCount(int i){
        NGPlusWarriorCount += i;
    }
    @Override
    public void addNGPMysticCount(int i){
        NGPlusMysticCount += i;
    }
    @Override
        public void addNGPGuardianCount(int i){
            NGPlusGuardianCount += i;
        }



}
