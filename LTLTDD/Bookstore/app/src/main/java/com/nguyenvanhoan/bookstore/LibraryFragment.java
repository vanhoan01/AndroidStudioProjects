package com.nguyenvanhoan.bookstore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenvanhoan.bookstore.Adapter.BookLibraryAdapter;
import com.nguyenvanhoan.bookstore.Models.BookLibrary;
import com.nguyenvanhoan.bookstore.Models.PopularBook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LibraryFragment extends Fragment {

    ListView lvThuVien;
    ArrayList<BookLibrary> bookArrayList;
    BookLibraryAdapter adapter;
    private String urlGetLibrary = "https://bookstoreandroid.000webhostapp.com/bookstore/getthuvien.php?iduser=US00000001&status=2";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        lvThuVien = (ListView) view.findViewById(R.id.listviewThuVien);

        bookArrayList = new ArrayList<>();
        adapter = new BookLibraryAdapter(getActivity(), R.layout.dong_thuvien, bookArrayList);
        lvThuVien.setAdapter(adapter);
        GetData(urlGetLibrary);

        return view;
    }
    public void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        bookArrayList.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                bookArrayList.add(new BookLibrary(
                                        object.getString("productID"),
                                        object.getString("userID"),
                                        object.getString("name"),
                                        object.getString("productImg"),
                                        object.getString("authorname"),
                                        object.getInt("status")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
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