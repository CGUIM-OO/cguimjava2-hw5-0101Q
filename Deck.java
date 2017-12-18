import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;//��|�ϥΪ��P
	private ArrayList<Card> usedCard;//��Q�ϥιL���P
	//�W(1)
	private ArrayList<Card> openCard; //�]5pt�^
	//�s�񦹰ƵP���Ҧ����}���P�A�~�P�ɭn���m
	//END(1)
	public int nUsed=0;//��ϥιL���P���ƶq�A�õ���l�Ȭ��s;
	//TODO: Please implement the constructor (30 points)���F
	public Deck(int nDeck){//�إߥH"�Ƭ��檺�P"
		nUsed=0;
		cards=new ArrayList<Card>();//�����
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		//����
		for(int deck=1;deck<=nDeck;deck++)//�q�Ĥ@�ƵP��ҵP���Ƽ�
		{
			for (Card.Suit s : Card.Suit.values())//�|�ت��
			{	
				for(int rank=1;rank<=13;rank++)//13�ؤj�p
				{
					Card card=new Card(s,rank);//����Ƥ@�i�P
					cards.add(card);//�N�s����X���P��Jcards
					
				}
			}
		}
		nUsed=0;//�ئn�H"��"����쪺�P��AnUsed�����s
		shuffle();//�]���m�X�Ӫ��P���ɬ����@�w�ݧǱƦC�A�ҥH�ئn�P��n�~�P
	}	
	
	public void shuffle() {//�~�P�A�N�������P���^�]���޵o�X�h�X�i�^�A���s�~�P
		//�W(2)
		//�~�P�ɰO�o���mprivate ArrayList openCard;�]5pt�^
		openCard=new ArrayList<Card>();	//�����
		openCard.clear();//�N��M��
		//END(2)
		//usedCard=new ArrayList<Card>();//�����
		nUsed=0;
		int j=0;//�ΨӦs�H�����Ʀr
		Random rnd = new Random();//��庨��H���Ʀr
		//����usedCard�|�O�Ū��A�ΨӦs�Q�H�����X���P
			//while(!cards.isEmpty())//�~���P��cards�N�|�ܪŪ��A�ҥH���٨S�~���P�ɱN�|����while
			while(nUsed!=cards.size()-1)//��Ҧ��P���]�L�@���~����A��@�O�]��nUsed�q�s�}�l��A�o��nUsed��~�L�X�i�P
			{ 
				j =rnd.nextInt(cards.size()-nUsed);//�d��v���Y�p
						 					 //�Ĥ@���i�Jwhile�ɡAj�N�����0~51(��@52�i�P�s��cards)
				//���ܡG�H�����@�i�P�P�A���s����m
				//�]usedCard, nUsed
				//����//usedCard.add(cards.get(j));//�H�����@�i�P���usedCard
				//����//cards.remove(cards.get(j));//���������X���usedCard�̪��P(�R�����٦���ơA�᭱����Ʒ|�۰ʦV�e�ɤW)
				//����//nUsed++;
				cards.add(cards.get(j));//�N�n���X���P���cards���̫�
				cards.remove(cards.get(j));//�N�쥻��(�N�Q���X���P)�R���A�o��cards�̪��`�P�Ƥ~�ण��
				nUsed++;//�C�~�@�i�P�N�[�@
			}//���槹��Acards�̱N�S���P�A�~�n���P�N�|�busedCard��
			//����//cards.addAll(usedCard);//��usedCard�̩Ҧ����P��icards��(����cards�쥻�O�Ū�)
			//����//usedCard.removeAll(usedCard);//����usedCard�̩Ҧ����P(usedCard�N�|�ܪŪ�)
			nUsed=0;
		//�~�ٵP��ϥιL���P���ƶqnUsed(�o�X���P���ƶq)�A�N�|���s�ܬ��s
	}
	//�W(3)
	//public Card getOneCard (boolean isOpened)
	//- ����@�i�P�A�ק�즳��k�A�[�JisOpened�ѼơA�M�w�o�X�h���P�O�}���٬O�\�_�Ӫ��A�Y�O�}�۪��P�A�[�JopenCard�C�]5pt�^
	//END(3)
	public Card getOneCard(boolean isOpened){//�o�@�i�P�X�ӡA�^��Card object,�[�JisOpened�ѼơA�M�w�o�X�h���P�O�}���٬O�\�_�Ӫ�
		//�n�ˬd�S�P�F (�P���o���F) ����H shuffle() �I
		//�V �o�X�h���P�����bArrayList<Card> usedCard
		//�V �o�F�X�i�P�H�����bprivate int nUsed=0
		//����//System.out.println(nUsed);
		nUsed++;//��o�X�h���P�ƪ��P���ƶq�O���bnUsed
		usedCard=new ArrayList<Card>();
		Card card=new Card(cards.get(nUsed-1).getSuit(),cards.get(nUsed-1).getRank());//���@�i�P�A���i�P�N�Q�ϥ�(�Q�o�X)
		usedCard.add(card);//�N�n�Q�o�X�a�P�����busedCard;
		if(isOpened=true)//�p�G�n�o���O���P�AisOpened=true
		{
			openCard.add(card);//�N�o�쪺�P�s�i���P��
		}
		//��o�X�h���P�ƪ��P���ƶq�O���bnUsed
		//cards.remove(cards.get(0));//�R���o�X�h���P
		//card.printCard();
		//�N�Q�o�X�h���P�O���busedCard
		if(nUsed==cards.size())//�p�Gcards�O�Ū���ܵP�Q�o���A�n���s���P&�~�P
		{
			shuffle();//�~�P
		}
		return card;
	}
	//�W(4)
	public ArrayList getOpenedCard()//�o�쥴�}�L���P
	{	
		return openCard;//�^�Ǧ��ƵP���Ҧ����}�L���P
	}
	//�^�Ǧ��ƵP���Ҧ����}�L���P�A�N�YopenCard�]5pt�^
	//END(4)
	//TODO: Please implement the method to print all cards on screen (10 points)
	/*public  void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//TODO: please implement and reuse printCard method in Card class (5 points)
		int n=0;//n���@�ӥΨӨ�while�i�H�B�檺��,�]�ΨӨ�cards.get()�ҭn�o�쪺���e
		while(cards.size()>n)//n�b���j��|�C�]�@���N�[�@,�ҥH���X�i�P�N�|�i��X��
		{ 
			Card card=new Card(cards.get(n).getSuit(),cards.get(n).getRank());
			//�����Card,�a�J����cards�̭��Ҧs���(�H�Ʀr���)cards.get().getSuit()�P�Ʀr(�H�Ʀr���)cards.get(n).getRank()
			card.printCard();//����Card class �̪�printCard()�N�L�X���P
			n++;
		}
		
	}*/
	
	
	public ArrayList<Card> getAllCards(){
		return cards;
	}
}
