package com.example.appbookstore;

import static androidx.fragment.app.FragmentStatePagerAdapter.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    private ViewPager vpgNoiDung;
    private BottomNavigationView bnvMenu;
    private ImageButton imSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        vpgNoiDung = (ViewPager) findViewById(R.id.viewPagerNoiDung);
        bnvMenu = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        imSearch = (ImageButton) findViewById(R.id.imageButtonSearch);

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpgNoiDung.setAdapter(vpAdapter);

        vpgNoiDung.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bnvMenu.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bnvMenu.getMenu().findItem(R.id.menu_popular).setChecked(true);
                        break;
                    case 2:
                        bnvMenu.getMenu().findItem(R.id.menu_library).setChecked(true);
                        break;
                    case 3:
                        bnvMenu.getMenu().findItem(R.id.menu_option).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bnvMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        vpgNoiDung.setCurrentItem(0);
                        break;
                    case R.id.menu_popular:
                        vpgNoiDung.setCurrentItem(1);
                        break;
                    case R.id.menu_library:
                        vpgNoiDung.setCurrentItem(2);
                        break;
                    case R.id.menu_option:
                        vpgNoiDung.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

        imSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
