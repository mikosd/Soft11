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

        Button button_start = findViewById(R.id.button_play);
        Button button_about = findViewById(R.id.button_about);
        Button button_settings = findViewById(R.id.button_settings);

        button_about.setOnClickListener(new aboutListener());
        button_settings.setOnClickListener(new settingsListener());

    }

    class aboutListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            Intent aboutIntent = new Intent(getApplicationContext(),AboutActivity.class);
            startActivity(aboutIntent);
        }
    }



    class settingsListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            Intent settingIntent = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(settingIntent);
        }
    }
}
