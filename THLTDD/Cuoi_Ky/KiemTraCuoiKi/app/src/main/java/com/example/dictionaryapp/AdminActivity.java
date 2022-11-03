package com.example.dictionaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dictionaryapp.Adapter.TuKhoaAdapter;
import com.example.dictionaryapp.Model.TaiKhoan;
import com.example.dictionaryapp.Model.TuKhoa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private RecyclerView rcvTuKhoa;
    private TuKhoaAdapter tukhoaAdapter;
    private List<TuKhoa> tuKhoaList;
    private String urlPhoBien = "https://bookstoreandroid.000webhostapp.com/translate/TuKhoaPhoBien.php";
    private String urlLuotDich = "https://bookstoreandroid.000webhostapp.com/translate/dichTrongNgay.php";
    private int ldHomNay = 0, ldHomQua = 0;
    private TextView txtLuotDich, txtTiLe;
    private TaiKhoan tk;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        intent = getIntent();
        tk = (TaiKhoan) intent.getSerializableExtra("TaiKhoan");

        rcvTuKhoa = findViewById(R.id.recyclerviewPhoBien);
        txtLuotDich = (TextView) findViewById(R.id.textViewLuotDich);
        txtTiLe = (TextView) findViewById(R.id.textViewTiLe);

        tukhoaAdapter = new TuKhoaAdapter();
        tuKhoaList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rcvTuKhoa.setLayoutManager(gridLayoutManager);
        rcvTuKhoa.setFocusable(false);
        rcvTuKhoa.setNestedScrollingEnabled(false);
        tukhoaAdapter.setData(tuKhoaList);
        rcvTuKhoa.setAdapter(tukhoaAdapter);
        GetDataLuotDich(urlLuotDich);
        GetDataPhoBien(urlPhoBien);
    }
    private void setHomNay(){
        txtLuotDich.setText(String.valueOf(ldHomNay));
        float c = (float) (ldHomNay*1.0/ldHomQua*100);
        txtTiLe.setText(String.valueOf(Math.round(c*100.0)/100.0)+" %");
    }
    private void GetDataLuotDich(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(AdminActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject object = response.getJSONObject(0);
                            ldHomNay = object.getInt("luotDich");
                            object = response.getJSONObject(1);
                            ldHomQua = object.getInt("luotDich");
                            setHomNay();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        tukhoaAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminActivity.this, "Loi!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void GetDataPhoBien(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(AdminActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                tuKhoaList.add(new TuKhoa(
                                        i + 1,
                                        object.getString("banNhap"),
                                        object.getString("banDich")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        tukhoaAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminActivity.this, "Loi!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_dichtu:
                intent = new Intent(AdminActivity.this, TranslatorTextActivity.class);
                intent.putExtra("TaiKhoan", tk);
                startActivity(intent);
                break;
            case R.id.menu_dangxuat:
                intent = new Intent(AdminActivity.this, LoginActivity.class);
                intent.putExtra("TaiKhoan", tk);
                startActivity(intent);
                break;
            case R.id.menu_tudien:
                intent = new Intent(AdminActivity.this, TranslateWordActivity.class);
                intent.putExtra("TaiKhoan", tk);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}