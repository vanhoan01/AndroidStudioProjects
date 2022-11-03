package com.nguyenvanhoan.dangnhap_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtUserName;
    private EditText edtPassWord;
    private CheckBox cbRemember;
    private Button btLogin;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        edtUserName = (EditText) findViewById(R.id.taiKhoan);
        edtPassWord = (EditText) findViewById(R.id.matKhau);
        cbRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
        btLogin = (Button) findViewById(R.id.DangNhap);

        preferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        edtUserName.setText(preferences.getString("taikhoan", ""));
        edtPassWord.setText(preferences.getString("matkhau", ""));
        cbRemember.setChecked(preferences.getBoolean("checked", false));
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUserName.getText().toString().trim();
                String passWord = edtPassWord.getText().toString().trim();
                if(userName.equals("vanhoan") && passWord.equals("123abc")){
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
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

                }
                else {
                    Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu bị sai!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}