package com.example.mikos.soft11;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity{


    Player player = new Player("player", 5000);
    Player dealer;
    Deck deck = new Deck();
    int chipsDown;


    boolean canBet = false;
    boolean canDoubleDown = false;
    boolean canHit = false;
    boolean canStand = false;
    boolean bust = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Button button_hit = findViewById(R.id.button_hit);
        Button button_bet = findViewById(R.id.button_bet);
        Button button_stand = findViewById(R.id.button_stand);
        Button button_inc = findViewById(R.id.button_up);
        Button button_double = findViewById(R.id.button_double);
        Button button_dec = findViewById(R.id.button_down);
        ImageView iv_dealer_cardup = findViewById(R.id.iv_dealer_card_up);

        TextView textView_chip = (TextView)findViewById(R.id.tv_chips);
        TextView textView_bet = (TextView)findViewById(R.id.tv_bet);

        button_hit.setOnClickListener(new hitListener());
        button_double.setOnClickListener(new doubleListener());
        button_bet.setOnClickListener(new betListener());
        button_stand.setOnClickListener(new standListener());
        button_inc.setOnClickListener(new incListener());
        button_dec.setOnClickListener(new decListener());
        player = (Player) getIntent().getSerializableExtra("player");
        dealer = new Player("Dealer", 99999);
        // Log.i("PLAYER", String.valueOf(player.chips)); tested for chip value  *working*
        textView_chip.setText("Chips: " + player.getChips());

        


        deck.shuffle();

        dealer.draw_from(deck);
        dealer.draw_from(deck);

        iv_dealer_cardup = findViewById(R.id.iv_dealer_card_up);
       // iv_dealer_cardup.setImageDrawable(dealer.getFromHand(0).getCardImage());
        TextView tv_dealer_total = findViewById(R.id.tv_dealer_total);
        tv_dealer_total.setText("Total: " + dealer.getFromHand(0).getValue() );

       /* textView_dealer_revealed_card.setText("Dealer Revealed Card: "+ dealer.getFromHand(0).getValue()+" of " + dealer.getFromHand(0).getSuit());
        Toast.makeText(getApplicationContext(), "Place Your Bet", Toast.LENGTH_SHORT).show();
        canBet = true;
        */
    }


    class hitListener implements View.OnClickListener{
        TextView textView_dealer_revealed_card = findViewById(R.id.textView_dealerCard);
        TextView textView_bet = (TextView)findViewById(R.id.tv_bet);
        @Override
        public void onClick(View view) {
            if(canHit){
                player.draw_from(deck);
                TextView textView_player_cards = (TextView) findViewById(R.id.textView_player_cards);
                textView_player_cards.setText(textView_player_cards.getText() + ", "+player.getFromHand(player.getHandSize()-1).getValue()+" of "+player.getFromHand(player.getHandSize()-1).getSuit());
                if(player.return_score()>21){
                    Toast.makeText(getApplicationContext(), "You busted", Toast.LENGTH_SHORT).show();
                    chipsDown=0;

                    deck = new Deck();
                    player.emptyHand();
                    dealer.emptyHand();
                    textView_dealer_revealed_card.setText("");
                    textView_player_cards.setText("");
                    textView_bet.setText("0");
                    deck.shuffle();

                    dealer.draw_from(deck);
                    dealer.draw_from(deck);

                    textView_dealer_revealed_card.setText("Dealer Revealed Card: "+ dealer.getFromHand(0).getValue()+" of " + dealer.getFromHand(0).getSuit());
                    Toast.makeText(getApplicationContext(), "New Game, Place Your Bet", Toast.LENGTH_SHORT).show();
                    canBet = true;
                    canHit = false;
                }
            }else{
                Toast.makeText(getApplicationContext(), "Can't do that now", Toast.LENGTH_SHORT).show();

            }


        }
    }
    class doubleListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            TextView textView_chip = (TextView)findViewById(R.id.tv_chips);
            if(canDoubleDown){
             if(chipsDown*2 < player.getChips()){
                 player.lose_chips(chipsDown);
                 chipsDown*=2;
                 canDoubleDown = false;
                 textView_chip.setText("Chips: "+player.getChips());
             }else{
                 Toast.makeText(getApplicationContext(), "Not enough", Toast.LENGTH_SHORT).show();
             }

            }else{
            Toast.makeText(getApplicationContext(), "Can't do that now", Toast.LENGTH_SHORT).show();
        }}
    }
    class betListener implements View.OnClickListener{
        TextView textView_player_cards = (TextView) findViewById(R.id.textView_player_cards);
        @Override
        public void onClick(View view) {
            TextView textView_chip = (TextView)findViewById(R.id.tv_chips);
            TextView textView_bet = (TextView)findViewById(R.id.tv_bet);
            int bet = Integer.parseInt(textView_bet.getText().toString());
        if(canBet){
        if(player.getChips() < bet){
            Toast.makeText(getApplicationContext(), "Invalid Bet: Too High", Toast.LENGTH_SHORT).show();

        }else if(bet<1){

            Toast.makeText(getApplicationContext(), "Invalid Bet: Too Low or negative", Toast.LENGTH_SHORT).show();
        }else{
            chipsDown = bet;
            player.lose_chips(bet);
            textView_chip.setText("Chips: " + player.getChips());
        }
        canBet = false;
        canDoubleDown = true;
        player.draw_from(deck);
        player.draw_from(deck);

        textView_player_cards.setText("Cards: "+player.getFromHand(0).getValue()+" of "+player.getFromHand(0).getSuit()+", "+player.getFromHand(1).getValue()+" of "+player.getFromHand(1).getSuit());
        Toast.makeText(getApplicationContext(), "Double Down? Then Hit or Stand", Toast.LENGTH_SHORT).show();
        canHit = true;
        canStand = true;
        }else {
            Toast.makeText(getApplicationContext(), "Can't do that now", Toast.LENGTH_SHORT).show();
        }}

    }
    class standListener implements View.OnClickListener{
        TextView textView_dealer_revealed_card = findViewById(R.id.textView_dealerCard);
        TextView textView_player_cards = (TextView) findViewById(R.id.textView_player_cards);
        TextView textView_chip = findViewById(R.id.tv_chips);
        TextView textView_bet = (TextView) findViewById(R.id.tv_bet);
        @Override
        public void onClick(View view) {
        if(canStand){
            if(player.return_score()<dealer.return_score()){
                Toast.makeText(getApplicationContext(), "Dealer wins, thanks for playing", Toast.LENGTH_SHORT).show();
                chipsDown=0;
                deck = new Deck();
                player.emptyHand();
                dealer.emptyHand();
                textView_dealer_revealed_card.setText("");
                textView_player_cards.setText("");
                textView_bet.setText("0");
                deck.shuffle();

                dealer.draw_from(deck);
                dealer.draw_from(deck);

                textView_dealer_revealed_card.setText("Dealer Revealed Card: "+ dealer.getFromHand(0).getValue()+" of " + dealer.getFromHand(0).getSuit());
                Toast.makeText(getApplicationContext(), "New Game, Place Your Bet", Toast.LENGTH_SHORT).show();
                canBet = true;
                canHit = false;

            }else if (player.return_score() == 21 && dealer.return_score() != 21){
                Toast.makeText(getApplicationContext(), "Blackjack! you win!", Toast.LENGTH_SHORT).show();
                player.win_chips(chipsDown*3/2);
                chipsDown=0;
                textView_chip.setText("Chips: "+player.getChips());

                deck = new Deck();
                player.emptyHand();
                dealer.emptyHand();
                textView_dealer_revealed_card.setText("");
                textView_player_cards.setText("");
                textView_bet.setText("0");
                deck.shuffle();

                dealer.draw_from(deck);
                dealer.draw_from(deck);

                textView_dealer_revealed_card.setText("Dealer Revealed Card: "+ dealer.getFromHand(0).getValue()+" of " + dealer.getFromHand(0).getSuit());
                Toast.makeText(getApplicationContext(), "New Game, Place Your Bet", Toast.LENGTH_SHORT).show();
                canBet = true;
                canHit = false;

            }else if (player.return_score()>dealer.return_score()) {
                Toast.makeText(getApplicationContext(), "you win!", Toast.LENGTH_SHORT).show();
                player.win_chips(chipsDown * 2);
                textView_chip.setText("Chips: "+player.getChips());
                chipsDown=0;
                deck = new Deck();
                player.emptyHand();
                dealer.emptyHand();
                textView_dealer_revealed_card.setText("");
                textView_player_cards.setText("");
                textView_bet.setText("0");
                deck.shuffle();

                dealer.draw_from(deck);
                dealer.draw_from(deck);

                textView_dealer_revealed_card.setText("Dealer Revealed Card: "+ dealer.getFromHand(0).getValue()+" of " + dealer.getFromHand(0).getSuit());
                Toast.makeText(getApplicationContext(), "New Game, Place Your Bet", Toast.LENGTH_SHORT).show();
                canBet = true;
                canHit = false;

            }else if (player.return_score()==dealer.return_score()) {
                Toast.makeText(getApplicationContext(), "tie", Toast.LENGTH_SHORT).show();
                player.win_chips(chipsDown);
                textView_chip.setText("Chips: "+player.getChips());
                chipsDown=0;
                deck = new Deck();
                player.emptyHand();
                dealer.emptyHand();
                textView_dealer_revealed_card.setText("");
                textView_player_cards.setText("");
                textView_bet.setText("0");
                deck.shuffle();

                dealer.draw_from(deck);
                dealer.draw_from(deck);

                textView_dealer_revealed_card.setText("Dealer Revealed Card: "+ dealer.getFromHand(0).getValue()+" of " + dealer.getFromHand(0).getSuit());
                Toast.makeText(getApplicationContext(), "New Game, Place Your Bet", Toast.LENGTH_SHORT).show();
                canBet = true;
                canHit = false;

            }
            canStand = false;









        }else{
            Toast.makeText(getApplicationContext(), "Can't do that now", Toast.LENGTH_SHORT).show();
        }


        }

    }
    class incListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            TextView textView_bet = (TextView)findViewById(R.id.tv_bet);
            int val = Integer.parseInt(textView_bet.getText().toString());
            val+=50;
            textView_bet.setText(val+"");

        }
    }
    class decListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            TextView textView_bet = (TextView)findViewById(R.id.tv_bet);
            int val = Integer.parseInt(textView_bet.getText().toString());
            val-=50;
            textView_bet.setText(val+"");

        }
    }
}
