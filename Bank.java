import java.util.HashSet;
import java.util.Set;

public class Bank {
	
	Set<Cards> deeds = new HashSet<Cards>();
	int cashReceived;
	int cashGranted;

	public Bank() {
		Cards deed1 = new Cards(9,0);
		deeds.add(deed1);
		Cards deed2 = new Cards(7,0);
		deeds.add(deed2);
		Cards deed3 = new Cards(6,0);
		deeds.add(deed3);
		Cards deed4 = new Cards(5,0);
		deeds.add(deed4);
		Cards deed5 = new Cards(4,0);
		deeds.add(deed5);
		Cards deed6 = new Cards(2,0);
		deeds.add(deed6);
		Cards deed7 = new Cards(1,0);
		deeds.add(deed7);
		Cards deed8 = new Cards(0,1);
		deeds.add(deed8);
		Cards deed9 = new Cards(0,2);
		deeds.add(deed9);
		Cards deed10 = new Cards(0,3);
		deeds.add(deed10);
		Cards deed11 = new Cards(0,4);
		deeds.add(deed11);
		Cards deed12 = new Cards(0,5);
		deeds.add(deed12);
		Cards deed13 = new Cards(0,6);
		deeds.add(deed13);
		Cards deed14 = new Cards(0,8);
		deeds.add(deed14);
		Cards deed15 = new Cards(0,9);
		deeds.add(deed15);
		Cards deed16 = new Cards(1,10);
		deeds.add(deed16);
		Cards deed17 = new Cards(3,10);
		deeds.add(deed17);
		Cards deed18 = new Cards(4,10);
		deeds.add(deed18);
		Cards deed19 = new Cards(5,10);
		deeds.add(deed19);
		Cards deed20 = new Cards(6,10);
		deeds.add(deed20);
		Cards deed21 = new Cards(7,10);
		deeds.add(deed21);
		Cards deed22 = new Cards(8,10);
		deeds.add(deed22);
		Cards deed23 = new Cards(9,10);
		deeds.add(deed23);		
	}
	
	public void collectIncomeTax(Player p)
	{
		if (p.cash>200)
		{
			int temp = p.cash;
			temp = temp-200;
			p.updateCash(temp);
			
		}
		else
		{
			// call the function to end game for player, if no deeds left
		}
		
	}
	
	public void collectLuxuryTax(Player p)
	{
		if (p.cash>100)
		{
			int temp = p.cash;
			temp = temp-100;
			p.updateCash(temp);
			
		}
		else
		{
			// call the function to end game for player, if no deeds left
		}
	}
	
	
	public void giveLoan(Player p, Cards c)
	{
		
	}
	
	public void sellProperty(Player p, Cards c)
	{
		
	}
	
	

}
