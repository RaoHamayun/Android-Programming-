package com.example.raohamayun.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class NextToLogin extends AppCompatActivity {

    List<EventModel> Events = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_to_login);

        DataAccessLayer DAL = new DataAccessLayer(this.getApplicationContext());
        DAL.OpenConnection();

        Events = DAL.GetEvents();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.RecilierView);
        RecyclerView.LayoutManager _layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(_layout);



        UserAdapter adapter = new UserAdapter(Events,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
