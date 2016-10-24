import java.util.HashSet;
import java.util.Scanner;


public class Player {

	private String name;
	private int location;
	private int balance;
	private HashSet<Properties> ownedProperty;
	private int housesOwned=0;
	private int hotelsOwned=0;
	private HashSet<Utilities> ownedUtilities;
	private HashSet<RailRoad> ownedRailRoad;


	public Player(){
		name = "";
		location = 0;
		balance = 1500;

	}

	public Player(String n, int l){
		name = n;
		location = l;
	}

	public String getName(){
		// to get player's name

		return name;
	}

	public void setName(String name) {
		// to set player's name
		this.name = name;
	}

	public int getLocation(){
		// returns current location of the player
		return location;
	}

	public void setBalance(int b){
		this.balance = b;
	}

	public void moveForward(int diceValue){
		// would move the player dicevalue squares forward on the board
		location = (location + diceValue) % 40;
	}

	public int getBalance(){
		return balance;
	}

	public void passGo(int diceValue){
		// This is to add $200 every time a player lands on or crosses "GO".
		for(int i = 0; i>40;i++ ){
			if(getLocation() + diceValue >= 40){
				int newBalance = getBalance() + 200;
				setBalance(newBalance);
			}
		}
	}

	public boolean landedOnGoToJail(){
		// to check if a player landed on Go To Jail square
		if(getLocation() == 30){
			return true;
		}
		return false;
	}

	public void sendtoInJail(){
		// if a player lands on go to jail he is sent to in jail square which 
		//is location 10 on board and is asked to pay $50 to get out of jail
		if(landedOnGoToJail() == true){
			location = 10;
			if(getBalance() > 50){
				System.out.println("Would you like to pay $50 to get out of jail? yes/no");
				Scanner scanner = new Scanner(System.in);
				String response = scanner.next();
				if(response.equals("yes")){

					int newBalance = balance-50;
					setBalance(newBalance);
				}
				scanner.close();
			}
		}
	}
	
	public boolean askToBuyProperty(Properties p, Bank b){
		// ask to buy a property
		if(b.getBankPropertiesSet().contains(p)){
			if(balance > p.getCost()){
				System.out.println("You can buy this! Do you want to? yes/no");
				Scanner scanner = new Scanner(System.in);
				String response = scanner.next();
				if(response.equals("yes")){
					scanner.close();

					return true;
				}
				scanner.close();
				return false;
			}
			return false;
		}
		return false;
	}


	public void buyProperty(Properties p, Bank b){
		// buy property from bank

		if(askToBuyProperty(p,b) == true){
			balance =  balance - p.getCost();
			ownedProperty.add(p);
			b.getBankPropertiesSet().remove(p);
		}
	}

	public boolean askToBuyUtility(Utilities u, Bank b){
		// ask to buy utility if the player has balance
		if(b.getBankUtilitySet().contains(u)){
			if(balance > u.getCost()){
				System.out.println("You can buy this! Do you want to? yes/no");
				Scanner scanner = new Scanner(System.in);
				String response = scanner.next();
				if(response.equals("yes")){
					scanner.close();

					return true;
				}
				scanner.close();
				return false;
			}
			return false;
		}
		return false;
	}

	public void buyUtility( Utilities u, Bank b){
		// buy utility from bank

		if(askToBuyUtility(u,b) == true){
			balance =  balance - u.getCost();
			ownedUtilities.add(u);
			b.getBankUtilitySet().remove(u);
		}
	}

	public boolean askToBuyRailRoad(RailRoad r, Bank b){
		// ask to buy a railroad if the player has balance
		if(b.getBankRailRoad().contains(r)){
			if(balance > r.getCost()){
				System.out.println("You can buy this! Do you want to? yes/no");
				Scanner scanner = new Scanner(System.in);
				String response = scanner.next();
				if(response.equals("yes")){
					scanner.close();
					return true;
				}
				scanner.close();
				return false;
			}
			return false;
		}
		return false;
	}

	public void buyRailRoad( RailRoad r, Bank b){
		// buy utility from bank

		if(askToBuyRailRoad(r,b) == true){
			balance =  balance - r.getCost();
			ownedRailRoad.add(r);
			b.getBankRailRoad().remove(r);
		}
	}

	public void payRent(Properties p){
		// pay rent to another player
		// by getting the rent from square.property 
		// subtracting the rent from player's balance
		// adding the rent in owner's balance

		int rent = p.getRentInitial();
		balance =  balance - rent;
		Player receiver = p.getOwner();
		receiver.balance = receiver.balance + rent;
	}

	public void payTax(int location){
		//		if(s instanceof Tax){
		//			if(s.Tax.name.equals("Luxury Tax ")){
		//				balance = balance - 100;
		//			}
		//			else if (s.Tax.name.equals("Income Tax")){
		//				balance = balance -200;
		//			}
		//		}
		if(location == 4){
			// income tax
			balance = balance - 200;
		}
		if(location == 38){
			// luxury tax
			balance = balance - 100;
		}

	}

	public void sellProperty(Properties p){

		if(ownedProperty.contains(p))
		{
			balance=balance+((1/2)*p.getCost());
			
			ownedProperty.remove(p);
		}
	}

	public void buyHouse(Properties p){
		// buy house on owned property
		if(ownedProperty.contains(p))
		{
			if(balance > p.getHouseCost())
			{
				balance=balance-p.getHouseCost();
				housesOwned++;
			}
		}
	}

	public void buyHotel(Properties p){
		// buy hotel on owned property
		if(ownedProperty.contains(p))
		{
			if(housesOwned>=4)
			{
				if(balance > p.getHotelCost())
				{
					balance=balance-p.getHotelCost();
					hotelsOwned++;
					housesOwned=0;
				}

			}
		}
	}
}