package com.nguyenvanhoan.bookstore.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nguyenvanhoan.bookstore.HomeFragment;
import com.nguyenvanhoan.bookstore.LibraryFragment;
import com.nguyenvanhoan.bookstore.OptionFragment;
import com.nguyenvanhoan.bookstore.PopularFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new PopularFragment();
            case 2:
                return new LibraryFragment();
            case 3:
                return new OptionFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
