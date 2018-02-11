package com.example.mikos.soft11;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity{


    Player player;
    Deck deck = new Deck();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Button button_hit = findViewById(R.id.button_hit);
        button_hit.setOnClickListener(new hitListener());

        player = (Player) getIntent().getSerializableExtra("player");
        // Log.i("PLAYER", String.valueOf(player.chips)); tested for chip value  *working*
        TextView chip_tv = (TextView)findViewById(R.id.tv_chips);
        chip_tv.setText("Chips: " + player.chips);

        deck.shuffle(); //pre-shuffling deck, seems to work but is  *untested*
        Card drawn = deck.draw();
        //Log.i("suit", drawn.getSuit());
        //Log.i("value", drawn.getValue());
        //Log.i("weight", String.valueOf(drawn.getWeight()));

    }


    class hitListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {



        }
    }
}
