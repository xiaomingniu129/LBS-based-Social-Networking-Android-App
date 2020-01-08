package com.laioffer.eventreporter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EventGridActivity extends AppCompatActivity {
    int pos = 0;
    CommentFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_grid);

        Intent intent = getIntent();
        pos = intent.getIntExtra("position", 0);
        fragment = new CommentFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.grid_container, fragment).commit();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        fragment.onItemSelected(pos);
    }
}
