/*
 * Copyright [2015] [Alexander Dridiger - drisoftie@gmail.com]
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */
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
