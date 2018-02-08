package com.example.mikos.soft11;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by JohnL on 2/6/2018.
 * This blackjack class contains clases and functions that can be used to play a cardgame.
 */


/*
* The BlackJack class will contain all the objects needed to play a game of blackjack, including cards,
* decks, players and a dealer. (Currently I've only implemented the Card, and The Deck classes, the
* others will be added)
* */
public class BlackJack{
    /*
    * The Card class has members to represent the Suit, value and an array for the "weights" of the
    * card. In blackjack, the ace can either be a 1 or an 11 so we should make sure both values are
    * represented in the object.
    * */
    public class Card{
        private String value;
        private int weight[];
        private String suit;
        /*
        * This is the Constructor for our Card Object, it takes values for the suit, value, as well
        * as an array of values for the weight, and creates a new card from the information.
        * */
        public Card(String value, String suit, int weight[])
        {
            this.value = value;
            this.suit = suit;
            this.weight = weight;
        }
        /*
        * This is the default constructor for the Card Object, it simply creates a blank card
        * */
        public Card(){}
    }

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
            String [] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
            int [][] weights = {{1,11},{2},{3},{4},{5},{6},{7},{8},{9},{10},{10},{10},{10}};
            cards = new ArrayList<>(52);
            for (int i=0; i < 4;i++)
            {
                for (int j=0;j<13;j++)
                {
                    cards.add(new Card(values[j], suits[i], weights[j]));
                }
        }}

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
    /*
    * The Player object represents someone playing blackjack. A player has a name, an amount of chips
    * determined by the options menu, and a hand that starts empty. The hand is represented by a 2d
    * Arraylist of cards, this supports n number of splits.
    * */
    public class Player{
        private String name;
        private int chips;
        private ArrayList<Card> hands;
        /*
        * The constructor for player. This creates a player with a blank hand, using a name and a
        * starting amount of chips
        * */
        public Player(String name, int chips){
            this.name = name;
            this.chips = chips;
            hands = new ArrayList<>();
        }
        /*
        * draw_from is a functions that takes two arguments, A Deck object, and an integer representing
        * a hand. Draw adds a card from the top of the deck (Using the Decks 'draw' function) and adds
        * it to the specified hand. It returns nothing.
        * */
        public void draw_from(Deck d){
            hands.add(d.draw());

        }
        /*The player can win a specified amount of chips. win_chips adds the integer argument of
        chips to the players total chip count. This function returns nothing*/
        public void win_chips(int chips){
            this.chips += chips;
        }
        /*Lose chips works inversely from win chips, except it returns a bool. If the amount the
        player would lose is greater than his chip count, the game returns false, if not, it returns
        true*/
        public boolean lose_chips(int chips){
            if (this.chips>= chips){
                this.chips -= chips;
                return true;
            }
            return false;
        }
    }
    /*
    public class Dealer{
        private ArrayList<Card> hand;

        public Dealer(){
            hand = new ArrayList<Card>();
        }
        public void draw_from(Deck d){
            hand.add(d.draw());

        }
    }
    */
}
