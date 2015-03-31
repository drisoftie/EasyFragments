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
 * Indicates the type of result coming from a {@link IDialog}.
 *
 * @author Alexander Dridiger
 */
public enum DialogResultState {

    /**
     * A successful result.
     */
    POSITIVE,

    /**
     * A failed result.
     */
    NEGATIVE,

    /**
     * Dialog canceled.
     */
    CANCELED,

    /**
     * Still in progress to return a result.
     */
    IN_PROGRESS;
}
