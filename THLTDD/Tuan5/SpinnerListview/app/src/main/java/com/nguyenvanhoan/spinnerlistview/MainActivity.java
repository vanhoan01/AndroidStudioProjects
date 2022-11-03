package com.nguyenvanhoan.spinnerlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btSpinner;
    Button btListview;
    Button btCTListview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Spinner và ListView");

        btSpinner = (Button) findViewById(R.id.buttonSpinner);
        btListview = (Button) findViewById(R.id.buttonListview);
        btCTListview = (Button) findViewById(R.id.buttonCTListview);

        btSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
                startActivity(intent);
            }
        });

        btListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListviewActivity.class);
                startActivity(intent);
            }
        });

        btCTListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomListviewActivity.class);
                startActivity(intent);
            }
        });
    }
}