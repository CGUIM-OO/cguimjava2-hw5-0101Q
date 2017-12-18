import java.util.ArrayList;

public class Table {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub

	//}
	private final int MAXPLAYER = 4;//最多一張牌桌能坐幾個人
	private Deck Deck;//存放所有的牌
	private Player Player[];//存放所有的玩家
	private Dealer Dealer;//存放一個莊家
	private int[] pos_betArray;//存放每個玩家在一局下的注
	public Table( int nDeck){//在HW5.java內有實體化Table Class，可參考。
	//在此Constructor中，請將Deck class實體化，並存入上述新增的Deck變數(instance field)
		Deck=new Deck(nDeck);//實體化並存入Deck;
		//- 新增 請初始化上述新增型別為Player[]的變數(instance field)，宣告一個長度是MAXPLAYER 的Player array
		Player=new Player[MAXPLAYER];//宣告一個長度是MAXPLAYER 的Player array
	}
	public void set_player(int pos, Player p){
	//將Player放到牌桌上 (意即放到型別為Player[]的變數(instance field)中，為setter)，pos為牌桌位置，最多MAXPLAYER人，p則為Player instance。	
		Player[pos]=p;//將Player放到牌桌上
	}
	public Player[] get_player(){
		//回傳所有在牌桌上的player，意即回傳型別為Player[]的變數(instance field)變數，為getter
		return Player;//回傳所有在牌桌上的player
	}
	public void set_dealer(Dealer d){
		//將Dealer放到牌桌上 (意即將Dealer放到 型別為Dealer 的變數(instance field) 中，為變數之setter)。
		Dealer=d;//將Dealer放到牌桌上
	}
	public Card get_face_up_card_of_dealer(){
		//回傳dealer打開的那張牌，也就是第二張牌
		//Card card=new Card(Dealer.getOneRoundCard().get(1).getSuit(),Dealer.getOneRoundCard().get(1).getRank());//實體化要打開的那張牌，也就是第二張牌
		return Dealer.getOneRoundCard().get(1);//回傳dealer打開的那張牌                                                      															//所以用get(1)
	}
	private void ask_each_player_about_bets(){
		//請每個玩家打招呼 (提示 say_hello())
		pos_betArray=new int[MAXPLAYER];//宣告陣列長度與玩家人數相同
		for(int i=0;i<MAXPLAYER;i++){//依序從Player[0](第一位玩家)到最後一位
			Player[i].say_hello();//請每個玩家打招呼 
			pos_betArray[i]=Player[i].make_bet();//請每個玩家下注並將下注依照玩家位置依序存入pos_betArray[i]
			//(提示 make_bet())
			//每個玩家下的注要存在pos_betArray供之後使用
		}
		
		
	}
	private void distribute_cards_to_dealer_and_players(){//發牌給玩家跟莊家
		//發牌給玩家跟莊家，先發兩張打開的牌給玩家，再一張蓋著的牌，以及一張打開的牌給莊家。(提示: setOneRoundCard())
		for(int i=0;i<MAXPLAYER;i++){//依序安照玩家位置發牌
			ArrayList<Card> b=new ArrayList<Card>();//實體化用來暫存的arraylist,用來存每個人一局的牌，輪到下一位玩家時b要與前一位玩家的不同，所以在回圈內實體化
			b.add(Deck.getOneCard(true));//發第一張牌，存到b，發的牌為打開的
			b.add(Deck.getOneCard(true));//發第二張牌，存到b，發的牌為打開的
			Player[i].setOneRoundCard(b);//將此玩家的兩張牌(在b裡)存到，此玩家的此局牌中
		}
		ArrayList<Card> b=new ArrayList<Card>();//實體化用來暫存的arraylist,用來存莊家一局的牌
		b.add(Deck.getOneCard(false));//發第一張牌，存到b,發的牌為蓋著的
		//Card card=new Card(Deck.getOneCard(true).getSuit(),Deck.getOneCard(true).getRank());//發第二張牌，此牌為打開的，並實體化它
		b.add(Deck.getOneCard(true));//將第二張牌存到b
		Dealer.setOneRoundCard(b);//將莊家的兩張牌(在b裡)存到，莊家此局牌中
		System.out.print("Dealer's face up card is ");//欲印出莊家打開的那張牌
		get_face_up_card_of_dealer().printCard();//將已被實體化的那張莊家的打開的牌印出
		//發牌給莊家後，在畫面上印出莊家打開的牌"Dealer's face up card is " (提示: printCard())
	}
	private void ask_each_player_about_hits(){//問每個玩家要不要牌 (提示: hit_me(Table tbl))
		for(int i=0;i<MAXPLAYER;i++){//按位置順序依序問每個玩家要不要牌
			System.out.println(Player[i].getName()+"'s Cards now:");//欲先將被詢問是否要牌的玩家一開始的兩張牌印出
			//for(Card c : Player[i].getOneRoundCard()){//依照牌得到的順序
				//c.printCard();//印出目前所有的牌
			//}
			Player[i].printAllCard();//印出目前所有的牌
			boolean hit=false;//hit是用來判斷是否要牌的，如果要牌hit=true，如果不要要牌hit=false,一開始讓hit=false			
			do{//先執行//請參照HW4.java，使用do while迴圈詢問各個玩家
				hit=Player[i].hit_me(this); //this，呼叫hit_me方法並傳入參數，可以確認是否要牌
				if(hit)//如果要牌(hit=true)
				{
					Player[i].getOneRoundCard().add(Deck.getOneCard(true));//發一張打開的牌，並存入此玩家這局的牌中
					//Player[i].setOneRoundCard(Player[i].getOneRoundCard());
					System.out.print("Hit! ");//印出，表示要牌
					System.out.println(Player[i].getName()+"'s Cards now:");//並印出要完牌後此局所有的牌
					//for(Card c : Player[i].getOneRoundCard())//依照牌得到的順序
					//{
					//	c.printCard();//印出目前所有的牌
					//}
					Player[i].printAllCard();//印出目前所有的牌
				}
				else{//如果不要牌(hit=false)
					//System.out.println(Player[i].getName()+"'s Cards now:");
					//for(Card c : Player[i].getOneRoundCard()){
						//c.printCard();}
					//System.out.println(Player[i].getName()+", Pass hit!");
					System.out.println(Player[i].getName()+" Pass hit!");//印出表此玩家不要牌
					//System.out.println(Player[i].getName()+", Final Card:");
					//System.out.println(Player[i].getName()+"'s hit is over!");//印出表此玩家結束了他的要牌(即不再動作)
					//for(Card c :Player[i].getOneRoundCard()){
						//c.printCard();
					//}
					//System.out.println(Player[i].getName()+"'s hit is over!");
					}
			}while(hit);//判斷是否還可能要牌，hit=true表還可能，hit=false表不再要牌
			}
		//詢問順序: 按照加入牌局的順序而定 (位置)
		//如果玩家要牌，請在畫面上印出"Hit! "+ 玩家的名字+ "’s cards now: "，並把玩家要牌後的完整牌印出。最後將新的牌用setOneRoundCard()設定回玩家物件。
		//如果爆了，請不要再問玩家是否要牌
		//如果玩家不要牌了，請在畫面上印出 玩家的名字+"Pass hit!"
	}
	private void ask_dealer_about_hits(){//詢問莊家是否要牌
		//詢問莊家是否要牌，完成後，印出"Dealer's hit is over!"
		boolean hit=false;//hit是用來判斷是否要牌的，如果要牌hit=true，如果不要要牌hit=false,一開始讓hit=false	
		do{//先執行//請參照HW4.java，使用do while迴圈詢問各個玩家
			hit=Dealer.hit_me(this); //this，呼叫hit_me方法並傳入參數，可以確認是否要牌
			if(hit){//如果要牌(hit=true)
				Dealer.getOneRoundCard().add(Deck.getOneCard(true));//發一張打開的牌，並存入莊家這局的牌中
				//Dealer.setOneRoundCard(Dealer.getOneRoundCard());
				//System.out.print("Hit! ");
				//System.out.println(Player[i].getName()+"'s Cards now:");
				//for(Card c : Dealer.getOneRoundCard()){
					//c.printCard();
				//}
			}
			else{//如果不要牌(hit=false)
				//System.out.println(Player[i].getName()+", Pass hit!");
				//System.out.println("Dealer'sFinal Card:");
				//for(Card c :Dealer.getOneRoundCard()){
					//c.printCard();
				//}
				System.out.println("Dealer's hit is over!");//印出表莊家結束了他的要牌(即不再動作)
			}
		}while(hit);//判斷是否還可能要牌，hit=true表還可能，hit=false表不再要牌//請參照HW4.java，使用do while迴圈詢問各個玩家
	}
	private void calculate_chips(){//計算籌碼
		System.out.println("Dealer's card value is "+Dealer.getTotalValue()+" ,Cards:");//印出表告知莊家的點數總和
		//for(Card c:Dealer.getOneRoundCard()){//依照牌得到的順序
			//c.printCard();//印出目前所有的牌
		//}
		Dealer.printAllCard();
		//印出莊家的點數和牌"Dealer's card value is "+總點數+" ,Cards:"+牌們 (提示: printAllCard())
		//莊家跟每一個玩家的牌比
		for(int i=0;i<MAXPLAYER;i++)//按位置順序依序比莊家跟每一個玩家的牌
		{
			System.out.println(Player[i].getName()+"'s Cards:");//印出表要告知要跟莊家比牌的玩家的牌
			//for(Card c:Player[i].getOneRoundCard()){//依照牌得到的順序
				//c.printCard();//印出目前所有的牌
			//}
			Player[i].printAllCard();
			System.out.print(Player[i].getName()+" card value is "+Player[i].getTotalValue());//印出表告知要跟莊家比牌的玩家的總點數
			if(Dealer.getTotalValue()>21 && Player[i].getTotalValue()>21){//如果兩人皆點數皆爆的情況
				System.out.println(",chips have no change! The Chips now is: "+Player[i].get_current_chip());//印出表示沒勝負的訊息//(提示: get_current_chips()));
			}else if(Dealer.getTotalValue()<=21&&Player[i].getTotalValue()>21){//玩家點數爆掉而莊家點數沒爆的情況，表示莊家贏，玩家輸
				Player[i].increaseChips(-pos_betArray[i]);//玩家的籌碼因為輸而要依所下的注丟失籌碼
				System.out.println(", Loss "+pos_betArray[i]+" Chips, the Chips now is: "+Player[i].get_current_chip());//印出表示玩家輸並且將勝負後的籌碼也印出
			}else if(Dealer.getTotalValue()>21&&Player[i].getTotalValue()<=21){//莊家點數爆掉而玩家點數沒爆的情形，表示玩家贏，莊家輸
				Player[i].increaseChips(pos_betArray[i]);//玩家的籌碼因為贏而依所下的注獲得籌碼
				System.out.println(",Get "+pos_betArray[i]+" Chips, the Chips now is: "+Player[i].get_current_chip());//印出表示玩家贏並且將勝負後的籌碼也印出
			}else if(Dealer.getTotalValue()>Player[i].getTotalValue()&&Dealer.getTotalValue()<=21){//兩者的點數皆沒爆，但莊家點數大於玩家點數的情懬，表示莊家贏，玩家輸
				Player[i].increaseChips(-pos_betArray[i]);//玩家的籌碼因為輸而要依所下的注丟失籌碼
				System.out.println(", Loss "+pos_betArray[i]+" Chips, the Chips now is: "+Player[i].get_current_chip());//印出表示玩家輸並且將勝負後的籌碼也印出
			}else if(Dealer.getTotalValue()<Player[i].getTotalValue()&&Player[i].getTotalValue()<=21){//兩者的點數皆沒爆。但玩家點數大於莊家點數的情況，表示玩家贏，莊家輸
				Player[i].increaseChips(pos_betArray[i]);//玩家的籌碼因為贏而依所下的注獲得籌碼
				System.out.println(",Get "+pos_betArray[i]+" Chips, the Chips now is: "+Player[i].get_current_chip());//印出表示玩家贏並且將勝負後的籌碼也印出
			}else{//兩者點數皆沒爆，但點數一樣的情形
				System.out.println(",chips have no change! The Chips now is: "+Player[i].get_current_chip());//印出表示沒勝負的訊息
			}	
		}
		//針對每個玩家，先印出 玩家姓名+" card value is "+玩家總點數
		//看誰贏，要是莊家贏，把玩家籌碼沒收，印出", Loss "+下注籌碼數+" Chips, the Chips now is: "+玩家最新籌碼數(提示: get_current_chips())
		//要是莊家輸，則賠玩家與下注籌碼相符之籌碼，印出",Get "+下注籌碼數+" Chips, the Chips now is: "+玩家最新籌碼數(提示: get_current_chips())
		//不輸不贏，印出",chips have no change! The Chips now is: "+玩家最新籌碼數(提示: get_current_chips())
	}
	public int[] get_players_bet(){	//獲得玩家所下的注
		return pos_betArray ;//回傳pos_betArray
	}
	//新增一method play()如下，使用方法見HW5.java
	public void play(){//玩的方法//依序呼叫
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	//可依需求增加field和method，但新增加的field和method一定為private層級
	//Table.java所需field (除static field外全為private)與method，若無寫明變數名稱，表示可自行取名
}
