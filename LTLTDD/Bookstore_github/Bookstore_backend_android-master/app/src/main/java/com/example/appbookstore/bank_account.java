package com.example.appbookstore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appbookstore.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class bank_account extends AppCompatActivity {

    private Button btnAddBankAccount;
    private TextView tvBankName;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account);
        // setColorStatusBar and setActionBar
        setColorStatusBar();
        // setActionBar();
        toolbarNavigation();
        // move to add bank account
        btnAddBankAccount = (Button) findViewById(R.id.btn_addBankAccount);
        btnAddBankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bank_account.this, add_bank_account.class);
                startActivity(intent);
            }
        });
        // mapping
        tvBankName = (TextView) findViewById(R.id.bankAccount_fullName);
        // call api
        getUsers(1);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(bank_account.this,R.color.white));
    }

    private void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color=\"#005792\">" + "<small>Tài khoản ngân hàng</small>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        // insert icon back for actionbar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_gray);
    }
    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.bank_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    // call api
    private void getUsers(int id){
        ApiService.apiService.getUsers(id)
                .enqueue(new Callback<UsersModel>() {
                    @Override
                    public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                        UsersModel usersModel = response.body();
                        if (usersModel != null){
                            tvBankName.setText(usersModel.getName());
                        }
                    }
                    @Override
                    public void onFailure(Call<UsersModel> call, Throwable t) {
                        tvBankName.setText("Lỗi!");
                    }
                });
    }
}