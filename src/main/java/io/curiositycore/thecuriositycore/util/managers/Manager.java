package io.curiositycore.thecuriositycore.util.managers;

/**
 * Interface representing the functionality of a Manager responsible for the registering and unregistering of
 * handled <code>Objects</code>.
 * @param <T> Type parameter representing the type of object the Manager handles.
 * @param <S> Type parameter representing the type of Object used to identify the handled Objects.
 */
public interface Manager<T,S> {
    void register(T objectToRegister);
    void unregister(S objectId);
}
