package online.remind.remind.client.gui.dreameaters;

import net.minecraft.client.Minecraft;
import online.kingdomkeys.kingdomkeys.client.gui.elements.MenuBackground;
import online.kingdomkeys.kingdomkeys.client.gui.elements.buttons.MenuButton;
import online.kingdomkeys.kingdomkeys.lib.Strings;
import online.remind.remind.client.gui.DreamEaterMenu;

import java.awt.*;

public class ChangeSpirit extends MenuBackground {


    public ChangeSpirit(String name, Color rgb) {
        super(name, rgb);
    }
    private MenuButton backButton;



    public ChangeSpirit() {
        super("Change Spirit", new Color(241, 115, 24));
        minecraft = Minecraft.getInstance();
    }

    protected void action(String string) {
        if (string.equals("back")) {
            minecraft.setScreen(new DreamEaterMenu());
        }
    }

    @Override
    public void init() {
        super.init();
        this.renderables.clear();

        float topBarHeight = (float) height * 0.17F;
        int button_statsY = (int) topBarHeight + 5;
        int button_stats_playerY = button_statsY;

        float buttonPosX = (float) width * 0.03F;
        float subButtonPosX = buttonPosX + 10;

        float buttonWidth = ((float) width * 0.1744F)- 20;
        float subButtonWidth = buttonWidth - 10;


        float dataWidth = ((float) width * 0.1744F)-10;

        int col1X = (int) (subButtonPosX + buttonWidth + 40), col2X=(int) (col1X + dataWidth * 2)+10 ;

        int i = 0;


        addRenderableWidget(backButton = new MenuButton((int) buttonPosX, button_statsY +60, (int) buttonWidth, (Strings.Gui_Menu_Back), MenuButton.ButtonType.BUTTON, false, (e) -> {
            action("back");
        }));
    }




}
