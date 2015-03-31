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
 * Interface for a defined [@link IDialog} with a choices.
 *
 * @author Alexander Dridiger
 */
public interface IDialogChoice<ResultT> extends IDialog<ResultT> {

    /**
     * Logic for when the {@link android.app.AlertDialog#BUTTON_POSITIVE} is pressed.
     */
    void buttonPositivePressed();

    /**
     * Logic for when the {@link android.app.AlertDialog#BUTTON_NEGATIVE} is pressed.
     */
    void buttonNegativePressed();
}
