package config;

import org.dom4j.Element;

/**
 * Created by StReaM on 1/30/2016.
 */
public class ButtonConfig {
    private final int buttonWidth;

    private final int buttonHeight;

    private final int startX;
    private final int startY;
    private final int settingX;
    private final int settingY;

    public ButtonConfig(Element button) {
        this.buttonWidth = Integer.parseInt(button.attributeValue("w"));
        this.buttonHeight = Integer.parseInt(button.attributeValue("h"));
        this.startX = Integer.parseInt(button.element("start").attributeValue("x"));
        this.startY = Integer.parseInt(button.element("start").attributeValue("y"));
        this.settingX = Integer.parseInt(button.element("setting").attributeValue("x"));
        this.settingY = Integer.parseInt(button.element("setting").attributeValue("y"));
    }

    public int getButtonWidth() {
        return buttonWidth;
    }

    public int getSettingY() {
        return settingY;
    }

    public int getSettingX() {
        return settingX;
    }

    public int getStartY() {
        return startY;
    }

    public int getStartX() {
        return startX;
    }

    public int getButtonHeight() {
        return buttonHeight;
    }
}
