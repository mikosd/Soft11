package com.example.mikos.soft11;

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
    public Deck()
    {
        String [] suits = {"Spades","Hearts","Clubs","Diamonds"};
        String [] values = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
        int [][] weights = {{11,1},{2},{3},{4},{5},{6},{7},{8},{9},{10},{10},{10},{10}};
        cards = new ArrayList<>(52);
        for (int i=0; i < 4;i++)
        {
            for (int j=0;j<13;j++)
            {
                cards.add(new Card(values[j], suits[i], weights[j]));
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