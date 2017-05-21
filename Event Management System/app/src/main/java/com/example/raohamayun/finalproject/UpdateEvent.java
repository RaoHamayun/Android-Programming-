package com.example.raohamayun.finalproject;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateEvent extends AppCompatActivity {

    EditText Name,Venu,EDate;
    DatePickerDialog datePicker;
    private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);

        final String ID = getIntent().getExtras().getString("ID");

        Name = (EditText)findViewById(R.id.UpdateEventName);
        Venu = (EditText)findViewById(R.id.UpdateEventVenu);
        EDate = (EditText)findViewById(R.id.UpdateEventDate);
        dateFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        EDate.setInputType(InputType.TYPE_NULL);
        Calendar newCalendar = Calendar.getInstance();
        datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                EDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        EDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == EDate)
                    datePicker.show();
            }
        });

        final DataAccessLayer DAL = new DataAccessLayer(getApplicationContext());

        DAL.OpenConnection();

        try {
            EventModel eventModel = DAL.GetEventByID(ID);

            if (eventModel != null) {
                Name.setText(eventModel.getEventName());
                Venu.setText(eventModel.getEventVenue());
                EDate.setText(eventModel.getEventDate());
            } else {
                Toast.makeText(getApplicationContext(), "Record Not Found...", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        finally {
            DAL.CloseConnection();
        }
        Button Update = (Button)findViewById(R.id.btnUpdateEvent);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    DAL.OpenConnection();
                    EventModel eventModel = new EventModel();

                    eventModel.setEventName(Name.getText().toString());
                    eventModel.setEventVenue(Venu.getText().toString());
                    eventModel.setEventDate(EDate.getText().toString());

                    int Result = DAL.UpdateEvent(eventModel, ID);

                    Toast.makeText(getApplicationContext(), Result + "Row Effected", Toast.LENGTH_SHORT).show();
                    UpdateEvent.this.finish();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
                finally {

                }

            }
        });


    }
}
