package com.example.checkit;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.checkit.ConnectionClasses.ServerGetOperations;
import com.example.checkit.Data.EventModel;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_event_list);

        ServerGetOperations serverOperations = new ServerGetOperations();

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));




        ListView mListView = (ListView) findViewById(R.id.eventList);

        ArrayList<EventModel> eventList = serverOperations.getEventList();


        // ArrayAdapter adapter = new ArrayAdapter(this,R.layout.list_item_layout, events);
        EventListAdapter adapter = new EventListAdapter(this,R.layout.adapter_view_layout, eventList);

        mListView.setAdapter(adapter);


    }
}
