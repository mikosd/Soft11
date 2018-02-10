package com.example.mikos.soft11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_play);

        findViewById(R.id.button_about).setOnClickListener(new aboutListener());

        button = findViewById(R.id.button_settings);

    }

    class aboutListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent aboutIntent = new Intent(getApplicationContext(),AboutActivity.class);
            startActivity(aboutIntent);
        }
    }
}
