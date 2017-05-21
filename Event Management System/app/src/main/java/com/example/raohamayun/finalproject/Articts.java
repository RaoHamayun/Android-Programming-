package com.example.raohamayun.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Articts extends AppCompatActivity {

    EditText Add;
    Button btnAdd;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articts);

        Add = (EditText)findViewById(R.id.AddTxt);
        data = getIntent().getExtras().getString("ID");
        btnAdd = (Button)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Add1 = Add.getText().toString();

                DataAccessLayer DAL = new DataAccessLayer(getApplicationContext());
                DAL.OpenConnection();
                ArtistsModel artistsModel = new ArtistsModel();
                artistsModel.setEventID(Integer.parseInt(data));
                artistsModel.setArtistsName(Add1);
                try
                {
                    long Result = DAL.AddArtist(artistsModel);
                    if(Result >= 0)
                    {
                        Toast.makeText(getApplicationContext(),"Record Inserted...",Toast.LENGTH_SHORT).show();
                        Add.setText("");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"No Record Inserted...",Toast.LENGTH_SHORT).show();
                    }
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
        });

    }
}
