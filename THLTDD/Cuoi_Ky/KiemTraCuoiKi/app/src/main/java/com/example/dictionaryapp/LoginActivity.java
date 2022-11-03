package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dictionaryapp.Model.TaiKhoan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUserName;
    private EditText edtPassWord;
    private CheckBox cbRemember;
    private Button btLogin;

    private SharedPreferences preferences;
    private List<TaiKhoan> taiKhoanList;
    private TaiKhoan tk;

    private String urlDangNhap = "https://bookstoreandroid.000webhostapp.com/translate/dangNhap.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        anhXa();

        setViews();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetDataTaiKhoan(urlDangNhap);
            }
        });
    }

    private void anhXa(){
        edtUserName = (EditText) findViewById(R.id.taiKhoan);
        edtPassWord = (EditText) findViewById(R.id.matKhau);
        cbRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
        btLogin = (Button) findViewById(R.id.DangNhap);

    }

    private void setViews(){
        preferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        edtUserName.setText(preferences.getString("taikhoan", ""));
        edtPassWord.setText(preferences.getString("matkhau", ""));
        cbRemember.setChecked(preferences.getBoolean("checked", false));
    }

    public int kiemTraDN(String userName, String passWord){
        for(TaiKhoan dn : taiKhoanList) {
            if(userName.equals(dn.getTaiKhoan()) && passWord.equals(dn.getMatKhau())){
                tk = new TaiKhoan(dn.getMa(), dn.getTaiKhoan(), dn.getMatKhau(), dn.getPhanQuyen());
                return dn.getPhanQuyen();
            }
        }
        return -1;
    }
    private void GetDataTaiKhoan(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        taiKhoanList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                taiKhoanList.add(new TaiKhoan(
                                        object.getInt("ma"),
                                        object.getString("taiKhoan"),
                                        object.getString("matKhau"),
                                        object.getInt("phanQuyen")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        login();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Loi!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void login(){
        String userName = edtUserName.getText().toString().trim();
        String passWord = edtPassWord.getText().toString().trim();
        int kt = kiemTraDN(userName, passWord);
        if(kt == 0 || kt == 1){
            Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            //Nếu có check
            if(cbRemember.isChecked()){
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("taikhoan", userName);
                editor.putString("matkhau", passWord);
                editor.putBoolean("checked", true);
                editor.commit();
            }
            else{
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("taikhoan");
                editor.remove("matkhau");
                editor.remove("checked");
                editor.commit();
            }
            if(kt == 1){
                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                intent.putExtra("TaiKhoan", tk);
                startActivity(intent);
            }
            else
                if(kt == 0){
                    Intent intent = new Intent(LoginActivity.this, TranslatorTextActivity.class);
                    intent.putExtra("TaiKhoan", tk);
                    startActivity(intent);
                }
        }
        else if(kt == -1){
            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu bị sai!", Toast.LENGTH_SHORT).show();
        }
    }
}