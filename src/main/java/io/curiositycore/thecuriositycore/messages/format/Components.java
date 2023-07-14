package io.curiositycore.thecuriositycore.messages.format;


import net.kyori.adventure.text.Component;

public interface Components<E extends Enum<E>>{
    Component getComponent();

    Component getWithColor(Integer hexColor);
}
