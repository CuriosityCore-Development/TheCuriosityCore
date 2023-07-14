package io.curiositycore.thecuriositycore.messages.format;

import net.kyori.adventure.text.format.TextColor;

public enum ColorsBase {
    LOGO_PRIMARY(0x555599),
    LOGO_SECONDARY(0x7272F2);

    final int color;

    ColorsBase(int color){this.color = color;}

    TextColor getColorComponent(){
        return TextColor.color(this.color);
    }

    int getColorValue(){
        return this.color;
    }
}
