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

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.drisoftie.frags.DialogResultState;
import com.drisoftie.frags.IDialogChoice;
import com.drisoftie.frags.R;
import com.drisoftie.frags.handler.IResultHandler;

/**
 * A special {@link DialogFragment} that supports callback mechanisms to get a result.
 *
 * @author Alexander Dridiger
 */
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
    public Dialog onCreateDialog(@NonNull Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(getArguments().getInt(getString(R.string.bundl_diag_layout)), null);
        createDialogComponents(v);
        builder.setView(v);
        if (getArguments().containsKey(getString(R.string.bundl_diag_title))) {
            Object arg = getArguments().get(getString(R.string.bundl_diag_title));
            if (arg instanceof String) {
                builder.setTitle(getArguments().getString(getString(R.string.bundl_diag_title)));
            } else if (arg instanceof Integer) {
                builder.setTitle(getArguments().getInt(getString(R.string.bundl_diag_title)));
            }
        }
        if (getArguments().containsKey(getString(R.string.bundl_diag_btn_positive))) {
            builder.setPositiveButton(getArguments().getInt(getString(R.string.bundl_diag_btn_positive)),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
        }
        if (getArguments().containsKey(getString(R.string.bundl_diag_btn_positive))) {
            builder.setNegativeButton(getArguments().getInt(getString(R.string.bundl_diag_btn_negative)),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
        }
        AlertDialog diag = builder.create();
        diag.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return diag;
    }

    @Override
    public void onStart() {
        super.onStart();

        AlertDialog diag = (AlertDialog) getDialog();

        if (getArguments().containsKey(getString(R.string.bundl_diag_btn_positive))) {
            diag.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setResultState(DialogResultState.POSITIVE);
                    buttonPositivePressed();
                }
            });
        }
        if (getArguments().containsKey(getString(R.string.bundl_diag_btn_positive))) {
            diag.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setResultState(DialogResultState.NEGATIVE);
                    buttonNegativePressed();
                }
            });
        }
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