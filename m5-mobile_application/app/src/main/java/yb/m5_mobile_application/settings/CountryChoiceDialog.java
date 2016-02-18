package yb.m5_mobile_application.settings;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import yb.m5_mobile_application.R;

public class CountryChoiceDialog extends DialogFragment {

    private CountryChoiceDialogListener mListener;
    private int mCheckedItem;

    public CountryChoiceDialog() {
        mCheckedItem = 0;
    }

    public void setCheckedItem(int checkedItem) {
        mCheckedItem = checkedItem;
    }

    public int getItemChecked() {
        return mCheckedItem;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.country_choice_dialog_title)
                .setSingleChoiceItems(R.array.countries, mCheckedItem,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mCheckedItem = which;
                            }
                        })
                .setPositiveButton(R.string.country_choice_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mListener.onCountryChoiceDialogPositiveClick(CountryChoiceDialog.this);
                            }
                        })
                .setNegativeButton(R.string.country_choice_dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dismiss();
                            }
                        });
        return builder.create();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (CountryChoiceDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement CountryChoiceDialogListener");
        }
    }

    public interface CountryChoiceDialogListener {
        void onCountryChoiceDialogPositiveClick(DialogFragment dialog);
    }

}
