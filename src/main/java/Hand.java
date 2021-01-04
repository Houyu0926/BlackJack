import java.util.LinkedList;
import java.util.List;

public class Hand{
			private LinkedList<Card> hand;
			
	public Hand(){
		hand  = new LinkedList<Card>();
		}
		
	@Override
	public String toString(){
		List<Integer> sum = count();
		return (hand+":"+sum);
		}
	public void add(Card card){
		hand.add(card);
		}
	public void clear(){
			hand.clear();
			}				
	public List<Integer> count(){
		List<Integer> list = new LinkedList<Integer>();
		list.add(0);
		int val,size_hand,size_list;
		size_hand=hand.size();
		for(int i=0;i<size_hand;i++){
			size_list=list.size();
			for(int j=0;j<size_list;j++){
				val=list.get(j);
				list.set(j, val + hand.get(i).getPoints());	
				if(hand.get(i).getPoints()==1){
					list.add(val+11);
					}
				}
			}	
		return list;
	}
	public int best(){
		List<Integer> list = count();
		int best=list.get(0);
		for(int i=1;i<list.size();i++){			
			if(list.get(i)<=21 && best<list.get(i)){
				best=list.get(i);
				}
			}
			return best;
		}
	public List<Card> getCardList(){
		return this.hand;
	}
		
}
