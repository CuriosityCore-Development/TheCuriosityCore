package io.curiositycore.thecuriositycore.messages.format;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public enum ComponentsBase implements Components<ComponentsBase> {
    START_BRACKET("["),
    END_BRACKET("] "),
    LOGO("CuriosityCore"),
    SHORT_LOGO("CC");

    final String text;
    ComponentsBase(final String text){this.text = text;}


    @Override
    public Component getComponent(){
        return Component.text(this.text);
    }

    @Override
    public Component getWithColor(Integer hexColor) {
        return this.getComponent().color(TextColor.color(hexColor));
    }
}
