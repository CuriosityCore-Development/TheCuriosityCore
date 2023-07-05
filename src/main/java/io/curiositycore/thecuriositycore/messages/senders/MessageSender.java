package io.curiositycore.thecuriositycore.messages.senders;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import java.util.UUID;

/**
 * Abstract class to define the generalisation of any messages to be sent within a Curiosity Core <code>Plugin</code>.
 * <br> Contains an <code>Audience</code> in order to streamline sending messages
 */
@Getter
public abstract class MessageSender {
    /**
     * A unique identifier for this <code>MessageSender</code>, generated on initialization.
     */
    UUID senderID;
    /**
     * The target audience of this <code>MessageSender</code>.
     */
    @Setter
    Audience audience;

    /**
     * The default constructor for all <code>MessageSender</code>s.
     * Generates a random <code>UUID</code> and sets a target <code>Audience</code>.
     * @param audience The desired <code>Audience</code> for this class to send messages to
     */
    protected MessageSender(Audience audience){
        this.audience = audience;
        this.senderID = UUID.randomUUID();
    }

    /**
     * Sends a message to predefined <code>Audience</code>
     * @param text An <code>Adventure Component</code> to be sent
     */
    protected void sendMessage(Component text){
        this.audience.sendMessage(text);
    }
}
