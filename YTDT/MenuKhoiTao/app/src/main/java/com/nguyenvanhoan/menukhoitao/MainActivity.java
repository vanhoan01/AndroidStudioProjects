package com.nguyenvanhoan.menukhoitao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnMenu;
    Button btnChonMau;
    ConstraintLayout manHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu = (Button) findViewById(R.id.buttonMenu);
        btnChonMau = (Button) findViewById(R.id.buttonChonMau);
        manHinh = (ConstraintLayout) findViewById(R.id.manHinh);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowMenu();
            }
        });

        //Đăng ký view cho context menu
        registerForContextMenu(btnChonMau);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Chọn màu");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_vang:
                manHinh.setBackgroundColor(Color.YELLOW);
                break;
            case R.id.menu_do:
                manHinh.setBackgroundColor(Color.RED);
                break;
            case R.id.menu_xanh:
                manHinh.setBackgroundColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnMenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_demo, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_settings:
                        Toast.makeText(MainActivity.this, "Bạn chọn Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_email:
                        Toast.makeText(MainActivity.this, "Bạn chọn Email", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_phone:
                        Toast.makeText(MainActivity.this, "Bạn chọn Phone", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_search:
                        Toast.makeText(MainActivity.this, "Bạn chọn Search", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_share:
                        Toast.makeText(MainActivity.this, "Bạn chọn Share", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_settings:
                Toast.makeText(this, "Bạn chọn Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_email:
                Toast.makeText(this, "Bạn chọn Email", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_phone:
                Toast.makeText(this, "Bạn chọn Phone", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_search:
                Toast.makeText(this, "Bạn chọn Search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                Toast.makeText(this, "Bạn chọn Share", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}