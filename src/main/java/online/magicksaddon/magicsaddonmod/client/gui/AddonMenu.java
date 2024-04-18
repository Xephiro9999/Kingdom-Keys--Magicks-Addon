package online.magicksaddon.magicsaddonmod.client.gui;

import net.minecraft.client.Minecraft;
import online.kingdomkeys.kingdomkeys.client.gui.elements.buttons.MenuButton;
import online.kingdomkeys.kingdomkeys.client.gui.menu.MenuScreen;
import online.magicksaddon.magicsaddonmod.lib.StringsRM;

public class AddonMenu extends MenuScreen {

    public AddonMenu(){
        super();
        minecraft = Minecraft.getInstance();
    }

    public enum RMButtons {
        PRESTIGE, DREAMEATER, CREDITS
    }

    MenuButton prestige, dreamEater, creditsScreen;

    protected void action(RMButtons buttonID){
        switch (buttonID){
            case PRESTIGE -> minecraft.setScreen(new PrestigeMenu());
            case DREAMEATER -> minecraft.setScreen(new DreamEaterMenu());
            case CREDITS -> minecraft.setScreen(new CreditsScreen());
        }
    }

    @Override
    public void init(){
        super.width = width;
        super.height = height;
        super.init();

        float topBarHeight = (float) height * 0.17F;
        int start = (int)(topBarHeight + 5);
        int pos = buttons.values().length - 1;

        float buttonPosX = (float) width * 0.03F;
        float buttonWidth = ((float) width * 0.1744F) - 22;

        addRenderableWidget(prestige = new MenuButton((int) buttonPosX, start + 18 * ++pos, (int) buttonWidth, (StringsRM.Gui_Menu_Button_Prestige), MenuButton.ButtonType.BUTTON, true, (e) -> {
            action(RMButtons.PRESTIGE);

        }));
        addRenderableWidget(dreamEater = new MenuButton((int) buttonPosX, start + 18 * ++pos, (int) buttonWidth, (StringsRM.Gui_Menu_Button_DreamEater), MenuButton.ButtonType.BUTTON, false, (e) -> {
            action(RMButtons.DREAMEATER);
        }));
        addRenderableWidget(creditsScreen = new MenuButton((int) buttonPosX, start + 18 * ++pos, (int) buttonWidth, (StringsRM.Gui_Menu_Button_Credits), MenuButton.ButtonType.BUTTON, false, (e) -> {
            action(RMButtons.CREDITS);
        }));
    }
}
