import java.util.LinkedList;
import java.util.Collections;

public class Deck{
	private LinkedList<Card> cardList;
	
	public Deck(int nbBox){
		cardList = new LinkedList<Card>();
		for(int i=0;i<nbBox;i++){
			for(Color c : Color.values()){
				for(Value v: Value.values()){
					cardList.add(new Card(v,c));
					}
				}
			}
			Collections.shuffle(cardList);
		}
	public String toString(){
		String msg="";
		for(int i=0;i<cardList.size();i++){
			msg+=cardList.get(i);
			}
				return ("["+msg+"]");
			}
			
			
	public Card draw(){
		Card mycard=cardList.pollFirst();
		return mycard;
		}
}
