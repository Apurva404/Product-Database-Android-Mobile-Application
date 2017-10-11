package com.apurva.assignment.androidDataStorage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivitySearch extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void retrieveFromDB(View v)     {
        String searchText = ((EditText)findViewById(R.id.searchText)).getText().toString();

        DataController dataController = new DataController(getBaseContext());
        dataController.open();

        String retrievedText = dataController.retrieve(searchText);

        TextView searchResult = (TextView) findViewById(R.id.searchResult_view);
        searchResult.setText(retrievedText);
    }

    public void cancelSearch(View v) {
        this.finish();
    }
}