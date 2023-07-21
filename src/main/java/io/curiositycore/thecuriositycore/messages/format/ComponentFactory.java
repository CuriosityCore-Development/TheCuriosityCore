package io.curiositycore.thecuriositycore.messages.format;


import net.kyori.adventure.text.Component;

/**
 * Interface representing the functionality of Enumerations of
 * {@link net.kyori.adventure.text.Component Components}
 * @param <E> The Enumeration this interface is implemented
 */
public interface ComponentFactory<E extends Enum<E>>{

    /**
     * Constructs Adventure <code>Component</code> from enumeration's string component
     * @return An Adventure <code>Component</code>
     */
    Component getComponent();

    /**
     * Constructs Adventure <code>Component</code> as with <code>getComponent()</code>
     * with the addition of a provided color
     * @param hexColor A hexadecimal integer representing a color (Ex. <code>0xFFFFFF</code>)
     * @return A colored Adventure <code>Component</code>
     */
    Component getWithColor(Integer hexColor);
}
