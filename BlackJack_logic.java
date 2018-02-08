import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MyClass {
    public static void main(String args[]) {

        //inititalization
        Deck d = new Deck();
        d.shuffle();
        Player John = new Player("John",1000);
        Player Dealer = new Player("Dealer",10000000);
        int bet;
        Scanner scanner = new Scanner(System. in);
        //System.out.println(input);

        //Player places starting bet
        System.out.println("Your chip total is: "+ John.chips);
        System.out.println("What would you like your opening bet to be?");
        String input = scanner. nextLine();
        System.out.println("You entered: "+input);
        bet = Integer.parseInt(input);
        boolean valid = John.lose_chips(bet);

        if(valid){
            //players hand is decided
            John.draw_from(d);
            John.draw_from(d);
            Dealer.draw_from(d);
            Dealer.draw_from(d);
            System.out.println("Here is your hand");
            System.out.println(John.hands.get(0).value);
            System.out.println(John.hands.get(1).value);
            System.out.println("For a score of: ");
            System.out.println(John.return_score());
            System.out.println("Here is the dealers face up card");
            System.out.println(Dealer.hands.get(0).value);

            //deciding to double down or not
            System.out.println("Double Down?(y or n)");
            input = scanner.nextLine();
            System.out.println("You entered: "+input);
            if (input.equals("y")){
                if(John.lose_chips(bet)){
                    bet *= 2;
                }else{
                    System.out.println("not enough chips, not doubling down");
                }

            }else{
                System.out.println("not doubling down");
            }
            //player decides to hit or not
            System.out.println("would you like to hit?(y or n)");
            input = scanner.nextLine();
            System.out.println("You entered: "+input);
            while(input.equals("y")){
                John.draw_from(d);
                System.out.println("You drew a");
                int last = John.hands.size()-1;
                System.out.println(John.hands.get(last).value);
                System.out.println("For a total score of: ");
                System.out.println(John.return_score());
                System.out.println("would you like to hit again?(y or n)");
                input = scanner.nextLine();
            }
            if(John.return_score() > 21){
                System.out.println("Sorry You busted...");
            }else if (John.return_score() == 21){
                System.out.println("BLACKJACK, you win triple!");
                John.win_chips(bet*3);
                bet =0;
            }else{
                System.out.println("Dealers hidden card was");
                System.out.println(Dealer.hands.get(1).value);
                System.out.println("For a score of:");
                System.out.println(Dealer.return_score());
                if(John.return_score() > Dealer.return_score()){
                    System.out.println("You win! Double your bet!");
                    John.win_chips(2*bet);
                    bet=0;
                }else if (John.return_score() < Dealer.return_score()){
                    System.out.println("You Lose, Sorry");


                }else if(John.return_score() == Dealer.return_score()){
                    System.out.println("Tie, you are returned your bet");
                    John.win_chips(bet);
                    bet=0;

                }



            }


        }else{
            System.out.println("Invalid Bet");
        }


        System.out.println("Your chip total is: "+ John.chips);

    }
}
/*
   * The Card class has members to represent the Suit, value and an array for the "weights" of the
   * card. In blackjack, the ace can either be a 1 or an 11 so we should Default to 11 and modify the
   value according to the rule when needed.
   * */
class Card{
    public String value;
    public int weight[];
    public String suit;
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
class Deck{
    public ArrayList<Card> cards;
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
        int count = 0;
        cards = new ArrayList(52);
        for (int i=0; i < 4;i++)
        {
            for (int j=0;j<13;j++) {
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
class Player{
    public String name;
    public int chips;
    public ArrayList<Card> hands;
    public boolean hasAce;
    public int numAce;
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
        if(t.value.equals("Ace")){
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
            temp += hands.get(i).weight[0];
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


Language Version:  JDK 9.0.1