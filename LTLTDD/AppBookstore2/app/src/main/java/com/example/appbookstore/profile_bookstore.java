package com.example.appbookstore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class profile_bookstore extends AppCompatActivity {

    private Button btnDetailInfo;
    private Button btnBankAccount;
    private Button btnCoins;
    private Button btnPhoneNumber;
    private Button btnEmail;
    private Button btnChangePassword;
    private Button btnOpenDialog_changePhone;
    private Button btnOpenDialog_changeEmail;
    private Button btnOpenDialog_changePassword;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_bookstore);
        // change status bar
        setColorStatusBar();
        // change actionbar
//        setActionBar();
        toolbarNavigation();
        // move to details information
        btnDetailInfo = (Button) findViewById(R.id.btn_detail_info);
        btnDetailInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_bookstore.this, details_personal_info.class);
                startActivity(intent);
            }
        });
        // move to bank account
        btnBankAccount = (Button) findViewById(R.id.btn_bank_account);
        btnBankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_bookstore.this, bank_account.class);
                startActivity(intent);
            }
        });
        // move to Bookstore Coins
        btnCoins = (Button) findViewById(R.id.btn_coins);
        btnCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_bookstore.this, exchange_coins.class);
                startActivity(intent);
            }
        });
        // move to diaglog change phone number
        btnOpenDialog_changePhone = (Button) findViewById(R.id.btn_phoneNumber);
        btnOpenDialog_changePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.BOTTOM,R.layout.activity_dialog_change_phonenumber);
            }
        });
        // move to dialog change email
        btnOpenDialog_changeEmail = (Button) findViewById(R.id.btn_email);
        btnOpenDialog_changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.BOTTOM, R.layout.activity_dialog_change_email);
            }
        });
        // move to dialog change password
        btnOpenDialog_changePassword = (Button) findViewById(R.id.btn_changePassword);
        btnOpenDialog_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(profile_bookstore.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_dialog_enter_old_password, null);
                mBuilder.setPositiveButton(Html.fromHtml("<font color='#00BBF0'>Tiếp tục</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openDialog(Gravity.BOTTOM, R.layout.activity_dialog_enter_newpassword);
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(profile_bookstore.this,R.color.colorStatusBar));
    }

    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

//    private void setActionBar(){
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle(Html.fromHtml("<small>Thông tin người dùng</small>"));
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0070A9")));
//        // insert icon back for actionbar
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
//    }



    // open dialog
    public void openDialog(int gravity, int view){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        // if click outside area, dialog will hide
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        } else{
            dialog.setCancelable(false);
        }
        dialog.show();
    }
}