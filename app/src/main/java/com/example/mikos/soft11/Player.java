package com.example.mikos.soft11;

import java.util.ArrayList;

/**
 * Created by JohnL on 2/10/2018.
 */
    /*
    * The Player object represents someone playing blackjack. A player has a name, an amount of chips
    * determined by the options menu, and a hand that starts empty. The hand is represented by a 2d
    * Arraylist of cards, this supports n number of splits.
    * */

public class Player{
    private String name;
    public int chips;
    private ArrayList<Card> hands;
    private boolean hasAce;
    private int numAce;
    /*
    * The constructor for player. This creates a player with a blank hand, using a name and a
    * starting amount of chips
    * */
    public Player(String name, int chips){
        this.name = name;
        this.chips = chips;
        hands = new ArrayList<>();
        hasAce = false;
        numAce=0;
    }
    /*
    * draw_from is a functions that takes two arguments, A Deck object, and an integer representing
    * a hand. Draw adds a card from the top of the deck (Using the Decks 'draw' function) and adds
    * it to the specified hand. It returns nothing.
    * */
    public void draw_from(Deck d){
        Card t = d.draw();
        hands.add(t);
        if(t.getValue().equals("Ace")){
            hasAce = true;
            numAce+=1;
        }

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
    /*
    * return_score is a method that returns an integer representing the player's score according
     * to his hand. It does this by adding the values of the weights of each of the cards in his hand
      * then, if its over 21, it attempts to lower it, by checking for Ace's. If one or more is found,
      * it decrements the score by 10, until its 21 or under. or it runs out of Aces to apply this to
    * */
    public int return_score(){
        int temp =0;
        for (int i=0;i< hands.size();i++){
            temp += hands.get(i).getWeight()[0];
        }
        while(temp > 21 && hasAce){
            temp-=10;
            numAce-=1;
            if(numAce ==0){
                hasAce = false;
            }
        }
        return temp;
    }
}