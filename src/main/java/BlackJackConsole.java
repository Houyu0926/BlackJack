import java.lang.Exception;
import java.util.*;
import java.util.Scanner;

public class BlackJackConsole {
	static String choice;
	public BlackJackConsole() {
		System.out.println("Welcome to the BlackJacktable. Let's play!");
		BlackJack blackjack =new BlackJack();
		blackjack.reset();
		System.out.println("The bank draw : "+blackjack.getBankHandString());
		System.out.println("You draw : "+blackjack.getPlayerHandString());
		Scanner scan =new Scanner(System.in);	
		do{
			System.out.println("Do you want another card ? [y/n]");
			choice = scan.next();
			switch(choice) {
				case "n":
				System.out.print("\n");
				break;
				case "y":
				blackjack.playerDrawAnotherCard();
				System.out.println("Your new hand : "+blackjack.getPlayerHandString());
				if(blackjack.getPlayerBest() > 21){
					System.out.println("Bank win!");
					System.exit(-1);
					}
				break;
				default : 
				System.out.println("Unknown command.");	}
				}while(!choice.equals("n"));
				blackjack.bankLastTurn();
				System.out.println("Player best :"+blackjack.getPlayerBest());
				
				System.out.println("The bank draw cards. New hand :"+blackjack.getBankHandString());	
				System.out.println("Bank best : "+blackjack.getBankBest());
				
				//System.out.println("Bank win!"+blackjack.isBankWinner());
				if(blackjack.isBankWinner() && !blackjack.isPlayerWinner()){
					System.out.println("Bank win!");
					}else if(!blackjack.isBankWinner() && blackjack.isPlayerWinner()){
						System.out.println("Player win!");
						}else{System.out.println("Draw!");}
				
		/*EXO 3
		
		Card[] tab = {new Card(Value.TWO, Color.HEART),new Card(Value.JACK, Color.SPADE) };
		for(Card c : tab) {//For each card
			System.out.println("This card is a "+c+" worth "+c.getPoints()+" points");
			System.out.print("It's a");
			switch(c.getColorSymbole()) {//OkfromJava1.7
				case"\u2665": System.out.print(" heart ");
				break;
				case"\u2660": System.out.print(" spade ");
				break;
				case"\u2663": System.out.print(" club ");
				break;
				case"\u2666": System.out.print(" diamond ");
				break;}
				if(c.getValueSymbole().matches("[JQK]")) {
					//Is the value symbole a J or a Q or a K?
					System.out.println("and a face!");
					}else{
						System.out.println("and it's not a face.");
						}
					}*/
					
					
					/*EXO 5 6 7
					
					Deck deck = new Deck(2);
					Hand hand = new Hand();
                    System.out.println("Here is the deck "+ deck.toString()+"\n");
                    System.out.println("Your hand is currently : "+ hand);
                    for(int i = 0 ; i < 111 ; i ++) { // For each card
					try{
					hand.add(deck.draw());
					
					Card c = deck.draw();
					System.out.println("This card is a "+c+ " worth "+c.getPoints()+ " points");
					System.out.print("It's a");
			        switch(c.getColorSymbole()) {//Ok from Java1.7
				          case"\u2665": System.out.print(" heart ");
				          break;
						  case"\u2660": System.out.print(" spade ");
			      	      break;
						  case"\u2663": System.out.print(" club ");
						  break;
						  case"\u2666": System.out.print(" diamond ");
						  break;}
						  if(c.getValueSymbole().matches("[JQK]")) {
								//Is the value symbole a J or a Q or a K?
								System.out.println("and a face!");
								}else{
									System.out.println("and it's not a face.");
									}
									} catch (EmptyDeckException ex) {
										System.err.println(ex.getMessage());
										System.exit(-1);
					                }
					      }
					      System.out.println("Your hand is currently : "+ hand);
					      System.out.println("The best score is: "+hand.best());
					      hand.clear();
					      System.out.println("Your hand is currently : "+ hand);*/
				}
		
		public static void main(String[] args) {
			new BlackJackConsole();
			}
}
