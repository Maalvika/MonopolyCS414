import java.util.HashSet;
import java.util.Set;

public class Bank {
	
	private Set<Utilities> bankUtilitySet = new HashSet<Utilities>();
	private Set<Properties> bankPropertiesSet = new HashSet<Properties>();
	private Set<RailRoad> bankRailRoad = new HashSet<RailRoad>();
	private int cashReceived;
	private int cashGranted;

	// Constructor for the bank
	public Bank() {
		// Creating Objects for squares and populating the sets respectively
		// p1 ...p22 are sequential properties and are in sequence with the board
		// for example p1 is property for location 1 on board "Mediterranean Avenue "
		// p3 is for location 6 on board "Oreintal Avenue"
		Properties p1 = new Properties(1);
		bankPropertiesSet.add(p1);
		Properties p2 = new Properties(3);
		bankPropertiesSet.add(p2);
		Properties p3 = new Properties(6);
		bankPropertiesSet.add(p3);
		Properties p4 = new Properties(8);
		bankPropertiesSet.add(p4);
		Properties p5 = new Properties(9);
		bankPropertiesSet.add(p5);
		Properties p6 = new Properties(11);
		bankPropertiesSet.add(p6);
		Properties p7 = new Properties(13);
		bankPropertiesSet.add(p7);
		Properties p8 = new Properties(14);
		bankPropertiesSet.add(p8);
		Properties p9 = new Properties(16);
		bankPropertiesSet.add(p9);
		Properties p10 = new Properties(18);
		bankPropertiesSet.add(p10);
		Properties p11 = new Properties(19);
		bankPropertiesSet.add(p11);
		Properties p12 = new Properties(21);
		bankPropertiesSet.add(p12);
		Properties p13 = new Properties(23);
		bankPropertiesSet.add(p13);
		Properties p14 = new Properties(24);
		bankPropertiesSet.add(p14);
		Properties p15 = new Properties(26);
		bankPropertiesSet.add(p15);
		Properties p16 = new Properties(27);
		bankPropertiesSet.add(p16);
		Properties p17 = new Properties(29);
		bankPropertiesSet.add(p17);
		Properties p18 = new Properties(31);
		bankPropertiesSet.add(p18);
		Properties p19 = new Properties(32);
		bankPropertiesSet.add(p19);
		Properties p20 = new Properties(34);
		bankPropertiesSet.add(p20);
		Properties p21 = new Properties(37);
		bankPropertiesSet.add(p21);
		Properties p22 = new Properties(39);
		bankPropertiesSet.add(p22);
		
		// Creating objects for rail roads
		// similar logic is followed as explained above
		RailRoad r1 = new RailRoad(5);
		bankRailRoad.add(r1);
		RailRoad r2 = new RailRoad(15);
		bankRailRoad.add(r2);
		RailRoad r3 = new RailRoad(25);
		bankRailRoad.add(r3);
		RailRoad r4 = new RailRoad(35);
		bankRailRoad.add(r4);
		
		// Creating objects for Utilities
		Utilities u1 = new Utilities(12);
		bankUtilitySet.add(u1);
		Utilities u2 = new Utilities(28);
		bankUtilitySet.add(u2);
		
		// Creating Objects for Tax
		Tax t1 = new Tax(4);
		Tax t2 = new Tax(38);
		
				
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
		int moneyLended = Mortgage.mortgageProperty(Cards c)
	}
	
	public void sellProperty(Player p, Cards c)
	{
		
	}
	
	

}
