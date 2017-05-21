package com.example.raohamayun.finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
/**
 * Created by rao hamayun on 5/1/2017.
 */
public class UserAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<EventModel> eventModels;
    private Context context;

    public UserAdapter(List<EventModel> eventModelsList,Context mcontext)
    {
        eventModels = eventModelsList;
        this.context = mcontext;
    }
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item_list,parent,false);
        return new EventViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final EventViewHolder holder, final int position)
    {

        final EventModel eventModel = eventModels.get(position);

        holder.EventName.setText(eventModel.getEventName());
        holder.EventVenu.setText(eventModel.getEventVenue());
        holder.EventDate.setText(eventModel.getEventDate());
        final long ID = eventModel.getID();
        holder.Menu_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(context,holder.Menu_items);
                popupMenu.inflate(R.menu.usermenu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {
                            case R.id.userDetail:
                                Intent intent = new Intent(context,Details.class);
                                intent.putExtra("ID",Integer.toString((int) ID));
                                context.startActivity(intent);
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }
}
