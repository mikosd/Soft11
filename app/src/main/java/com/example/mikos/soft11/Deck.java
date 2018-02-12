package com.example.mikos.soft11;

import android.media.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by JohnL on 2/10/2018.
 */

 /*
    * Our deck class contains a collection of cards, as well as ways we can operate on the deck,
    * these operations include drawing from and shuffling.
    * */
public class Deck{
    private ArrayList<Card> cards;
    /*
    * Our constructor for our deck generates all 52 cards that would be in a deck, by creating
    * collections of suits, values and weights, and iterating through each list, until applicable
    * combinations have been assigned. It takes no arguments, and returns nothing
    * */
    public Deck() {
        String [] suits = {"s","h","c","d"};
        String [] values = {"ace","two","three","four","five","six","seven","eight","nine","ten","jack","queen","king"};
        int [][] weights = {{11,1},{2},{3},{4},{5},{6},{7},{8},{9},{10},{10},{10},{10}};
        int[] cardImages = new int[]{
                /////////////////Spades////////////////
                R.drawable.ace_s, R.drawable.king_s, R.drawable.queen_s,
                R.drawable.jack_s, R.drawable.ten_s, R.drawable.nine_s,
                R.drawable.eight_s, R.drawable.seven_s, R.drawable.six_s,
                R.drawable.five_s, R.drawable.four_s, R.drawable.three_s,
                R.drawable.two_s,
                //////////////////Hearts////////////////////////////
                R.drawable.ace_h, R.drawable.king_h, R.drawable.queen_h,
                R.drawable.jack_h, R.drawable.ten_h, R.drawable.nine_h,
                R.drawable.eight_h, R.drawable.seven_h, R.drawable.six_h,
                R.drawable.five_h, R.drawable.four_h, R.drawable.three_h,
                R.drawable.two_h,
                /////////////////Clubs//////////////////////////////
                R.drawable.ace_c, R.drawable.king_c, R.drawable.queen_c,
                R.drawable.jack_c, R.drawable.ten_c, R.drawable.nine_c,
                R.drawable.eight_c, R.drawable.seven_c, R.drawable.six_c,
                R.drawable.five_c, R.drawable.four_c, R.drawable.three_c,
                R.drawable.two_c,
                ////////////////Diamonds///////////////////////////
                R.drawable.ace_d, R.drawable.king_d, R.drawable.queen_d,
                R.drawable.jack_d, R.drawable.ten_d, R.drawable.nine_d,
                R.drawable.eight_d, R.drawable.seven_d, R.drawable.six_d,
                R.drawable.five_d, R.drawable.four_d, R.drawable.three_d,
                R.drawable.two_d
        };

        cards = new ArrayList<>(52);
        int cardCount = 52;
        for (int i=0; i < 4;i++) {
            for (int j=0;j<13;j++) {
                cards.add(new Card(values[j], suits[i], weights[j], cardImages[cardCount]));
                cardCount--;
            }
        }



    }

    /*
    * The draw function removes the top card from the deck, "Drawing" the card involves copying
    * the top card of the deck to a temporary card object, deleting the top card of the deck,
    * and returning the temporary object.
    * */
    public Card draw()
    {
        Card drawn = cards.get(0);
        cards.remove(0);
        return drawn;
    }
    /*
    * The shuffle function swaps positions of the cards to a random order, so the card order
    * is psuedorandom. Our algorithm takes no arguments and returns nothing. It shuffles by
    * taking the last card of the deck, generating a random number within the remaining cards
    * and swaps the card, then, it decrements to the next cards and repeats the process.
     * This function is based on the Fisher-Yates Shuffle Algorithm, more information can be
     * found here:
     * https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
    * */
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
}