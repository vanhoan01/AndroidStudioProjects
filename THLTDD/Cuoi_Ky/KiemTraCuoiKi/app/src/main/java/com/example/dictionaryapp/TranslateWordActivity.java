package com.example.dictionaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionaryapp.Adapter.MeaningsAdapter;
import com.example.dictionaryapp.Adapter.PhonicticsAdapter;
import com.example.dictionaryapp.Model.APIResponse;
import com.example.dictionaryapp.Model.TaiKhoan;

public class TranslateWordActivity extends AppCompatActivity {
    private SearchView search_view;
    private TextView textView_word;
    private RecyclerView recycler_phonetics, recycler_meanins;
    private ProgressDialog progressDialog;
    private PhonicticsAdapter phonicticsAdapter;
    private MeaningsAdapter meaningsAdapter;
    private TaiKhoan tk;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_word);

        intent = getIntent();
        tk = (TaiKhoan) intent.getSerializableExtra("TaiKhoan");

        AnhXa();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading.....");
        progressDialog.show();
        RequestManager manager = new RequestManager(TranslateWordActivity.this);
        manager.getWordMeaning(listener, "Hello");
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for " + query);
                progressDialog.show();
                RequestManager manager = new RequestManager(TranslateWordActivity.this);
                manager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private  final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if(apiResponse == null){
                Toast.makeText(TranslateWordActivity.this, "No data found!!!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(TranslateWordActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(APIResponse apiResponse) {
        textView_word.setText("Word: " + apiResponse.getWord());
        recycler_phonetics.setHasFixedSize(true);
        recycler_phonetics.setLayoutManager(new GridLayoutManager(this,1));
        phonicticsAdapter = new PhonicticsAdapter(this, apiResponse.getPhonetics());
        recycler_phonetics.setAdapter(phonicticsAdapter);

        recycler_meanins.setHasFixedSize(true);
        recycler_meanins.setLayoutManager(new GridLayoutManager(this, 1));
        meaningsAdapter = new MeaningsAdapter(this, apiResponse.getMeanings());
        recycler_meanins.setAdapter(meaningsAdapter);
    }

    public void AnhXa(){
        search_view = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textView_word);
        recycler_phonetics = findViewById(R.id.recycler_phonetics);
        recycler_meanins = findViewById(R.id.recycler_meanins);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(tk.getPhanQuyen() == 0){
            getMenuInflater().inflate(R.menu.menu_option, menu);
        }
        else{
            getMenuInflater().inflate(R.menu.menu_admin, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_dangxuat:
                intent = new Intent(TranslateWordActivity.this, LoginActivity.class);
                intent.putExtra("TaiKhoan", tk);
                startActivity(intent);
                break;
            case R.id.menu_tongquan:
                intent = new Intent(TranslateWordActivity.this, AdminActivity.class);
                intent.putExtra("TaiKhoan", tk);
                startActivity(intent);
                break;
            case R.id.menu_dichtu:
                intent = new Intent(TranslateWordActivity.this, TranslatorTextActivity.class);
                intent.putExtra("TaiKhoan", tk);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}