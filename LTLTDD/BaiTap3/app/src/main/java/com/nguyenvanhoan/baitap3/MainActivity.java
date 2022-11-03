package com.nguyenvanhoan.baitap3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imvMessenger;
    private EditText edtNhap;
    private Button btGui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imvMessenger = (ImageView) findViewById(R.id.imageViewMessenger);
        edtNhap = (EditText) findViewById(R.id.editTextNhap);
        btGui = (Button) findViewById(R.id.buttonGui);

        imvMessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SENDTO);
                    intent.putExtra("sms_body", "Hello");
                    intent.setData(Uri.parse("sms:0967124589"));
                    startActivity(intent);
                }catch (Exception e){

                }
            }
        });

        btGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SENDTO);
                    intent.putExtra("sms_body", "Hello");
                    intent.setData(Uri.parse("sms:0967124589"));
                    startActivity(intent);
                }catch (Exception e){

                }
            }
        });
    }
}