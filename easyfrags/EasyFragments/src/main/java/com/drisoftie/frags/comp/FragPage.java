/*****************************************************************************
 *
 * Copyright 2012-2013 Sony Corporation
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

import com.drisoftie.frags.IFragManaged;
import com.drisoftie.frags.INotificationForwarder;

/**
 * 
 * @author Alexander Dridiger
 */
public abstract class FragPage extends FragManaged implements IFragManaged {

    @Override
    public INotificationForwarder getNotificationForwarder() {
        return (FragManagedPaging) getParentFragment();
    }
}