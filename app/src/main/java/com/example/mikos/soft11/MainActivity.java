package com.example.mikos.soft11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
    static final int START_SETTINGS = 1;
    int chips;
    String name;
    Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_play = findViewById(R.id.button_play);
        Button button_about = findViewById(R.id.button_about);
        Button button_settings = findViewById(R.id.button_settings);

        button_play.setOnClickListener(new playListener());
        button_about.setOnClickListener(new aboutListener());
        button_settings.setOnClickListener(new settingsListener());

        //BlackJack.Player player= new BlackJack.Player(name,chips);
        //player.win_chips(100);

        //Toast toast = Toast.makeText(getApplicationContext(), player.return_score(), Toast.LENGTH_SHORT);
        //toast.show();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == START_SETTINGS) {
            if(resultCode == Activity.RESULT_OK){
                name = data.getStringExtra("name");
                chips = data.getIntExtra("chips",1);

                player = new Player(name,chips);
                player.win_chips(1000); //this adds 1000 chips on top of our original input, wasn't sure if this is intended
                Toast toast = Toast.makeText(getApplicationContext(), name+" "+ player.chips, Toast.LENGTH_SHORT);
                toast.show();

            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }
    class playListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            //passing our whole object over, though the object's name is private
            Intent playIntent = new Intent(getApplicationContext(),PlayActivity.class);
            playIntent.putExtra("player",player);
            startActivity(playIntent);
        }
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
            startActivityForResult(settingIntent,START_SETTINGS);

        }
    }

}
