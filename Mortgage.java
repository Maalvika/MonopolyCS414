import java.util.HashSet;
import java.util.Set;

public class Mortgage {
	
	
	Set<Cards> mortgageSet = new HashSet<Cards>();
	
	public Mortgage() {
		// TODO Auto-generated constructor stub
	}

	public int mortgageProperty(int i, int j)
	{
		Cards cs = new Cards(i,j);
		return cs.mortgageValue;
		
	}
	
	public void addPropertyMortgage(Cards c)
	{
		mortgageSet.add(c);
	}
	
	public void unMortgageProperty()
	{
		
	}
	
	public void removePropertyMortgage(Cards c)
	{
		mortgageSet.remove(c);
	}
}
