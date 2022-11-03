package com.nguyenvanhoan.explicitintents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LayoutActivity2 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout2);
        final EditText receiveValueEdit = (EditText) findViewById(R.id.value_receive);
        final Button callReceiverButton = (Button) findViewById(R.id.call_button);
        // Lay ve Bundle duoc gui kem Intent roi lay ra gia tri
        Bundle receiveBundle = this.getIntent().getExtras();
        final long receiveValue = receiveBundle.getLong("value");
        receiveValueEdit.setText(String.valueOf(receiveValue));
        callReceiverButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Khoi tao 1 Intent de gui toi BroadCast Receiver
                // Gan gia tri vao Intent, lan nay ko can Bundle nua
                Intent i = new Intent(LayoutActivity2.this, Receiver.class);
                i.putExtra("new value", receiveValue - 10);
                sendBroadcast(i);
            }
        });
    }
}
