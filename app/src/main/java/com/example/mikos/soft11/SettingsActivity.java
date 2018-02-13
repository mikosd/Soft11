package com.example.mikos.soft11;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);





        Button button_confirm = findViewById(R.id.button_confirm);
        button_confirm.setOnClickListener(new returnListener());



    }

    class returnListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            int chips = 0;
            String name;
            EditText editText_chips = findViewById(R.id.editText_chips);
            EditText editText_name = findViewById(R.id.editText_name);
            Intent returnIntent = new Intent();
            if(!editText_chips.getText().toString().equals("")) {
                chips = Integer.parseInt(editText_chips.getText().toString());
            }
            if(chips < 1){chips=1;}
            if(chips > 5000){chips = 5000;}
            name = editText_name.getText().toString();
            returnIntent.putExtra("chips",chips);
            returnIntent.putExtra("name",name);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();

        }
    }

}