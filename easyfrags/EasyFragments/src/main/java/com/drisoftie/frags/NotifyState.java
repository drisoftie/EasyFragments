/*****************************************************************************
 *
 * Copyright 2012-2014 Sony Corporation
 *
 * The information contained here-in is the property of Sony corporation and
 * is not to be disclosed or used without the prior written permission of
 * Sony corporation. This copyright extends to all media in which this
 * information may be preserved including magnetic storage, computer
 * print-out or visual display.
 *
 * Contains proprietary information, copyright and database rights Sony.
 * Decompilation prohibited save as permitted by law. No using, disclosing,
 * reproducing, accessing or modifying without Sony prior written consent.
 *
 ****************************************************************************/
package com.drisoftie.frags;

/**
 * This enum indicates what information is passed in the context of a {@link FragManagedPaging} and between its
 * child {@link FragManaged}s. Also it's used to pass what operation needs to be done by
 * {@link FragManagedPaging#forwardChanges(NotifyState, Object...)} with its {@link FragManaged}s.
 *
 * @author Alexander Dridiger
 */
public enum NotifyState {

    /**
     * Notify all participating {@link FragManaged}s that something has changed and pass optional data to them.
     */
    NOTIFY_ALL,

    /**
     * Notify {@link FragManagedPaging} that a child {@link FragManaged} should be removed. Or notify
     * all participating {@link FragManaged} that data has been removed and that this change should be accounted for.
     */
    REMOVE,

    /**
     * Notify {@link FragManagedPaging} that a child {@link FragManaged} should be added. Or notify
     * all participating {@link FragManaged} that data has been added and that this change should be accounted for.
     */
    ADD,

    /**
     * Indicates that external input changed data and that this should be accounted for.
     */
    EXTERNAL;
}
