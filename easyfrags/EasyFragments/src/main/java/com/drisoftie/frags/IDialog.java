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
