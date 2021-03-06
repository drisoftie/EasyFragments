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

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.drisoftie.frags.handler.IResultHandler;

/**
 * A special {@link DialogFragment} that supports callback mechanisms to get a result.
 *
 * @author Alexander Dridiger
 */
@SuppressLint("NewApi")
public abstract class BaseDiagResult<ResultT> extends DialogFragment implements IDialogChoice<ResultT> {

    private IResultHandler<ResultT> resultListener;

    private DialogResultState state = DialogResultState.IN_PROGRESS;

    @Override
    public void setBundle(Bundle bundle) {
        setArguments(bundle);
    }

    @Override
    public IResultHandler<ResultT> getResultListener() {
        return resultListener;
    }

    @Override
    public void setResultHandler(IResultHandler<ResultT> resultListener) {
        this.resultListener = resultListener;
    }

    @Override
    public DialogResultState getResultState() {
        return state;
    }

    @Override
    public void setResultState(DialogResultState state) {
        this.state = state;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(getArguments().getInt(getString(R.string.bundl_diag_layout)), null);
        createDialogComponents(v);
        builder.setView(v)
                .setTitle(getArguments().getInt(getString(R.string.bundl_diag_title)))
                .setPositiveButton(getArguments().getInt(getString(R.string.bundl_diag_btn_positive)),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        })
                .setNegativeButton(getArguments().getInt(getString(R.string.bundl_diag_btn_negative)),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
        AlertDialog diag = builder.create();
        diag.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return diag;
    }

    @Override
    public void onStart() {
        super.onStart();

        AlertDialog diag = (AlertDialog) getDialog();
        diag.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResultState(DialogResultState.POSITIVE);
                buttonPositivePressed();
            }
        });
        diag.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResultState(DialogResultState.NEGATIVE);
                buttonNegativePressed();
            }
        });
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (DialogResultState.IN_PROGRESS.equals(getResultState())) {
            setResultState(DialogResultState.CANCELED);
        }
        if (isVisible()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
        dismissDialog(dialog);
    }
}