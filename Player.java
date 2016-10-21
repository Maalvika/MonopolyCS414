import java.util.HashSet;
import java.util.Scanner;


public class Player {
	
	private String name;
    Token token;
    int location = 0;
    int balance;
    HashSet<Property> ownedProperty;
   
    
    public Player(String n, Token t, int l){
    	name = n;
    	token = t;
    	location = l;
    	balance = 1500;
    	
    }
    
    public String getName(){
    	return name;
    }
	
	public int getLocation(){
		return location;
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
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
