package com.example.raohamayun.finalproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddNewEventAdmin extends AppCompatActivity {

    EditText EventName,EventVenu,EventDate,Price;
    DatePickerDialog datePicker;
    private SimpleDateFormat dateFormatter;
    String Date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_event_admin);

        Button AddEvent = (Button)findViewById(R.id.btnCreateEvent);

        EventName = (EditText)findViewById(R.id.txtEventName);
        EventVenu = (EditText)findViewById(R.id.txtEventVenu);
        EventDate = (EditText)findViewById(R.id.txtDate);
        dateFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        EventDate.setInputType(InputType.TYPE_NULL);
        Calendar newCalendar = Calendar.getInstance();
        datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                EventDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        EventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == EventDate)
                    datePicker.show();
            }
        });
        AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataAccessLayer DAL = new DataAccessLayer(AddNewEventAdmin.this);
                try {
                        DAL.OpenConnection();

                        final EventModel eventModel = new EventModel();

                        eventModel.setEventName(EventName.getText().toString());
                        eventModel.setEventVenue(EventVenu.getText().toString());
                        eventModel.setEventDate(EventDate.getText().toString());

                        long Resutl = DAL.CreateEvent(eventModel);

                        if (Resutl >= 0) {
                            AddNewEventAdmin.this.finish();
                            Intent intent = new Intent(AddNewEventAdmin.this, Articts.class);
                            intent.putExtra("ID", Long.toString(Resutl));
                            startActivity(intent);
                            DAL.CloseConnection();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"No Event Created Try Again please ....",Toast.LENGTH_SHORT).show();
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
