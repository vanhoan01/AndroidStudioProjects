package com.nguyenvanhoan.bt_gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView grvHinhAnh;
    ArrayList<HinhAnh> arrayImage;
    HinhAnhAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        adapter = new HinhAnhAdapter(this, R.layout.grid_hinh_anh, arrayImage);

        grvHinhAnh.setAdapter(adapter);
        
        grvHinhAnh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arrayImage.get(i).getTen(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AnhXa() {
        grvHinhAnh = (GridView) findViewById(R.id.gridViewHinhAnh);
        arrayImage = new ArrayList<>();
        arrayImage.add(new HinhAnh("Hinh so 1", R.drawable.anime1));
        arrayImage.add(new HinhAnh("Hinh so 2", R.drawable.anime2));
        arrayImage.add(new HinhAnh("Hinh so 3", R.drawable.anime3));
        arrayImage.add(new HinhAnh("Hinh so 4", R.drawable.anime4));
        arrayImage.add(new HinhAnh("Hinh so 5", R.drawable.anime5));
        arrayImage.add(new HinhAnh("Hinh so 6", R.drawable.anime6));
        arrayImage.add(new HinhAnh("Hinh so 7", R.drawable.anime7));
        arrayImage.add(new HinhAnh("Hinh so 8", R.drawable.anime8));
        arrayImage.add(new HinhAnh("Hinh so 9", R.drawable.anime9));
        arrayImage.add(new HinhAnh("Hinh so 10", R.drawable.anime10));

    }
}