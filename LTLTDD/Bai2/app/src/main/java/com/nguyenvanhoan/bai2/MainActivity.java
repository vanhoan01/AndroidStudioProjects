package com.nguyenvanhoan.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int a, b;
    private float kq;
    EditText soA;
    EditText soB;
    TextView ketQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soA = (EditText) findViewById(R.id.editTextSoA);
        soB = (EditText) findViewById(R.id.editTextSoB);
        ketQua = (TextView) findViewById(R.id.textViewKetQua);
    }

    public void layHaiSo(){
        try {
            a = Integer.parseInt(soA.getText().toString());
            b = Integer.parseInt(soB.getText().toString());
        }catch (Exception e){
            a = 0;
            b = 0;
        }
    }
    public void phepCong(View view) {
        layHaiSo();
        kq = a + b;
        ketQua.setText(String.valueOf(kq));
    }
    public void phepTru(View view) {
        layHaiSo();
        kq = a - b;
        ketQua.setText(String.valueOf(kq));
    }
    public void phepNhan(View view) {
        layHaiSo();
        kq = a * b;
        ketQua.setText(String.valueOf(kq));
    }
    public void phepChia(View view) {
        layHaiSo();
        kq = (float)a/b;
        ketQua.setText(String.valueOf(kq));
    }
}