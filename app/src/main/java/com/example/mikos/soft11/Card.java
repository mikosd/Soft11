package com.example.mikos.soft11;

/**
 * Created by JohnL on 2/10/2018.
 */
/*
    * The Card class has members to represent the Suit, value and an array for the "weights" of the
    * card. In blackjack, the ace can either be a 1 or an 11 so we should make sure both values are
    * represented in the object.
    * */

public class Card{
    private String value;
    private int[] weight;
    private String suit;
    private int[] cardImage;
    /*
    * This is the Constructor for our Card Object, it takes values for the suit, value, as well
    * as an array of values for the weight, and creates a new card from the information.
    * */
    public Card(String value, String suit, int[] weight, int cardImage) {
        this.value = value;
        this.suit = suit;
        this.weight = weight;
        this.cardImage = cardImage;
    }
    /*
    * This is the default constructor for the Card Object, it simply creates a blank card
    * */
    public Card(){}

    public String getValue() {
        return value;
    }

    public int[] getWeight() {
        return weight;
    }

    public String getSuit() {
        return suit;
    }

    public int[] getCardImage() {return cardImage;}

}



