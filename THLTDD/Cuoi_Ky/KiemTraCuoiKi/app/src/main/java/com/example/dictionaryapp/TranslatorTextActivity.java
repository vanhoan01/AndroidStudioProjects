package com.example.dictionaryapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dictionaryapp.Adapter.LichSuAdapter;
import com.example.dictionaryapp.Adapter.TuKhoaAdapter;
import com.example.dictionaryapp.Model.LichSu;
import com.example.dictionaryapp.Model.TaiKhoan;
import com.example.dictionaryapp.Model.TuKhoa;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TranslatorTextActivity extends AppCompatActivity  {
    private Spinner fromSPinner, toSpinner;
    private TextInputEditText sourceText;
    private ImageView micTV;
    private MaterialButton translatorBtn, btnScanner;
    private Bitmap bitmap;
    private static final  int REQUEST_CAMERA_CODE =100;
    private TextView translatedTV;
    private TaiKhoan tk;
    private Intent intent;
    private RecyclerView rcvLichSu;
    private LichSuAdapter lichSuAdapter;
    private List<LichSu> lichSuList;
    private String urlLichSu = "https://bookstoreandroid.000webhostapp.com/translate/lichSu.php";

    String[] fromLanguages =  {"English", "Afrikaans", "Arabic", "Belarusian", "Bulgarian", "Bengali", "Catalan", "Czech",
        "Welsh", "Hindi","Urdu", "VietNam"};
    String[] toLanguages =  {"VietNam", "English", "Afrikaans", "Arabic", "Belarusian", "Bulgarian", "Bengali", "Catalan", "Czech",
            "Welsh", "Hindi","Urdu" };

    private static final int REQUEST_PERMISSION_CODE =1;
    int languageCode, fromLanguageCode, toLanguageCode=0;

    private String urlInsert = "https://bookstoreandroid.000webhostapp.com/translate/insertBienDich.php";
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private int maND = 1;
    private String banDich, ngonNguNhap, ngonNguDich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_text);

        intent = getIntent();
        tk = (TaiKhoan) intent.getSerializableExtra("TaiKhoan");
        maND = tk.getMa();

        AnhXa();

        lichSuAdapter = new LichSuAdapter();
        lichSuList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rcvLichSu.setLayoutManager(gridLayoutManager);
        rcvLichSu.setFocusable(false);
        rcvLichSu.setNestedScrollingEnabled(false);
        lichSuAdapter.setData(lichSuList);
        rcvLichSu.setAdapter(lichSuAdapter);
        urlLichSu = urlLichSu + "?ma=" + maND;
        GetDataLichSu(urlLichSu);

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fromSPinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                fromLanguageCode = getLanguageCode(fromLanguages[position]);
                ngonNguNhap = fromLanguages[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter fromAdapter = new ArrayAdapter(this, R.layout.spinner_tiem, fromLanguages);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSPinner.setAdapter(fromAdapter);

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                toLanguageCode = getLanguageCode(toLanguages[position]);
                ngonNguDich = toLanguages[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter toAdapter = new ArrayAdapter(this, R.layout.spinner_tiem, toLanguages);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);

        translatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translatedTV.setText("");
                if(sourceText.getText().toString().isEmpty()){
                    Toast.makeText(TranslatorTextActivity.this, "Please enter your text to translate", Toast.LENGTH_SHORT).show();
                }else if(fromLanguageCode == 0){
                    Toast.makeText(TranslatorTextActivity.this, "Please select source language", Toast.LENGTH_SHORT).show();
                }else if(toLanguageCode == 0){
                    Toast.makeText(TranslatorTextActivity.this, "Please select the language to make translation", Toast.LENGTH_SHORT).show();
                }else{
                    translateText(fromLanguageCode,toLanguageCode,sourceText.getText().toString());

                }
            }
        });

        micTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                i.putExtra(RecognizerIntent.EXTRA_PROMPT,"SPeak to convert in to text");
                try {
                    startActivityForResult(i,REQUEST_PERMISSION_CODE);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(TranslatorTextActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (ContextCompat.checkSelfPermission(TranslatorTextActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(TranslatorTextActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, REQUEST_CAMERA_CODE);
        }

        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(TranslatorTextActivity.this);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_PERMISSION_CODE){
            if(resultCode == RESULT_OK && data!=null){
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                sourceText.setText(result.get(0));
            }
        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),resultUri);
                    getTextFromImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void getTextFromImage(Bitmap bitmap){
        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();
        if (!recognizer.isOperational()){
            Toast.makeText(TranslatorTextActivity.this, "Error Occurred!!", Toast.LENGTH_SHORT).show();
        }else{
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i<textBlockSparseArray.size(); i++){
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");
            }
            sourceText.setText(stringBuilder.toString());
            btnScanner.setText("Retake");
        }
    }


    private void translateText(int fromLanguageCode, int toLanguageCode, String source){
        translatedTV.setText("Downloading....");
        FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                .setSourceLanguage(fromLanguageCode)
                .setTargetLanguage(toLanguageCode)
                .build();

        FirebaseTranslator translator = FirebaseNaturalLanguage.getInstance().getTranslator(options);
        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().build();


        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                translatedTV.setText("Translating...");
                translator.translate(source).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        translatedTV.setText(s);
                        banDich = s;
                        themBienDich(urlInsert);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TranslatorTextActivity.this, "Fail to translate "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TranslatorTextActivity.this, "Fail to download language Model "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void AnhXa(){
        fromSPinner = findViewById(R.id.IdFromSpinner);
        toSpinner = findViewById(R.id.idToSpinner);
        fromSPinner = findViewById(R.id.IdFromSpinner);
        sourceText = findViewById(R.id.idEditSource);
        micTV = findViewById(R.id.idTVMic);
        translatorBtn = findViewById(R.id.idBtnTranslated);
        translatedTV = findViewById(R.id.idTVTranslate);
        rcvLichSu = findViewById(R.id.recyclerviewLichSu);
        btnScanner = findViewById(R.id.btnScanner);
    }

    public int getLanguageCode(String language){
        int languageCode = 0;
        switch (language){
            case "English":
                languageCode = FirebaseTranslateLanguage.EN;
                break;
            case "Afrikaans":
                languageCode = FirebaseTranslateLanguage.AF;
                break;
            case "Arabic":
                languageCode = FirebaseTranslateLanguage.AR;
                break;
            case "Belarusian":
                languageCode = FirebaseTranslateLanguage.BE;
                break;
            case "Bulgarian":
                languageCode = FirebaseTranslateLanguage.BG;
                break;
            case "Bengali":
                languageCode = FirebaseTranslateLanguage.BN;
                break;
            case "Catalan":
                languageCode = FirebaseTranslateLanguage.CA;
                break;
            case "Czech":
                languageCode = FirebaseTranslateLanguage.CS;
                break;
            case "Welsh":
                languageCode = FirebaseTranslateLanguage.CY ;
                break;
            case "Hindi":
                languageCode = FirebaseTranslateLanguage.HI;
                break;
            case "VietNam":
                languageCode = FirebaseTranslateLanguage.VI;
                break;
            default:
                languageCode = 0;

        }
        return languageCode;
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
                intent = new Intent(TranslatorTextActivity.this, LoginActivity.class);
                intent.putExtra("TaiKhoan", tk);
                startActivity(intent);
                break;
            case R.id.menu_tongquan:
                intent = new Intent(TranslatorTextActivity.this, AdminActivity.class);
                intent.putExtra("TaiKhoan", tk);
                startActivity(intent);
                break;
            case R.id.menu_tudien:
                intent = new Intent(TranslatorTextActivity.this, TranslateWordActivity.class);
                intent.putExtra("TaiKhoan", tk);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void themBienDich(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GetDataLichSu(urlLichSu);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TranslatorTextActivity.this, "Xãy ra lỗi!", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("banNhap", sourceText.getText().toString().trim());
                params.put("banDich", banDich);
                params.put("ngonNguNhap", ngonNguNhap);
                params.put("ngonNguDich", ngonNguDich);
                calendar = Calendar.getInstance();
                //simpleDateFormat.format(calendar.getTime()
                params.put("thoiGian", simpleDateFormat.format(calendar.getTime()));
                params.put("maND", String.valueOf(maND));

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    private void GetDataLichSu(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(TranslatorTextActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        lichSuList.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                lichSuList.add(new LichSu(
                                        object.getString("banNhap"),
                                        object.getString("banDich")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        lichSuAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TranslatorTextActivity.this, "Loi!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
