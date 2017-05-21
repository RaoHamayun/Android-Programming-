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
public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<EventModel> eventModels;
    private Context context;

    public EventAdapter(List<EventModel> eventModelsList,Context mcontext)
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
                popupMenu.inflate(R.menu.tinymenu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {
                            case R.id.mnu_item_update:
                                Intent intent1 = new Intent(context,UpdateEvent.class);
                                intent1.putExtra("ID",Integer.toString((int) ID));
                                context.startActivity(intent1);
                                //Toast.makeText(context,"update "+ position,Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.mnu_item_delete:
                                DataAccessLayer DAL = new DataAccessLayer(context);
                                DAL.OpenConnection();
                                int Result = DAL.DeleteItem(Integer.toString((int) ID),context);
                                eventModels.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context,"Delete "+ Result,Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.mnu_item_details:
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
