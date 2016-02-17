package yb.m5_mobile_application.settings;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import yb.m5_mobile_application.R;
import yb.m5_mobile_application.utils.MyApp;

public class ClearDataDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final Context context = getActivity();
        builder.setMessage(R.string.clear_data_info_dialog_title)
                .setPositiveButton(R.string.clear_data_info_dialog_clear,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MyApp.getInstance().getSP().setDefaultSharedPreferences();
                                Toast.makeText(context, getString(R.string.clear_data_done), Toast.LENGTH_LONG).show();
                            }
                        })
                .setNegativeButton(R.string.clear_data_info_dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dismiss();
                            }
                        });
        return builder.create();
    }
}
