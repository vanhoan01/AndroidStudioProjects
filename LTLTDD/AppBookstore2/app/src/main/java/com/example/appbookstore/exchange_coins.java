package com.example.appbookstore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

public class exchange_coins extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_coins);
        // setActionBar and statusBar
        setColorStatusBar();
//        setActionBar();
        toolbarNavigation();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(exchange_coins.this,R.color.colorStatusBar));
    }

    private void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<small>Bookstore xu</small>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0070A9")));
        // insert icon back for actionbar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }
    private void toolbarNavigation() {
        Toolbar toolbar = findViewById(R.id.coin_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }
}