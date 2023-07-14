package io.curiositycore.thecuriositycore.messages.managers;

import io.curiositycore.thecuriositycore.messages.senders.MessageSender;
import net.kyori.adventure.audience.Audience;
import java.util.HashMap;
import java.util.UUID;

/**
 * <strong>Deprecated to investigate alternate approach.</strong> <br>
 * Abstract class to define the generalization of managing <code>MessageSenders</code> within a Curiosity Core <code>Plugin</code>
 * <br> Allows for limited modification and easy management of individual <code>MessageSenders</code>
 */
@Deprecated
public abstract class SenderManager {
    /**
     * Hashmap of individual <code>MessageSenders</code> as well as their Unique Identifiers
     */
    protected HashMap<UUID, MessageSender> senders = new HashMap<>();

    /**
     * Default constructor for <code>SenderManager</code>s.
     * Initializes <code>MessageSender</code> HashMap
     */
    protected SenderManager(){

    }

    /**
     * Sets individual <code>MessageSender</code>'s <code>Audience</code>
     * @param id Desired <code>MessageSender</code>'s UUID
     * @param audience <code>Audience</code> to change target's audience to
     */
    public void setAudience(UUID id, Audience audience){
        this.senders.get(id).setAudience(audience);
    }

    /**
     * Add a new <code>MessageSender</code> to this <code>SenderManager</code>
     * @param sender A new <code>MessageSender</code>
     */
    public void addSender(MessageSender sender){
        this.senders.put(sender.getSenderID(), sender);
    }

    /**
     * Remove a <code>MessageSender</code> from this <code>SenderManager</code>
     * @param sender <code>MessageSender</code> to remove
     */
    public void removeSender(MessageSender sender){
        this.senders.remove(sender.getSenderID());
    }

    /**
     * Gets desired <code>MessageSender</code> based on it's unique identifier
     * @param id <code>UUID</code> of the desired <code>MessageSender</code>
     * @return desired <code>MessageSender</code>, or <code>null</code> if none are found
     */
    public MessageSender getSender(UUID id){
        return this.senders.get(id);
    }
}
