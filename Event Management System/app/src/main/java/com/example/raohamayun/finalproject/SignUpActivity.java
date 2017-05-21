package com.example.raohamayun.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {


    DataAccessLayer DAL;
    EditText txtPassword;
    EditText txtUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtUserName = (EditText)findViewById(R.id.txtUser);
        txtPassword = (EditText)findViewById(R.id.txtPasswordSignUP);

        Button btnCreate = (Button)findViewById(R.id.btnCreateNewUser);
        DAL = new DataAccessLayer(this.getApplicationContext());
        btnCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        DAL.OpenConnection();
        UserModel userModel = new UserModel();


        if (TextUtils.isEmpty(txtUserName.getText()))
        {
            txtUserName.setError("Enter User Name");
            //Toast.makeText(this, "User Name and Password Should not be Null", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(txtPassword.getText()))
        {
            txtPassword.setError("Enter Password");
        }
        else
        {
            userModel.setUserName(txtUserName.getText().toString());
            userModel.setPassword(txtPassword.getText().toString());

            long Result = DAL.CreateUser(userModel);
            if(Result > 0)
            {
                Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"User Already Exist", Toast.LENGTH_SHORT).show();

            }

        }
    }
}
