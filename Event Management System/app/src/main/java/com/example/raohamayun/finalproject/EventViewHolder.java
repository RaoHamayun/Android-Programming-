package com.example.raohamayun.finalproject;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * Created by rao hamayun on 5/1/2017.
 */
public class EventViewHolder extends RecyclerView.ViewHolder {

    TextView EventName,EventVenu,EventDate,Menu_items;

    public EventViewHolder(View itemView) {
        super(itemView);
        EventName = (TextView)itemView.findViewById(R.id.tvEventName);
        EventVenu = (TextView)itemView.findViewById(R.id.tvEventLocatio);
        EventDate = (TextView)itemView.findViewById(R.id.tvDate);
        Menu_items = (TextView) itemView.findViewById(R.id.txtOptionDigit);
    }

}
