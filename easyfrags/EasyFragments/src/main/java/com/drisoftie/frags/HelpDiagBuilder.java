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

import android.os.Bundle;

/**
 * @author Alexander Dridiger
 */
public class HelpDiagBuilder<DiagT extends IDialog> {

    /**
     * Building chain starts registration with <b>optional</b> {@link DiagT}s to bind something to. <b>{@code null}</b> passing is
     * allowed for not targeting any views.
     *
     * @return further build options
     */
    public Wrapper with(DiagT diag) {
        Wrapper wrapper = new Wrapper(diag);
        return wrapper;
    }

    /**
     *
     */
    public class Wrapper {

        private DiagT target;
        private Bundle bundle;

        protected Wrapper(DiagT target) {
            this.target = target;
        }

        protected Bundle getBundle() {
            if (bundle == null) {
                bundle = new Bundle();
            }
            return bundle;
        }

        public Wrapper putInt(String name, int value) {
            getBundle().putInt(name, value);
            return this;
        }

        public Wrapper putBool(String name, boolean value) {
            getBundle().putBoolean(name, value);
            return this;
        }

        public Wrapper putString(String name, String value) {
            getBundle().putString(name, value);
            return this;
        }

        public Wrapper setBundle() {
            target.setBundle(bundle);
            return this;
        }

        public DiagT getDialog() {
            return target;
        }
    }
}
