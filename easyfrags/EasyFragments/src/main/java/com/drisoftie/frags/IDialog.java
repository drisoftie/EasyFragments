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


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.drisoftie.frags.handler.IResultHandler;

/**
 * Interface for a defined dialog with a result.
 *
 * @author Alexander Dridiger
 */
public interface IDialog<ResultT> {

    void setBundle(Bundle bundle);

    DialogResultState getResultState();

    void setResultState(DialogResultState state);

    IResultHandler<ResultT> getResultListener();

    void setResultHandler(IResultHandler<ResultT> resultListener);

    /**
     * @param dialogView the inflated layout
     */
    void createDialogComponents(View dialogView);

    /**
     * Logic for when the dialog is dismissed.
     *
     * @param dialog the dismissed dialog
     */
    void dismissDialog(DialogInterface dialog);
}
