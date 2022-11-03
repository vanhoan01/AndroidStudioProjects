package com.nguyenvanhoan.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this, "ghichu.sqlite", null, 1);

        //tao bang
        database.QueryData("CREATE TABLE IF NOT EXITS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(200))");
        //insert
        database.QueryData("INSERT INTO CongViec VALUES (null, 'Làm bài tập')");
    }
}