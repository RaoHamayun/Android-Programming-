package com.example.raohamayun.androidappgetdataapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btnGetApi);
        btn.setOnClickListener(this);
        btn2 = (Button)findViewById(R.id.btnNext);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        final TextView txtVeiw = (TextView)findViewById(R.id.txtview);

        RequestQueue requestQueue =  Volley.newRequestQueue(this);

        String url = "http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<ModelCountry> countres = new ArrayList<ModelCountry>();
                try {

                    JSONArray jArray = response.getJSONArray("geonames");
                    for(int i = 0 ; i< jArray.length() ; i++)
                    {
                        JSONObject Data = jArray.getJSONObject(i);
                        ModelCountry country = new ModelCountry();

                        country.setID(Data.getInt("geonameId"));
                        country.setCountryName(Data.getString("toponymName"));
                        country.setPopulation(Data.getLong("population"));

                        countres.add(country);
                    }
                    String Result = "";

                    for(int j = 0; j < countres.size(); j++ )
                    {
                        Result += countres.get(j).getCountryName()+" "+ countres.get(j).getPopulation() + "\n";
                        txtVeiw.setText(Result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}
