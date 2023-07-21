package io.curiositycore.thecuriositycore.messages.enums;

import io.curiositycore.thecuriositycore.messages.format.MultiComponentFactory;
import net.kyori.adventure.text.Component;

// TODO: On JavaDoc compilation, verify Adventure Component link functionality
/**
 * Enumeration for the formatting of {@link net.kyori.adventure.text.Component Components} based on
 * predetermined formats.
 */
public enum CoreMultiComponents implements MultiComponentFactory<CoreMultiComponents> {
    /**
     * A component that needs to be surrounded by square brackets <br>
     * (Ex. <code>"[CuriosityCore] "</code>)
     */
    BRACKETED_COMPONENT {
        @Override
        public Component getComponent(Component customTextComponent) {
            int logoColor = CoreColors.LOGO_SECONDARY.getColorValue();
            return CoreComponents.START_BRACKET.getWithColor(logoColor)
                    .append(customTextComponent)
                    .append(CoreComponents.END_BRACKET.getWithColor(logoColor));
        }
    },
    /**
     * A component that needs a separator line after it's text <br>
     * (Ex. <code>"CuriosityCore | "</code>)
     */
    SEPARATED_COMPONENT {
        @Override
        public Component getComponent(Component customTextComponent){
            return customTextComponent
                    .append(CoreComponents.SEPARATOR.getWithColor(
                            CoreColors.LOGO_SECONDARY.getColorValue()
                    ));
        }
    },
    /**
     * A component that needs to be formatted as a Plugin Tag <br>
     * (Ex. <code>"[CuriosityCore]  | "</code>)
     */
    PLUGIN_TAG {
        @Override
        public Component getComponent(Component customTextComponent) {
            return SEPARATED_COMPONENT.getComponent(
                    BRACKETED_COMPONENT.getComponent(
                            customTextComponent
                    )
            );
        }
    }
}
