package com.example.raohamayun.androidappgetdataapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {


    String Name1,Detail1;
    EditText Name,Detail;
    Button btnPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Name = (EditText)findViewById(R.id.txtName);
        Detail = (EditText)findViewById(R.id.txtDetail);

        btnPost = (Button)findViewById(R.id.btnPost);

        btnPost.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String Url= "https://raohamayun.000webhostapp.com/register.php";


        Name1 = Name.getText().toString();
        Detail1 = Detail.getText().toString();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Name", Name1);
            jsonObject.put("Detail",Detail1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Main2Activity.this, "Done", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
