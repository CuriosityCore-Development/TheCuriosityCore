package io.curiositycore.thecuriositycore.messages.format;

import net.kyori.adventure.text.Component;

/**
 * Interface representing the functionality of Enumerations of
 * multiple {@link net.kyori.adventure.text.Component Components}
 * @param <E> The Enumeration this interface is implemented
 */
public interface MultiComponentFactory<E extends Enum<E>> {
    /**
     * Constructs a new Adventure <code>Component</code> based on given input
     * @param customTextComponent An Adventure <code>Component</code> to be formatted
     * @return A formatted <code>Component</code> based on this enumeration
     */
    Component getComponent(Component customTextComponent);
}
