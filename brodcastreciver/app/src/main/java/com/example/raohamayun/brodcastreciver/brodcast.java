package com.example.raohamayun.brodcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by rao hamayun on 5/22/2017.
 */
public class brodcast extends BroadcastReceiver {

    public brodcast(){};
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Broad Cast Recive", Toast.LENGTH_SHORT).show();
    }
}
