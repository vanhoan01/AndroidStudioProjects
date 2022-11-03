package com.nguyenvanhoan.json_language;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageButton ibtnVN, ibtnEL;
    private TextView txtInfo;
    private String noiDung = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        new ReadJSON().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json");

        ibtnEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReadJSONLanguage("en");
            }
        });

        ibtnVN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReadJSONLanguage("vn");
            }
        });
    }

    private class ReadJSON extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer content = new StringBuffer();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            noiDung = s;
            ReadJSONLanguage("vn ");
        }
    }

    private void ReadJSONLanguage(String lang){
        try {
            JSONObject object = new JSONObject(noiDung);
            JSONObject objectLang = object.getJSONObject("language");
            JSONObject objectVN = objectLang.getJSONObject(lang);
            String ten = objectVN.getString("name");
            String diachi = objectVN.getString("address");
            String khoahoc1 = objectVN.getString("course1");
            String khoahoc2 = objectVN.getString("course2");
            String khoahoc3 = objectVN.getString("course3");

            txtInfo.setText(ten +"\n" + diachi +"\n" + khoahoc1 +"\n"+ khoahoc2 + "\n" + khoahoc3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void AnhXa() {
        ibtnVN = (ImageButton) findViewById(R.id.imageButtonVietNam);
        ibtnEL = (ImageButton) findViewById(R.id.imageButtonEngLand);
        txtInfo = (TextView) findViewById(R.id.textViewInfo);
    }
}