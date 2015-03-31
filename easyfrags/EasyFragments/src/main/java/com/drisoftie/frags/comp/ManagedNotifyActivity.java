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
package com.drisoftie.frags.comp;

import java.util.List;

import android.app.Activity;

import com.drisoftie.frags.INotificationForwarder;
import com.drisoftie.frags.NotifyState;

/**
 * A simple skeleton for a managed {@link Activity}.
 *
 * @author Alexander Dridiger
 */
public abstract class ManagedNotifyActivity extends ManagedActivity implements INotificationForwarder {

    @Override
    public void forwardChanges(NotifyState state, Object... args) {
        for (FragManaged f : getFragments()) {
            switch (state) {
                case ADD: {
                }
                case NOTIFY_ALL: {
                    f.onChangeOccurred(args);
                    break;
                }
                case REMOVE:
                    break;
            }
        }
    }

    /**
     * Get all Fragments to be notified.
     *
     * @return
     */
    public abstract List<FragManaged> getFragments();
}
