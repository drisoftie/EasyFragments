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
package com.drisoftie.frags;

import android.annotation.SuppressLint;

/**
 * 
 * @author Alexander Dridiger
 */
public abstract class FragPageMenu extends FragManagedMenu implements IFragManagedMenu {

    @SuppressLint("NewApi")
	@Override
    public INotificationForwarder getNotificationForwarder() {
        return (FragManagedPaging) getParentFragment();
    }
}
