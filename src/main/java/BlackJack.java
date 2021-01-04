import java.util.List;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;

public class BlackJack{

private Deck deck;
private Hand playerHand;
private Hand bankHand;
private boolean gameFinished;

public BlackJack(){
	deck = new Deck(4);
    playerHand = new Hand();
    bankHand = new Hand();
    gameFinished=false;
	}
	public void reset(){
		try{playerHand.clear();
		bankHand.clear();
		gameFinished=false;
		bankHand.add(deck.draw());
		for(int i=0;i<2;i++){
			playerHand.add(deck.draw());
			}
			}catch (EmptyDeckException ex){
				System.err.println(ex.getMessage());
				System.exit(-1);
				}
	}	
	public String getPlayerHandString(){
		return playerHand.toString();
	}
	public String getBankHandString(){
		return bankHand.toString();		
	}
	public int getPlayerBest(){
		return playerHand.best();
	}
	public int getBankBest(){
		return bankHand.best();				
	}
	public boolean isPlayerWinner(){
		if(playerHand.best()<=21 && (playerHand.best()>bankHand.best() || bankHand.best()>21)){
			return true;
			}else{ 
				return false;
			}
		}
	public boolean isBankWinner(){
		if(bankHand.best()<=21 && (bankHand.best()>playerHand.best() || playerHand.best()>21)){
			return true;
			}else{ 
				return false;
			}
		}
	
	public boolean isGameFinished(){
		if(gameFinished==true){
			return true;
			}else{
				return false;
				}
		}
	
	public void playerDrawAnotherCard(){
		try{
			if(gameFinished==false){
				playerHand.add(deck.draw());
				if(playerHand.best()>21){
					gameFinished=true;
					}
				}
		}catch (EmptyDeckException ex){
			System.err.println(ex.getMessage());
			System.exit(-1);
		}
		}
	
	public void bankLastTurn() {
		try{
			int size_playerHand=playerHand.getCardList().size();
			int size_bankHand=bankHand.getCardList().size();
			if(gameFinished==false && playerHand.best()<=21 && bankHand.best()<=21){
				while(size_bankHand < size_playerHand && bankHand.best() < playerHand.best()){
					bankHand.add(deck.draw());
					size_bankHand++;
					}
				}	
			gameFinished=true;
		}catch (EmptyDeckException ex){
			System.err.println(ex.getMessage());
				System.exit(-1);
		}
		}
		
		public List<Card> getPlayerCardList(){
			
			List<Card> originalList = playerHand.getCardList();
			List<Card> copyList1 = new LinkedList<Card>(originalList); // Copy !
			return copyList1;	
		}
		
		public List<Card> getBankCardlist(){
			List<Card> originalList = bankHand.getCardList();
			List<Card> copyList2 = new LinkedList<Card>(originalList); // Copy !
			return copyList2;	
			}

}
