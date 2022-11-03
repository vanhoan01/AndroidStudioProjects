package com.nguyenvanhoan.bookstore;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenvanhoan.bookstore.Adapter.HomeGoiYAdapter;
import com.nguyenvanhoan.bookstore.Adapter.HomeTheLoaiAdapter;
import com.nguyenvanhoan.bookstore.Models.HomeBook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rcvTheLoai;
    private RecyclerView rcvviewGoiY;

    private HomeTheLoaiAdapter theLoaiAdapter;
    private HomeGoiYAdapter goiYAdapter;

    List<HomeBook> listGY;
    List<HomeBook> listTL;

    private String urlGetData = "https://bookstoreandroid.000webhostapp.com/bookstore/product.php";
    private String urlGetTheLoai = "https://bookstoreandroid.000webhostapp.com/bookstore/sachlyki.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Anh xa
        rcvTheLoai = view.findViewById(R.id.recyclerviewTheLoai);
        rcvviewGoiY = view.findViewById(R.id.recyclerviewGoiY);

        //Load sach theo the loai
        theLoaiAdapter = new HomeTheLoaiAdapter();
        listTL = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        //myRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvTheLoai.setLayoutManager(linearLayoutManager);
        rcvTheLoai.setFocusable(false);
        rcvTheLoai.setNestedScrollingEnabled(false);
        theLoaiAdapter.setData(listTL);
        rcvTheLoai.setAdapter(theLoaiAdapter);
        GetDataTheLoai(urlGetTheLoai);

        //Load sach goi y danh cho ban
        goiYAdapter = new HomeGoiYAdapter();
        listGY = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvviewGoiY.setLayoutManager(gridLayoutManager);
        rcvviewGoiY.setFocusable(false);
        rcvviewGoiY.setNestedScrollingEnabled(false);
        goiYAdapter.setData(listGY);
        rcvviewGoiY.setAdapter(goiYAdapter);
        GetData(urlGetData);

        return view;
    }

    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                listGY.add(new HomeBook(
                                        object.getString("productImg"),
                                        object.getString("name"),
                                        object.getInt("price")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        goiYAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Loi!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void GetDataTheLoai(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                listTL.add(new HomeBook(
                                        object.getString("productImg"),
                                        object.getString("name"),
                                        object.getInt("price")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        theLoaiAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Loi!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}