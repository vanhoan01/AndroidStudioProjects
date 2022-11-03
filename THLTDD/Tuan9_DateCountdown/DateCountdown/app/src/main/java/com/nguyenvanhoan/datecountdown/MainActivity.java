package com.nguyenvanhoan.datecountdown;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText edtTitle, edtEvent;
    private TextView txtDate;
    private Button btnSetDate, btnAddDate;
    private Calendar calendarOne, calendarTwo;
    private SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Date Countdown");

        AnhXa();

        calendarOne = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtDate.setText(simpleDateFormat.format(calendarOne.getTime()));

        calendarTwo = Calendar.getInstance();

        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ngay = calendarTwo.get(Calendar.DATE);
                int thang = calendarTwo.get(Calendar.MONTH);
                int nam = calendarTwo.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarTwo.set(i, i1, i2);
                        txtDate.setText(simpleDateFormat.format(calendarTwo.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        btnAddDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString();
                String event = edtEvent.getText().toString();
                int day = (int) ((calendarTwo.getTimeInMillis() - calendarOne.getTimeInMillis()) / (1000*60*60*24));
                String days;
                if(day >= 0){
                    days = Math.abs(day) + " days remaining";
                }else{
                    days = Math.abs(day) + " days ago";
                }

                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("event", event);
                bundle.putString("days", days);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        edtTitle = (EditText) findViewById(R.id.editTextTitle);
        edtEvent = (EditText) findViewById(R.id.editTextEvent);
        txtDate = (TextView) findViewById(R.id.textViewDate);
        btnSetDate = (Button) findViewById(R.id.buttonSetDate);
        btnAddDate = (Button) findViewById(R.id.buttonAddEvent);
    }
}