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

        



    }

    class aboutListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent aboutIntent = new Intent(getApplicationContext(),AboutActivity.class);
            startActivity(aboutIntent);
        }
    }

    class playListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent playIntent = new Intent(getApplicationContext(),PlayActivity.class);
            startActivity(playIntent);
        }
    }
}
