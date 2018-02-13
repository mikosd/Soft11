package com.example.mikos.soft11;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JohnL on 2/10/2018.
 */
    /*
    * The Player object represents someone playing blackjack. A player has a name, an amount of chips
    * determined by the options menu, and a hand that starts empty. The hand is represented by a 2d
    * Arraylist of cards, this supports n number of splits.
    * */

public class Player implements Serializable {
    private String name;
    private int chips;
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
        if(t.getValue().equals("ace")){
            hasAce = true;
            numAce+=1;
        }

    }
    /*The player can win a specified amount of chips. win_chips adds the integer argument of
    chips to the players total chip count. This function returns nothing*/
    public void win_chips(int chips){
        this.chips = this.getChips() + chips;
    }
    /*Lose chips works inversely from win chips, except it returns a bool. If the amount the
    player would lose is greater than his chip count, the game returns false, if not, it returns
    true*/
    public boolean lose_chips(int chips){
        if (this.getChips() >= chips){
            this.chips = this.getChips() - chips;
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
        int tempAce = numAce;
        boolean tempHasAce = hasAce;
        for (int i=0;i< hands.size();i++){
            temp += hands.get(i).getWeight()[0];
        }
        while(temp > 21 && tempHasAce){
            temp-=10;
            tempAce-=1;
            if(tempAce ==0){
                tempHasAce = false;
            }
        }
        return temp;
    }


    public String getName() {
        return name;
    }

    public int getChips() {
        return chips;
    }
    public Card getFromHand(int index){

        return hands.get(index);
    }
    public int getHandSize(){
        return hands.size();
    }
    public void emptyHand(){
        hands.clear();
    }
}