package dah.budgetapp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;

import Factotum.AdapterUpdater;
import dah.budgetapp.Dialogs.WaitDialog;

public class UiRefresh
{
    private Handler handler;
    private AdapterUpdater updater;
    private ArrayList list;
    private WaitDialog dialog;

    public UiRefresh(AdapterUpdater updater, WaitDialog dialog)
    {
        this.updater = updater;
        this.dialog = dialog;
        this.assignHandler();
    }

    public void setList(ArrayList list)
    {
        this.list = list;
    }

    public void sendHandlerMessage(int what)
    {
        this.handler.sendEmptyMessage(what);
    }

    private void assignHandler()
    {
        this.handler = new Handler(Looper.getMainLooper())
        {
            @Override
            public void handleMessage(Message msg)
            {
                if(msg.what == 0)
                {
                    refreshList();
                    dialog.dismissDialog();
                }
            }
        };
    }

    private void refreshList()
    {
        updater.refresh(this.list);
    }
}
