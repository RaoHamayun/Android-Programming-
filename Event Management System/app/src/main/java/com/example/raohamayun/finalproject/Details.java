package com.example.raohamayun.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String data = getIntent().getExtras().getString("ID");
        DataAccessLayer DAL = new DataAccessLayer(getApplicationContext());

        try
        {
        DAL.OpenConnection();
        int ID = Integer.parseInt(data);
        List<ArtistsModel> artistsModel = DAL.GetListByID(ID);

        ArrayList<String> list = new ArrayList<String>();
        //ArtistsModel artistsModel = new ArtistsModel();
        for (ArtistsModel articts:artistsModel)
        {
            list.add(articts.getArtistsName());
        }
        ListView LVItems = (ListView)findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        LVItems.setAdapter(adapter);
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        finally {
            DAL.CloseConnection();
        }

    }


    }

