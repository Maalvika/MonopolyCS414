package MonopolyCS414;

public class Property {

	private String name;
	private int price;
	private int rent;
	private int houseCount;
	private int hotelCount;
	public Player owner;
	public Property(String name,int price,int rent)
	{
		this.name=name;
		this.price=price;
		this.rent=rent;
	}
	
	public Player getOwner()
	{
		return owner;
	}
	public void setOwner(Player p)
	{
		owner=p;
	}
	public boolean isOwned()
	{
		if(getOwner()==null)
		{
			return false;
		}
		else return true;
	}
	public int gethouseCount()
	{
		return houseCount;
	}
	public void sethouseCount(int houseCount)
	{
		this.houseCount=houseCount;
	}
	public int gethotelCount()
	{
		return hotelCount;
	}
	public void sethotelCount(int hotelCount)
	{
		this.hotelCount=hotelCount;
	}
	public String getName()
	{
		return name;
	}
	public int getPrice()
	{
		return price;
	}
}
