package com.example.mikos.soft11;

/**
 * Created by JohnL on 2/6/2018.
 */

public class BlackJack{

    public class Card{
        private char value;
        private int weight;
        private char suit;

        public Card(char value, char suit, int weight){
            this.value = value;
            this.suit = suit;
            this.weight = weight;
        }
        public Card(){}
    }


}
