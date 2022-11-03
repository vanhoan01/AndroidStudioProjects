package com.nguyenvanhoan.bai3_songuyento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtSo;
    Button btKiemTra;
    TextView txtKetQua;
    int so;
    int kt = 1;
    String kq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        edtSo = (EditText) findViewById(R.id.edtSo);
        btKiemTra = (Button) findViewById(R.id.btKiemTra);
        txtKetQua = (TextView) findViewById(R.id.txtKetQua);

        btKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    so = Integer.parseInt(edtSo.getText().toString());
                }catch (Exception e){
                    kt = 0;
                }
                if(kt == 1){
                    if(isPrime(so))
                        kq = "Kết quả: " + so + " là số nguyên tố!";
                    else
                        kq = "Kết quả: " + so + " không là số nguyên tố!";
                }
                else
                    kq = "Nhập vào không hợp lệ!";
                txtKetQua.setText(kq);
                kt = 1;
            }
        });
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}