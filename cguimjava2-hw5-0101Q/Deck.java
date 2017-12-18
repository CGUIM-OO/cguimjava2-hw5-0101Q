import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;//放會使用的牌
	private ArrayList<Card> usedCard;//放被使用過的牌
	//增(1)
	private ArrayList<Card> openCard; //（5pt）
	//存放此副牌中所有打開的牌，洗牌時要重置
	//END(1)
	public int nUsed=0;//表使用過的牌的數量，並給初始值為零;
	//TODO: Please implement the constructor (30 points)做了
	public Deck(int nDeck){//建立以"副為單的牌"
		nUsed=0;
		cards=new ArrayList<Card>();//實體化
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		//嘗試
		for(int deck=1;deck<=nDeck;deck++)//從第一副牌到所牌的副數
		{
			for (Card.Suit s : Card.Suit.values())//四種花色
			{	
				for(int rank=1;rank<=13;rank++)//13種大小
				{
					Card card=new Card(s,rank);//實體化一張牌
					cards.add(card);//將新實體出的牌放入cards
					
				}
			}
		}
		nUsed=0;//建好以"副"為單位的牌後，nUsed應為零
		shuffle();//因為練出來的牌此時為按一定問序排列，所以建好牌後要洗牌
	}	
	
	public void shuffle() {//洗牌，意指全部牌收回（不管發出去幾張），重新洗牌
		//增(2)
		//洗牌時記得重置private ArrayList openCard;（5pt）
		openCard=new ArrayList<Card>();	//實體化
		openCard.clear();//將其清空
		//END(2)
		//usedCard=new ArrayList<Card>();//實體化
		nUsed=0;
		int j=0;//用來存隨機的數字
		Random rnd = new Random();//用摨取隨機數字
		//此時usedCard會是空的，用來存被隨機取出的牌
			//while(!cards.isEmpty())//洗完牌後cards將會變空的，所以當還沒洗完牌時將會持續while
			while(nUsed!=cards.size()-1)//當所有牌都跑過一次才停止，減一是因為nUsed從零開始算，這時nUsed表洗過幾張牌
			{ 
				j =rnd.nextInt(cards.size()-nUsed);//範圍逐漸縮小
						 					 //第一次進入while時，j將等於由0~51(表共52張牌存於cards)
				//提示：隨機取一張牌牌，放到新的位置
				//設usedCard, nUsed
				//測試//usedCard.add(cards.get(j));//隨機取一張牌放到usedCard
				//測試//cards.remove(cards.get(j));//移除剛剛取出放到usedCard裡的牌(刪除後還有資料，後面的資料會自動向前補上)
				//測試//nUsed++;
				cards.add(cards.get(j));//將要取出的牌放到cards的最後
				cards.remove(cards.get(j));//將原本的(將被取出的牌)刪除，這樣cards裡的總牌數才能不變
				nUsed++;//每洗一張牌就加一
			}//執行完後，cards裡將沒有牌，洗好的牌將會在usedCard裡
			//測試//cards.addAll(usedCard);//把usedCard裡所有的牌放進cards裡(此時cards原本是空的)
			//測試//usedCard.removeAll(usedCard);//移除usedCard裡所有的牌(usedCard將會變空的)
			nUsed=0;
		//洗還牌後使用過的牌的數量nUsed(發出的牌的數量)，將會重新變為零
	}
	//增(3)
	//public Card getOneCard (boolean isOpened)
	//- 拿到一張牌，修改原有方法，加入isOpened參數，決定發出去的牌是開著還是蓋起來的，若是開著的牌，加入openCard。（5pt）
	//END(3)
	public Card getOneCard(boolean isOpened){//發一張牌出來，回傳Card object,加入isOpened參數，決定發出去的牌是開著還是蓋起來的
		//要檢查沒牌了 (牌都發完了) 怎麼辦？ shuffle() ！
		//– 發出去的牌紀錄在ArrayList<Card> usedCard
		//– 發了幾張牌？紀錄在private int nUsed=0
		//測試//System.out.println(nUsed);
		nUsed++;//把發出去的牌數的牌的數量記錄在nUsed
		usedCard=new ArrayList<Card>();
		Card card=new Card(cards.get(nUsed-1).getSuit(),cards.get(nUsed-1).getRank());//取一張牌，此張牌將被使用(被發出)
		usedCard.add(card);//將要被發出地牌紀錄在usedCard;
		if(isOpened=true)//如果要發的是明牌，isOpened=true
		{
			openCard.add(card);//將得到的牌存進明牌中
		}
		//把發出去的牌數的牌的數量記錄在nUsed
		//cards.remove(cards.get(0));//刪除發出去的牌
		//card.printCard();
		//將被發出去的牌記錄在usedCard
		if(nUsed==cards.size())//如果cards是空的表示牌被發完，要重新收牌&洗牌
		{
			shuffle();//洗牌
		}
		return card;
	}
	//增(4)
	public ArrayList getOpenedCard()//得到打開過的牌
	{	
		return openCard;//回傳此副牌中所有打開過的牌
	}
	//回傳此副牌中所有打開過的牌，意即openCard（5pt）
	//END(4)
	//TODO: Please implement the method to print all cards on screen (10 points)
	/*public  void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//TODO: please implement and reuse printCard method in Card class (5 points)
		int n=0;//n為一個用來使while可以運行的數,也用來取cards.get()所要得到的內容
		while(cards.size()>n)//n在此迴圈會每跑一次就加一,所以有幾張牌就會進行幾次
		{ 
			Card card=new Card(cards.get(n).getSuit(),cards.get(n).getRank());
			//實體化Card,帶入的為cards裡面所存花色(以數字表示)cards.get().getSuit()與數字(以數字表示)cards.get(n).getRank()
			card.printCard();//執行Card class 裡的printCard()將印出此牌
			n++;
		}
		
	}*/
	
	
	public ArrayList<Card> getAllCards(){
		return cards;
	}
}
