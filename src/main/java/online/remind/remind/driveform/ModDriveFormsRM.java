package online.remind.remind.driveform;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import online.kingdomkeys.kingdomkeys.KingdomKeys;
import online.kingdomkeys.kingdomkeys.driveform.DriveForm;
import online.remind.remind.KingdomKeysReMind;
import online.remind.remind.lib.StringsRM;

public class ModDriveFormsRM {
    public static DeferredRegister<DriveForm> DRIVE_FORMS = DeferredRegister.create(new ResourceLocation(KingdomKeys.MODID, "driveforms"), KingdomKeysReMind.MODID);

    static int order = 10;



     public static final RegistryObject<DriveForm>

        // Forms list

             LIGHT = DRIVE_FORMS.register(StringsRM.DFMA_Prefix + "light", () -> new DriveFormLight(KingdomKeysReMind.MODID + ":"+ StringsRM.DFMA_Prefix + "light", order++, new ResourceLocation(KingdomKeysReMind.MODID, "textures/models/armor/light.png"), false, true)),
             DARK = DRIVE_FORMS.register(StringsRM.DFMA_Prefix + "dark", () -> new DriveFormDark(KingdomKeysReMind.MODID + ":"+ StringsRM.DFMA_Prefix + "dark", order++, new ResourceLocation(KingdomKeysReMind.MODID, "textures/models/armor/dark.png"), false, true)),
             RAGE = DRIVE_FORMS.register(StringsRM.DFMA_Prefix + "rage", () -> new DriveFormRage(KingdomKeysReMind.MODID + ":"+ StringsRM.DFMA_Prefix + "rage", order++, new ResourceLocation(KingdomKeysReMind.MODID, "textures/models/armor/rage.png"), false, false)),
             TWILIGHT = DRIVE_FORMS.register(StringsRM.DFMA_Prefix + "twilight", () -> new DriveFormTwilight(KingdomKeysReMind.MODID + ":"+ StringsRM.DFMA_Prefix + "twilight", order++, new ResourceLocation(KingdomKeysReMind.MODID, "textures/models/armor/twilight.png"), true, true));
}
