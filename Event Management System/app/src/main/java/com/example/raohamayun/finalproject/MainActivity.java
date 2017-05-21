package com.example.raohamayun.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtPassword;
    EditText txtUserName;
    DataAccessLayer DAL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DAL = new DataAccessLayer(this.getApplicationContext());
        DAL.OpenConnection();


        Button btnSignin = (Button) findViewById(R.id.btnSignin);
        Button btnSignUp = (Button) findViewById(R.id.btnSignUP);

        txtPassword = (EditText)findViewById(R.id.txtPasswordSignUP);
        txtUserName = (EditText)findViewById(R.id.txtUserName);

        btnSignin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnSignin:

                try {
                    DAL.OpenConnection();
                    UserModel userModel = new UserModel();

                    userModel.setUserName(txtUserName.getText().toString());
                    userModel.setPassword(txtPassword.getText().toString());

                    String AdminName = "Admin";
                    String AdminPassword = "Admin";

                    if (AdminName.equals(userModel.getUserName()) && AdminPassword.equals(userModel.getPassword())) {
                        Intent intent = new Intent(this, AdminActivity.class);
                        startActivity(intent);
                        txtPassword.setText("");
                        txtUserName.setText("");
                        DAL.CloseConnection();
                    } else {
                        int Result = DAL.GetUser(userModel);

                        if (Result == 1) {
                            Intent intent = new Intent(this.getApplicationContext(), NextToLogin.class);
                            startActivity(intent);
                            txtPassword.setText("");
                            txtUserName.setText("");
                            DAL.CloseConnection();
                        } else {
                            Toast.makeText(this, "Enter Valid User Name Or Password Please....", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(this,"Please Enter Valid User Name & Password", Toast.LENGTH_SHORT).show();
                }
                finally
                {
                    DAL.CloseConnection();
                }
                break;
            case R.id.btnSignUP:
                    Intent intent = new Intent(this,SignUpActivity.class);
                    startActivity(intent);
                break;
        }
    }
}
