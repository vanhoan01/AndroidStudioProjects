package com.nguyenvanhoan.bookstore;

import static androidx.fragment.app.FragmentStatePagerAdapter.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nguyenvanhoan.bookstore.Adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager vpgNoiDung;
    private BottomNavigationView bnvMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        vpgNoiDung = (ViewPager) findViewById(R.id.viewPagerNoiDung);
        bnvMenu = (BottomNavigationView) findViewById(R.id.bottom_navigation);

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

//        rlPopular.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, PopularActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}