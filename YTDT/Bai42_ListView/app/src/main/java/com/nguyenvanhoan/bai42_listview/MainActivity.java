package com.nguyenvanhoan.bai42_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTraiCay;
    ArrayList<TraiCay> arrayTraiCay;
    TraiCayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        adapter = new TraiCayAdapter(this, R.layout.dong_trai_cay, arrayTraiCay);
        lvTraiCay.setAdapter(adapter);
    }
    public void anhXa(){
        lvTraiCay = (ListView) findViewById(R.id.listViewTraiCay);
        arrayTraiCay = new ArrayList<>();
        arrayTraiCay.add(new TraiCay("Dâu tây", "Dâu tây Đà Lạt", R.drawable.delete));
        arrayTraiCay.add(new TraiCay("Dừa sáp", "Đặc sản Trà Vinh", R.drawable.file));
        arrayTraiCay.add(new TraiCay("Măng cụt", "Măng cụt miền tây", R.drawable.forms));
        arrayTraiCay.add(new TraiCay("Thanh long", "Thanh long Bình Thuận", R.drawable.heart));
    }
}