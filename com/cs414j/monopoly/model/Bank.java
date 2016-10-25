import java.util.HashSet;
import java.util.Set;

public class Bank {
	
	private Set<Utilities> bankUtilitySet = new HashSet<Utilities>();
	private Set<Properties> bankPropertiesSet = new HashSet<Properties>();
	private Set<RailRoad> bankRailRoad = new HashSet<RailRoad>();
	private int cashReceived;
	private int cashGranted;
	private int bankHouse;
	private int bankHotel;
	private Board boardInstance;
	private static Bank bankInstance;

	// Constructor for the bank
	public Bank() {
		// Creating Objects for squares and populating the sets respectively
		// p1 ...p22 are sequential properties and are in sequence with the board
		// for example p1 is property for location 1 on board "Mediterranean Avenue "
		// p3 is for location 6 on board "Oriental Avenue"
		boardInstance = Board.getInstance();
		Properties p1 = new Properties(1);
		bankPropertiesSet.add(p1);
		boardInstance.initializeStringProperties(1,p1);
		Properties p2 = new Properties(3);
		bankPropertiesSet.add(p2);
		boardInstance.initializeStringProperties(3,p2);
		Properties p3 = new Properties(6);
		bankPropertiesSet.add(p3);
		boardInstance.initializeStringProperties(6,p3);
		Properties p4 = new Properties(8);
		bankPropertiesSet.add(p4);
		boardInstance.initializeStringProperties(8,p4);
		Properties p5 = new Properties(9);
		bankPropertiesSet.add(p5);
		boardInstance.initializeStringProperties(9,p5);
		Properties p6 = new Properties(11);
		bankPropertiesSet.add(p6);
		boardInstance.initializeStringProperties(11,p6);
		Properties p7 = new Properties(13);
		bankPropertiesSet.add(p7);
		boardInstance.initializeStringProperties(13,p7);
		Properties p8 = new Properties(14);
		bankPropertiesSet.add(p8);
		boardInstance.initializeStringProperties(14,p8);
		Properties p9 = new Properties(16);
		bankPropertiesSet.add(p9);
		boardInstance.initializeStringProperties(16,p9);
		Properties p10 = new Properties(18);
		bankPropertiesSet.add(p10);
		boardInstance.initializeStringProperties(18,p10);
		Properties p11 = new Properties(19);
		bankPropertiesSet.add(p11);
		boardInstance.initializeStringProperties(19,p11);
		Properties p12 = new Properties(21);
		bankPropertiesSet.add(p12);
		boardInstance.initializeStringProperties(21,p12);
		Properties p13 = new Properties(23);
		bankPropertiesSet.add(p13);
		boardInstance.initializeStringProperties(23,p13);
		Properties p14 = new Properties(24);
		bankPropertiesSet.add(p14);
		boardInstance.initializeStringProperties(24,p14);
		Properties p15 = new Properties(26);
		bankPropertiesSet.add(p15);
		boardInstance.initializeStringProperties(26,p15);
		Properties p16 = new Properties(27);
		bankPropertiesSet.add(p16);
		boardInstance.initializeStringProperties(27,p16);
		Properties p17 = new Properties(29);
		bankPropertiesSet.add(p17);
		boardInstance.initializeStringProperties(29,p17);
		Properties p18 = new Properties(31);
		bankPropertiesSet.add(p18);
		boardInstance.initializeStringProperties(31,p18);
		Properties p19 = new Properties(32);
		bankPropertiesSet.add(p19);
		boardInstance.initializeStringProperties(32,p19);
		Properties p20 = new Properties(34);
		bankPropertiesSet.add(p20);
		boardInstance.initializeStringProperties(34,p20);
		Properties p21 = new Properties(37);
		bankPropertiesSet.add(p21);
		boardInstance.initializeStringProperties(37,p21);
		Properties p22 = new Properties(39);
		bankPropertiesSet.add(p22);
		boardInstance.initializeStringProperties(39,p22);
		
		// Creating objects for rail roads
		// similar logic is followed as explained above
		RailRoad r1 = new RailRoad(5);
		bankRailRoad.add(r1);
		boardInstance.initializeStringRailRoad(5,r1);
		RailRoad r2 = new RailRoad(15);
		bankRailRoad.add(r2);
		boardInstance.initializeStringRailRoad(15,r2);
		RailRoad r3 = new RailRoad(25);
		bankRailRoad.add(r3);
		boardInstance.initializeStringRailRoad(25,r3);
		RailRoad r4 = new RailRoad(35);
		bankRailRoad.add(r4);
		boardInstance.initializeStringRailRoad(35,r4);
		
		// Creating objects for Utilities
		Utilities u1 = new Utilities(12);
		bankUtilitySet.add(u1);
		boardInstance.initializeStringUtilities(12,u1);
		Utilities u2 = new Utilities(28);
		bankUtilitySet.add(u2);
		boardInstance.initializeStringUtilities(28,u2);
		
		// Creating Objects for Tax
		Tax t1 = new Tax(4);
		Tax t2 = new Tax(38);
		
				
	}
	// singleton 
	public static Bank getInstance() {
		if(bankInstance == null) {
			bankInstance = new Bank();
		}
		return bankInstance;
	}
	
	/*This functionality no more required 
	 * // Collect Income Tax from player
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
	
	// collect luxury tax from the player
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
	}*/
	
	// un mortgaging by the bank
	public void unMortgageProperty(Player p, Properties prop)
	{
		int unMortgageValue = prop.getMortgageValue();
		double interest = 0.10*unMortgageValue;
		int newInterest = (int) interest;
		unMortgageValue = unMortgageValue + newInterest;
		p.setBalance(p.getBalance() - unMortgageValue);
		Mortgage.getInstance().unMortgageProperty(prop);
		
	}
	
	public void unMortgageUtility(Player p, Utilities u)
	{
		int unMortgageValue = u.getMortgageValue();
		double interest = 0.10*unMortgageValue;
		int newInterest = (int) interest;
		unMortgageValue = unMortgageValue + newInterest;
		p.setBalance(p.getBalance() - unMortgageValue);
		Mortgage.getInstance().unMortgageUtilities(u);
	}
	
	public void unMortgageRailRoad(Player p, RailRoad r)
	{
		int unMortgageValue = r.getMortgageValue();
		double interest = 0.10*unMortgageValue;
		int newInterest = (int) interest;
		unMortgageValue = unMortgageValue + newInterest;
		p.setBalance(p.getBalance() - unMortgageValue);
		Mortgage.getInstance().unMortgageRailRoad(r);
	}
	
	
	// give loan to the player by mortgaging property
	public void giveLoanProperty(Player p, Properties prop, Mortgage m)
	{
		int cost = prop.getMortgageValue();
		int currentBalance = p.getBalance();
		p.setBalance(currentBalance+cost);
		m.mortgageProperty(prop);
		
	}
	
	// give loan to the player by mortgaging utilities
	public void giveLoanUtility(Player p, Utilities u, Mortgage m)
	{
		int cost = u.getMortgageValue();
		int currentBalance = p.getBalance();
		p.setBalance(currentBalance+cost);
		m.mortgageUtilities(u);
	}
	
	// give loan to the player by mortgaging railroad
	public void giveLoanRailRoad( Player p, RailRoad r, Mortgage m)
	{
		int cost = r.getMortgageValue();
		int currentBalance = p.getBalance();
		p.setBalance(currentBalance+cost);
		m.mortgageRailRoad(r);
	}

	/* This functionality is not required anymore, may be required at refactoring
	// sell the property owned by a player
	public void sellProperty(Player p, Properties prop)
	{
		
	}
	
	// sell the utilities owned by player
	public void sellUtilities (Player p, Utilities U)
	{
		
	}
	
	// sell the railroad owned by a player to bank
	public void sellRailRoad(Player p, RailRoad r)
	{
		
	}
	
	// add $200 cash to player wallet when he passes the Go on board
	public void addGoCash(Player p)
	{
		
	}
	*/

	/**
	 * @return the bankHouse
	 */
	public int getBankHouse() {
		return this.bankHouse;
	}

	/**
	 * @param bankHouse the bankHouse to set
	 */
	public void setBankHouse(int bankHouse) {
		this.bankHouse = bankHouse;
	}

	/**
	 * @return the bankHotel
	 */
	public int getBankHotel() {
		return this.bankHotel;
	}

	/**
	 * @param bankHotel the bankHotel to set
	 */
	public void setBankHotel(int bankHotel) {
		this.bankHotel = bankHotel;
	}

	/**
	 * @return the bankUtilitySet
	 */
	public Set<Utilities> getBankUtilitySet() {
		return this.bankUtilitySet;
	}

	/**
	 * @return the bankPropertiesSet
	 */
	public Set<Properties> getBankPropertiesSet() {
		return this.bankPropertiesSet;
	}

	/**
	 * @return the bankRailRoad
	 */
	public Set<RailRoad> getBankRailRoad() {
		return this.bankRailRoad;
	}

	/**
	 * @return the cashReceived
	 */
	public int getCashReceived() {
		return this.cashReceived;
	}

	/**
	 * @return the cashGranted
	 */
	public int getCashGranted() {
		return this.cashGranted;
	}
	

}
