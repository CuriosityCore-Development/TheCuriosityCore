package io.curiositycore.thecuriositycore.messages.enums;

import io.curiositycore.thecuriositycore.messages.format.ColorFactory;
import net.kyori.adventure.text.format.TextColor;

/**
 * Enumeration of {@link net.kyori.adventure.text.format.TextColor TextColors} used for
 * the easy creation of messages requiring the CuriosityCore stamp
 */
public enum CoreColors implements ColorFactory<CoreColors> {
    /**
     * Primary color of the CuriosityCore stamp (<code>0x555599</code>)
     */
    LOGO_PRIMARY(0x555599),
    /**
     * Secondary color of the CuriosityCore stamp (<code>0x7272F2</code>)
     */
    LOGO_SECONDARY(0x7272F2);

    /**
     * A hexadecimal representation of a color in this enumeration value
     */
    final int color;

    /**
     * The default constructor for all <code>CCColors</code> values
     * @param color Desired Integer to be formatted
     */
    CoreColors(int color){this.color = color;}

    @Override
    public TextColor getColorComp(){
        return TextColor.color(this.color);
    }

    @Override
    public int getColorValue(){
        return this.color;
    }
}
