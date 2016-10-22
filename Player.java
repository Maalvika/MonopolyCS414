import java.util.HashSet;
import java.util.Scanner;


public class Player {

	private String name;
	private int location;
	private int balance;
	private HashSet<Property> ownedProperty;


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

		return name;
	}

	public void setName(String name) {
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


	public boolean askToBuy(Property p){
		if(!p.isOwned()== true){	
			if(balance > p.getPrice()){
				System.out.println("You can buy this! Do you want to? yes/no");
				Scanner scanner = new Scanner(System.in);
				String response = scanner.next();
				if(response.equals("yes")){
					return true;
				}
				else return false;
			}
			return false;
		}
		return false;
	}


	public void buy(Property p){
		if(askToBuy(p) == true){
			balance =  balance - p.getPrice();
			//	p.setOwner(name);
			ownedProperty.add(p);

		}
	}

	public void payRent(int rent){
		// pay rent to another player

	}
	
	public void payTax(){
		
	}

	public void CollectRent(Player p){

	}

	public void sellProperty(Property p){

	}

	public void buyHouse(Property p){

	}
	
	public void buyHotel(Property p){
		
	}
	
	


}

