package com.nguyenvanhoan.datecountdown;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {
    ListView lvEvent;
    ImageButton tbtAdd;
    private ArrayList<Event> arrayEvent;
    EventAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Date Countdown");

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String title = bundle.getString("title");
        String event = bundle.getString("event");
        String days = bundle.getString("days");

        lvEvent = (ListView) findViewById(R.id.listViewEvent);
        tbtAdd = (ImageButton) findViewById(R.id.imageButtonAdd);

        if(arrayEvent == null){
            arrayEvent = new ArrayList<>();
            arrayEvent.add(new Event("Kate's Wedding", "Souuth Afria", "25 day remains"));
            arrayEvent.add(new Event("Sarah's Birthday", "Buy Presents", "250 day remains"));
            arrayEvent.add(new Event("Kante's Birthday", "Buy Cake", "100 day remains"));
            arrayEvent.add(new Event(title, event, days));
        }else{
            arrayEvent.add(new Event(title, event, days));
        }

        adapter = new EventAdapter(this, R.layout.activity_item_event, arrayEvent);
        lvEvent.setAdapter(adapter);

        tbtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}