package com.nguyenvanhoan.recyclerview_nestedscrollview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvCategory;
    private RecyclerView rcvUser;

    private CategoryAdapter categoryAdapter;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvCategory = findViewById(R.id.rcvCategory);
        rcvUser = findViewById(R.id.rcvUser);

        //category
        categoryAdapter = new CategoryAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcvCategory.setLayoutManager(gridLayoutManager);
        rcvCategory.setFocusable(false);
        rcvCategory.setNestedScrollingEnabled(false);
        categoryAdapter.setData(getListCategory());
        rcvCategory.setAdapter(categoryAdapter);

        //User
        userAdapter = new UserAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //myRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvUser.setLayoutManager(linearLayoutManager);
        rcvUser.setFocusable(false);
        rcvUser.setNestedScrollingEnabled(false);
        userAdapter.setData(getListUser());
        rcvUser.setAdapter(userAdapter);
    }

    private List<User> getListUser() {
        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.avatar_1, "User 1"));
        list.add(new User(R.drawable.avatar_2, "User 2"));
        list.add(new User(R.drawable.avatar_3, "User 3"));
        list.add(new User(R.drawable.avatar_4, "User 4"));
        list.add(new User(R.drawable.avatar_5, "User 5"));
        list.add(new User(R.drawable.avatar_1, "User 1"));
        list.add(new User(R.drawable.avatar_2, "User 2"));
        list.add(new User(R.drawable.avatar_3, "User 3"));
        list.add(new User(R.drawable.avatar_4, "User 4"));
        list.add(new User(R.drawable.avatar_5, "User 5"));
        return list;
    }

    private List<Category> getListCategory() {
        List<Category> list = new ArrayList<>();
        list.add(new Category(R.drawable.avatar_1, "Category 1"));
        list.add(new Category(R.drawable.avatar_2, "Category 2"));
        list.add(new Category(R.drawable.avatar_3, "Category 3"));
        list.add(new Category(R.drawable.avatar_4, "Category 4"));
        list.add(new Category(R.drawable.avatar_5, "Category 5"));
        list.add(new Category(R.drawable.avatar_1, "Category 1"));
        list.add(new Category(R.drawable.avatar_2, "Category 2"));
        list.add(new Category(R.drawable.avatar_3, "Category 3"));
        list.add(new Category(R.drawable.avatar_4, "Category 4"));
        list.add(new Category(R.drawable.avatar_5, "Category 5"));
        return list;
    }
}