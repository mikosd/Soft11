package com.example.mikos.soft11;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static com.example.mikos.soft11.BlackJack.*;

public class PlayActivity extends Activity{
    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

       // BlackJack.Deck deck = new BlackJack.Deck();

        //deck.shuffle();

        BlackJack.Player dealer;

     //   int bet = findViewById(R.id.);

    player = (Player) getIntent().getSerializableExtra("player");
    // Log.i("PLAYER", String.valueOf(player.chips)); tested for chip value  *working*
    }



}
