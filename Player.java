import java.util.ArrayList;

public class Player {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub

	//}
	private String name; //(5pt)
	//玩家姓名
	private int chips; //(5pt)
	//玩家有的籌碼
	private int bet; //(5pt)
	//玩家此局下注的籌碼
	private ArrayList<Card> oneRoundCard;// (5pt)
	//此牌局的卡
	public Player(String name, int chips){//建玩家名稱&籌碼
		this.name=name;//將名字存到name
		this.chips=chips;//將籌碼存到chips
		//(5pt)
	}
	//Player constructor，新增Player物件時，需要姓名、擁有的籌碼等兩個參數
	public String getName()//得到名字
	{
		return name;//回傳名字
	}//(5pt)
	//name的getter
	//return name;
	public int makeBet()//下注
	{	
		if(getCurrentChips()==0)//如果玩家現有籌碼=0,表示沒錢了，不能再繼續下注
		{
			System.out.println("沒錢了，不能再繼續下注");//發出提醒訊息
			return bet=0;//
		}
		else//表還有錢，可以繼續下注
		{
			bet=1;//基本下注：一次1元
			return bet;//回傳預計下注的籌碼
		}
		//bet=1;//基本下注：一次1元
		//return bet;//回傳預計下注的籌碼
	} // (5pt)
	//下注，回傳預計下注的籌碼
	//基本下注：一次1元
	//如
	//bet=1;
	//return bet;
	//注意：要檢查是否還有錢，沒錢了就不能再繼續下注
	public void setOneRoundCard(ArrayList cards)//得到此局所得到的卡
	{
		oneRoundCard=cards;//將所得到的卡存入oneRoundCard
	}// (5pt)
	//設定此牌局所得到的卡 setter
	//oneRoundCard=cards;
	public boolean hitMe()//是否要牌
	{	
		if(getTotalValue()<=16)//如果總點數(getTotalValue())<=16就繼續要牌
		{
			return true;//要要牌就回傳true
		}
		else//如果總點數(getTotalValue())>=17就停止要牌
		{
			return false;//要停止要牌救回傳false
		}
		
	} //(10pt)
	//是否要牌，是回傳true，不再要牌則回傳false
	//基本參考條件：16點以下要牌，17點以上不要牌
	//提示：用oneRoundCard來算
	public int getTotalValue()//此牌局所得的卡點數加總
	{	
		int sum=0;//設變數來存此牌局所得的卡點數總和
		for(Card c:oneRoundCard)//將此局的牌按順序走一遍
		{
			if(c.getRank()==11||c.getRank()==12||c.getRank()==13)//如果牌為11(J)，12(Q)，13(K)
			{	
				sum=sum+10;//這三張牌在遊戲中被算做十點，所以總和加10
			}
			else if(c.getRank()==1)//牌為1(Ace)時，可當11或1
			{
				if((sum+11)==21)//如果把Ace當11時，總和等於21，就讓它當11
				{
					sum=sum+11;//總和加11
				}
				else//如果Ace當11時不能剛好到21點，就讓它當1
				{
					sum=sum+1;//總和加1
				}
			}
			else//其他當牌點數為2~10時
			{
				sum=sum+c.getRank();//牌的點數為2~10時，按其數字進行加總
			}
		}
		
		return sum;////回傳此牌局所得的卡點數加總
	} //(5pt)
	//回傳此牌局所得的卡點數加總
	public int getCurrentChips()
	{
		return chips;
	} //(5pt)
	//回傳玩家現有籌碼
	public void increaseChips (int diff)//玩家籌碼變動，diff傳進來的數值已訂好正負
	{
		chips=chips+diff;//贏者將會+(正的diff)，輸者將會+(負的diff)
	} //(5pt)
	//玩家籌碼變動，setter
	public void sayHello()//玩家Say Hello
	{
		System.out.println("Hello, I am " + name + ".");//玩家敘述其名字
		System.out.println("I have " + chips + " chips.");//玩家敘述其籌碼

	}//(5pt)
	//玩家Say Hello
	
}
