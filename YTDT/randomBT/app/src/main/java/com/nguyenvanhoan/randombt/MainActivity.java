package com.nguyenvanhoan.randombt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText edtSoMin, edtSoMax;
    Button button;
    TextView tvSoRandom;
    int min, max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSoMin = (EditText) findViewById(R.id.edtSoMin);
        edtSoMax = (EditText) findViewById(R.id.edtSoMax);
        button = (Button) findViewById(R.id.button);
        tvSoRandom = (TextView) findViewById(R.id.tvSoRandom);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    min = Integer.parseInt(edtSoMin.getText().toString());
                    max = Integer.parseInt(edtSoMax.getText().toString());
                }catch (Exception e){
                    min = 0;
                    max = 0;
                }
                Random random = new Random();
                int rd = random.nextInt(max - min + 1) + min;
                tvSoRandom.setText(String.valueOf(rd));
            }
        });
    }
}