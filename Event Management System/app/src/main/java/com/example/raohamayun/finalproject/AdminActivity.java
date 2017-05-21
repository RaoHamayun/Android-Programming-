package com.example.raohamayun.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {


    List<EventModel> Events = new ArrayList<>();

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        DataAccessLayer DAL = new DataAccessLayer(this.getApplicationContext());
            try {
                DAL.OpenConnection();

                Events = DAL.GetEvents();

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecilierView);
                RecyclerView.LayoutManager _layout = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(_layout);

                //registerForContextMenu(recyclerView);


                EventAdapter adapter = new EventAdapter(Events, this);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);


                Button AddNewEvent = (Button) findViewById(R.id.btnEvent);


                AddNewEvent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AdminActivity.this, AddNewEventAdmin.class);
                        startActivity(intent);
                    }
                });
            }
            catch (Exception ex)
            {
                Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
            finally
            {
                DAL.CloseConnection();
            }
    }

}
