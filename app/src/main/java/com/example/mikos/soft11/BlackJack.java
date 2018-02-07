package com.example.mikos.soft11;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by JohnL on 2/6/2018.
 */

public class BlackJack{

    public class Card{
        private String value;
        private int weight[];
        private char suit;

        public Card(String value, char suit, int weight[])
        {
            this.value = value;
            this.suit = suit;
            this.weight = weight;
        }
        public Card(){}
    }

    public class Deck{
        private ArrayList<Card> cards;

        public Deck()
        {
            char [] suits = {'S','H','C','D'};
            String [] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
            int [][] weights = {{1,11},{2},{3},{4},{5},{6},{7},{8},{9},{10},{10},{10},{10}};
            //cards = new Card[52];
            int count = 0;
            for (int i=0; i < 4;i++)
            {
                for (int j=0;j<13;j++) {
                    cards.add(new Card(values[j], suits[i], weights[j]));
                }
        }}
        public Card draw()
        {
            Card drawn = cards.get(0);
            cards.remove(0);
            return drawn;
        }
        public void shuffle()
        {
            Random r = new Random();
            for (int i = cards.size()-1; i > 0; i--)
            {
                int j = r.nextInt(i);
                Card temp = cards.get(i);
                cards.set(i,cards.get(j));
                cards.set(j,temp);
            }
        }
    }}
