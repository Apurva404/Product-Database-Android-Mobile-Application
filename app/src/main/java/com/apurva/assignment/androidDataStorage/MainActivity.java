package com.apurva.assignment.androidDataStorage;

import android.app.Activity;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;

public class MainActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insert(View v) {
        Intent intent = new Intent(MainActivity.this, ActivityInsert.class);
        startActivity(intent);
    }

    public void search(View v) {
        Intent intent = new Intent(MainActivity.this, ActivitySearch.class);
        startActivity(intent);
    }
}


