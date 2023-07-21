package io.curiositycore.thecuriositycore.messages.enums;

import io.curiositycore.thecuriositycore.messages.format.ComponentFactory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

/**
 * Enumeration of {@link net.kyori.adventure.text.Component Components} used for
 * the easy creation of messages requiring the CuriosityCore stamp
 */
public enum CoreComponents implements ComponentFactory<CoreComponents> {
    START_BRACKET("["),
    END_BRACKET("] "),
    SEPARATOR(" | "),
    DASHED_HEADER("-----"),
    DASH("-"),
    /**
     * The full <code>CuriosityCore</code> name
     */
    LOGO("CuriosityCore"),
    /**
     * A shorted form (<code>CC</code>) of the CuriosityCore name
     */
    SHORT_LOGO("CC");

    /**
     * The raw String contained within this enumeration value
     */
    final String text;

    /**
     * The default constructor for all <code>CCComponents</code> values
     * @param text Desired String to be formatted
     */
    CoreComponents(final String text){this.text = text;}

    @Override
    public Component getComponent() {
        return Component.text(this.text);
    }

    @Override
    public Component getWithColor(Integer hexColor) {
        return this.getComponent().color(TextColor.color(hexColor));
    }
}
