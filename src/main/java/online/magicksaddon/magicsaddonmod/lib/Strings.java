package online.magicksaddon.magicsaddonmod.lib;

import online.magicksaddon.magicsaddonmod.MagicksAddonMod;

public class Strings {


    public static final String
            // Prefixes
        ABMA_Prefix = "ability_",
        DFMA_Prefix = "form_",
        KBMA_Prefix = "keyblade_",
        KCMA_Prefix = "keychain_",


        //Forms
        rageForm = Strings.DFMA_Prefix+"rage",
        darkMode = Strings.DFMA_Prefix+"dark",


        //Ability List
        darkPassage = MagicksAddonMod.MODID+":"+Strings.ABMA_Prefix+"dark_passage",
        darknessBoost = MagicksAddonMod.MODID+":"+Strings.ABMA_Prefix+"darkness_boost",
        lightBoost = MagicksAddonMod.MODID+":"+Strings.ABMA_Prefix+"light_boost",
        rageAwakened = MagicksAddonMod.MODID+":"+Strings.ABMA_Prefix+"rage_awakened",
        darkPower = MagicksAddonMod.MODID+":"+Strings.ABMA_Prefix+"dark_power",

        //Keyblades
        xephiroKeyblade = MagicksAddonMod.MODID+":"+Strings.KBMA_Prefix+"xephiro_keyblade",

        //Keychains
        xephiroKeybladeChain = MagicksAddonMod.MODID+":"+Strings.KCMA_Prefix+"xephiro_keyblade_chain";
}