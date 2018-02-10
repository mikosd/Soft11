package com.example.mikos.soft11;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        findViewById(R.id.button_back).setOnClickListener(new backListener());

    }

    class backListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            finish();
        }
    }

}
