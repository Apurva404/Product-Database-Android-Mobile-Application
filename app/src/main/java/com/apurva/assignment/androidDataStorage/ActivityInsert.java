package com.apurva.assignment.androidDataStorage;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.widget.EditText;

public class ActivityInsert extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }

    public void saveInDB(View v) {
        EditText editText1 = (EditText)findViewById(R.id.item_name_edit_box);
        EditText editText2 = (EditText)findViewById(R.id.item_desc_edit_box);
        EditText editText3 = (EditText)findViewById(R.id.item_price_edit_box);
        EditText editText4 = (EditText)findViewById(R.id.item_review_edit_box);

        String name = editText1.getText().toString();
        String desc = editText2.getText().toString();
        Float price = Float.parseFloat(editText3.getText().toString());
        String review = editText4.getText().toString();

        DataController dataController = new DataController(getBaseContext());
        dataController.open();

        long retValue = dataController.insert(name,desc,price,review);
        dataController.close();

        if(retValue != -1) {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.save_success_msg);
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(context, text, duration).show();
        }

        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
    }

    public void cancelInsert(View v) {
        this.finish();
    }
}

