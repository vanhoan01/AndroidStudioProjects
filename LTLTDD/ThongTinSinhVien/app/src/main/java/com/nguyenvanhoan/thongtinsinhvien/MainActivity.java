package com.nguyenvanhoan.thongtinsinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvSinhVien;
    ArrayList<SinhVien> arraySinhVien;
    SinhVienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        adapter = new SinhVienAdapter(this, R.layout.dong_sinh_vien, arraySinhVien);
        lvSinhVien.setAdapter(adapter);
    }

    private void anhXa() {
        lvSinhVien = (ListView) findViewById(R.id.listViewSinhVien);
        arraySinhVien = new ArrayList<>();
        arraySinhVien.add(new SinhVien("Nguyễn Thanh Tùng", "1911505310218", "19T2", R.drawable.anime1));
        arraySinhVien.add(new SinhVien("Võ Văn Thanh", "1911505310218", "19T2", R.drawable.anime2));
        arraySinhVien.add(new SinhVien("Hồ Tấn Tài", "1911505310218", "19T2", R.drawable.anime3));
        arraySinhVien.add(new SinhVien("Hồ Việt Trung", "1911505310218", "19T2", R.drawable.anime4));
        arraySinhVien.add(new SinhVien("Võ Văn Cao", "1911505310218", "19T2", R.drawable.anime5));
    }
}