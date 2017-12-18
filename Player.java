import java.util.ArrayList;

public class Player {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub

	//}
	private String name; //(5pt)
	//���a�m�W
	private int chips; //(5pt)
	//���a�����w�X
	private int bet; //(5pt)
	//���a�����U�`���w�X
	private ArrayList<Card> oneRoundCard;// (5pt)
	//���P�����d
	public Player(String name, int chips){//�ت��a�W��&�w�X
		this.name=name;//�N�W�r�s��name
		this.chips=chips;//�N�w�X�s��chips
		//(5pt)
	}
	//Player constructor�A�s�WPlayer����ɡA�ݭn�m�W�B�֦����w�X����ӰѼ�
	public String getName()//�o��W�r
	{
		return name;//�^�ǦW�r
	}//(5pt)
	//name��getter
	//return name;
	public int makeBet()//�U�`
	{	
		if(getCurrentChips()==0)//�p�G���a�{���w�X=0,��ܨS���F�A����A�~��U�`
		{
			System.out.println("�S���F�A����A�~��U�`");//�o�X�����T��
			return bet=0;//
		}
		else//���٦����A�i�H�~��U�`
		{
			bet=1;//�򥻤U�`�G�@��1��
			return bet;//�^�ǹw�p�U�`���w�X
		}
		//bet=1;//�򥻤U�`�G�@��1��
		//return bet;//�^�ǹw�p�U�`���w�X
	} // (5pt)
	//�U�`�A�^�ǹw�p�U�`���w�X
	//�򥻤U�`�G�@��1��
	//�p
	//bet=1;
	//return bet;
	//�`�N�G�n�ˬd�O�_�٦����A�S���F�N����A�~��U�`
	public void setOneRoundCard(ArrayList cards)//�o�즹���ұo�쪺�d
	{
		oneRoundCard=cards;//�N�ұo�쪺�d�s�JoneRoundCard
	}// (5pt)
	//�]�w���P���ұo�쪺�d setter
	//oneRoundCard=cards;
	public boolean hitMe()//�O�_�n�P
	{	
		if(getTotalValue()<=16)//�p�G�`�I��(getTotalValue())<=16�N�~��n�P
		{
			return true;//�n�n�P�N�^��true
		}
		else//�p�G�`�I��(getTotalValue())>=17�N����n�P
		{
			return false;//�n����n�P�Ϧ^��false
		}
		
	} //(10pt)
	//�O�_�n�P�A�O�^��true�A���A�n�P�h�^��false
	//�򥻰Ѧұ���G16�I�H�U�n�P�A17�I�H�W���n�P
	//���ܡG��oneRoundCard�Ӻ�
	public int getTotalValue()//���P���ұo���d�I�ƥ[�`
	{	
		int sum=0;//�]�ܼƨӦs���P���ұo���d�I���`�M
		for(Card c:oneRoundCard)//�N�������P�����Ǩ��@�M
		{
			if(c.getRank()==11||c.getRank()==12||c.getRank()==13)//�p�G�P��11(J)�A12(Q)�A13(K)
			{	
				sum=sum+10;//�o�T�i�P�b�C�����Q�ⰵ�Q�I�A�ҥH�`�M�[10
			}
			else if(c.getRank()==1)//�P��1(Ace)�ɡA�i��11��1
			{
				if((sum+11)==21)//�p�G��Ace��11�ɡA�`�M����21�A�N������11
				{
					sum=sum+11;//�`�M�[11
				}
				else//�p�GAce��11�ɤ����n��21�I�A�N������1
				{
					sum=sum+1;//�`�M�[1
				}
			}
			else//��L��P�I�Ƭ�2~10��
			{
				sum=sum+c.getRank();//�P���I�Ƭ�2~10�ɡA����Ʀr�i��[�`
			}
		}
		
		return sum;////�^�Ǧ��P���ұo���d�I�ƥ[�`
	} //(5pt)
	//�^�Ǧ��P���ұo���d�I�ƥ[�`
	public int getCurrentChips()
	{
		return chips;
	} //(5pt)
	//�^�Ǫ��a�{���w�X
	public void increaseChips (int diff)//���a�w�X�ܰʡAdiff�Ƕi�Ӫ��ƭȤw�q�n���t
	{
		chips=chips+diff;//Ĺ�̱N�|+(����diff)�A��̱N�|+(�t��diff)
	} //(5pt)
	//���a�w�X�ܰʡAsetter
	public void sayHello()//���aSay Hello
	{
		System.out.println("Hello, I am " + name + ".");//���a�ԭz��W�r
		System.out.println("I have " + chips + " chips.");//���a�ԭz���w�X

	}//(5pt)
	//���aSay Hello
	
}
