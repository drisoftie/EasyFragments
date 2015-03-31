/**
 * 
 */
package com.drisoftie.frags;

/**
 * @author Alexander Dridiger
 *
 */
public interface INotificationForwarder {

    /**
     * Forwards changes to appropriate components.
     */
    public void forwardChanges(NotifyState state, Object... args);
}
