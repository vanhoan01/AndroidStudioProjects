package com.nguyenvanhoan.recyclerviewgridlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvUser;
    private UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvUser = (RecyclerView) findViewById(R.id.recyclerViewUser);
        adapter = new UserAdapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rcvUser.setLayoutManager(gridLayoutManager);

        adapter.setData(getListUser());
        rcvUser.setAdapter(adapter);
    }

    private List<User> getListUser() {
        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.avatar_1, "User name 1"));
        list.add(new User(R.drawable.avatar_2, "User name 2"));
        list.add(new User(R.drawable.avatar_3, "User name 3"));
        list.add(new User(R.drawable.avatar_4, "User name 4"));
        list.add(new User(R.drawable.avatar_5, "User name 5"));
        list.add(new User(R.drawable.avatar_6, "User name 6"));
        list.add(new User(R.drawable.avatar_7, "User name 7"));
        list.add(new User(R.drawable.avatar_8, "User name 8"));
        list.add(new User(R.drawable.avatar_9, "User name 9"));

        list.add(new User(R.drawable.avatar_1, "User name 1"));
        list.add(new User(R.drawable.avatar_2, "User name 2"));
        list.add(new User(R.drawable.avatar_3, "User name 3"));
        list.add(new User(R.drawable.avatar_4, "User name 4"));
        list.add(new User(R.drawable.avatar_5, "User name 5"));
        list.add(new User(R.drawable.avatar_6, "User name 6"));
        list.add(new User(R.drawable.avatar_7, "User name 7"));
        list.add(new User(R.drawable.avatar_8, "User name 8"));
        list.add(new User(R.drawable.avatar_9, "User name 9"));

        return list;
    }
}