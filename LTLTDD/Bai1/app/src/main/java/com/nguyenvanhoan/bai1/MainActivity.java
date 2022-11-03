package com.nguyenvanhoan.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText edit = (EditText) findViewById(R.id.edit_text);
        final TextView text = (TextView) findViewById(R.id.text_view);
        // Thiết lập xử lý cho sự kiện nhấn nút giữa của điện thoại
        edit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_ENTER ) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        text.setText(edit.getText().toString());
                        edit.setText("");
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
