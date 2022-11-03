package com.nguyenvanhoan.bai4_bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {
    EditText edtCanNang;
    EditText edtChieuCao;
    Button btDanhGia;
    TextView txtPhLoai;

    float khluong;
    float chcao;
    float bmi;
    int kt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        edtCanNang = (EditText) findViewById(R.id.edtCanNang);
        edtChieuCao = (EditText) findViewById(R.id.edtChieuCao);
        btDanhGia = (Button) findViewById(R.id.btDanhGia);
        txtPhLoai = (TextView) findViewById(R.id.txtPhanLoai);

        btDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPhLoai.setText(bmiTinh());
                kt = 1;
            }
        });
    }

    public String bmiTinh(){
        String phLoai;
        try {
            khluong = Float.parseFloat(edtCanNang.getText().toString());
            chcao = Float.parseFloat(edtChieuCao.getText().toString());
        }catch (Exception e){
            kt = 0;
        }
        if(kt == 1){
            bmi = (float)khluong/(chcao*chcao);
            if (bmi < 18)
                phLoai = "Bạn thuộc loại người gầy";
            else
            if(bmi < 25)
                phLoai = "Bạn thuộc loại người Bình Thường";
            else
            if(bmi < 30)
                phLoai = "Bạn thuộc loại người báo phì độ I";
            else
            if (bmi < 35)
                phLoai = "Bạn thuộc loại người béo phì độ II";
            else
                phLoai = "Bạn thuộc loại người béo phì độ III";
        }
        else
            phLoai = "Nhập vào không hợp lệ!";
        return phLoai;
    }
}