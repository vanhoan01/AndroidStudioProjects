package com.nguyenvanhoan.baitapintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> arrayName;
    private ImageView imgGoc;
    private ImageView imgNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgGoc = (ImageView) findViewById(R.id.imageViewGoc);
        imgNhan = (ImageView) findViewById(R.id.imageViewNhan);

        String[] mangName = getResources().getStringArray(R.array.list_name);
        arrayName = new ArrayList<>(Arrays.asList(mangName));

        Collections.shuffle(arrayName);
        int idHinh = getResources().getIdentifier(arrayName.get(5), "drawable", getPackageName());
        imgGoc.setImageResource(idHinh);

        imgNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ImageActivity.class));
            }
        });
    }
}