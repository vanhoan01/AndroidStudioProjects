package com.nguyenvanhoan.json_object;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new ReadJSONObject().execute("https://drive.google.com/file/d/17sBmoY3Ityx6AaDEUJJ7KfUIv0EYSFpX/view?usp=sharing");
        //new ReadJSONObject().execute("https://github.com/vanhoan01/LapTrinhAndroid/blob/efae745eaaaf4f6c0bd2e6ff1f4b1389887b695a/data.json");
        new ReadJSONObject().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json");
    }

    private class ReadJSONObject extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer content = new StringBuffer();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
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
            try {
                JSONObject object = new JSONObject(s);
                String monhoc = object.getString("monhoc");
                String noihoc = object.getString("noihoc");
                String website = object.getString("website");

                Toast.makeText(MainActivity.this, monhoc + "\n" + noihoc + "\n" + website, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}