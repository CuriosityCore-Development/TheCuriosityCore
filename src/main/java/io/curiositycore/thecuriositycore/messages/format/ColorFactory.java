package io.curiositycore.thecuriositycore.messages.format;

import net.kyori.adventure.text.format.TextColor;

/**
 * Interface representing the functionality of Enumerations of
 *  {@link net.kyori.adventure.text.format.TextColor TextColors}
 * @param <E> The Enumeration this interface is implemented
 */
public interface ColorFactory<E extends Enum<E>> {
    /**
     * Constructs a {@link net.kyori.adventure.text.format.TextColor TextColor} from
     * this enumeration's hexadecimal integer component.
     * @return A <code>TextColor</code> element using this enumeration
     */
    TextColor getColorComp();

    /**
     * Gets this enumeration's hexadecimal integer components.
     * @return A hexadecimal integer representing a color (Ex. <code>0xFFFFFF</code>)
     */
    int getColorValue();
}
