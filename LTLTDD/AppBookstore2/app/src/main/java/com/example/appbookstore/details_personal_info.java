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

import java.security.PrivateKey;

public class details_personal_info extends AppCompatActivity {

    private Button btnChangeInfo;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_personal_info);
        // set actionbar and statusBar
        setColorStatusBar();
//        setActionBar();
        toolbarNavigation();
        // move to change information
        btnChangeInfo = (Button) findViewById(R.id.btnChange_profile);
        btnChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(details_personal_info.this,change_information_personal.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(details_personal_info.this,R.color.colorStatusBar));
    }

    //    private void setActionBar(){
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle(Html.fromHtml("<small>Thông tin chi tiết</small>"));
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0070A9")));
//        // insert icon back for actionbar
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
//    }
    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.chiTietProfile_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

}