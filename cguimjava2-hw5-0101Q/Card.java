

public class Card
 {	
	enum Suit{Club,Diamond,Heart,Spade};
	//private int suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; //1~13
	Suit suit;
	public Card(Suit s, int value){
		suit=s;
		rank=value;
	}
	//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank)
	public void printCard(){
		//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
		String []R={"Ace","2","3","4","5","6","7","8","9","10","J","Q","K"};//��P(�Ʀr)�j�p���r��}�C
		System.out.println(getSuit().name()+" "+R[getRank()-1]);//�L�X���εP���j�p
	}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
}
