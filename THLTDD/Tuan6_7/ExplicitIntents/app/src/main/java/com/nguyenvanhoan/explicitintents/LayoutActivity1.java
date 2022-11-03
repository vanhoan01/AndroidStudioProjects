package com.nguyenvanhoan.explicitintents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LayoutActivity1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout1);
        final EditText editValue = (EditText) findViewById(R.id.value_edit);
        final Button sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueString = editValue.getText().toString();
                long value;
                if (valueString != null) {
                    value = Long.parseLong(valueString);
                } else {
                    value = 0;
                }
                // Tao 1 doi tuong Bundle de gui di cung Intent
                Bundle sendBundle = new Bundle();
                sendBundle.putLong("value", value);
                // Tao Intent de khoi chay Activity2 va gan sendBundble vao Intent
                Intent i = new Intent(LayoutActivity1.this, LayoutActivity2.class);
                i.putExtras(sendBundle);
                startActivity(i);
                // Giai phong Activity1 khoi Activity Stack vi ta se ko quay lai no nua
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}