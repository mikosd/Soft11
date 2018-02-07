package com.example.mikos.soft11;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_play);

        button = findViewById(R.id.button_about);
        button = findViewById(R.id.button_settings);

    }

}
