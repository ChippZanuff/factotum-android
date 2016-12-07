package dah.budgetapp.Dialogs;

import android.app.ProgressDialog;
import android.content.Context;

public class WaitDialog
{
    private final String dialogMsg = "Loading, please, wait...";
    private ProgressDialog dialog;

    public WaitDialog(Context context)
    {
        this.dialog = new ProgressDialog(context);
        this.assignParameters();
    }

    private void assignParameters()
    {
        this.dialog.setMessage(this.dialogMsg);
        this.dialog.setIndeterminate(true);
        this.dialog.show();
    }

    public void dismissDialog()
    {
        this.dialog.dismiss();
    }
}
