package monopoly.cs414;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class PlayerTest {

	

	@Test
	public final void testBuyProperty() {
		Board board = new Board();
		Bank bank = new Bank();		
		Player p = new Player("Trump");
		Set<Utilities> ownedUtilities= new HashSet<Utilities>();
		Set<RailRoad> ownedRailRoad = new HashSet<RailRoad>();
		Set<Properties>ownedProperty = new HashSet<Properties>();
	
		assertEquals(22,bank.getBankPropertiesSet().size());
		p.buyProperty("Mediterranean Avenue", bank, board);
		ownedProperty = p.getOwnedProperties();
		Iterator<Properties> iter = ownedProperty.iterator();
		Properties prop = iter.next();
		assertEquals("Mediterranean Avenue",prop.getName());
		assertEquals(21,bank.getBankPropertiesSet().size());
		
		
		assertEquals(2,bank.getBankUtilitySet().size());
		p.buyProperty("Electric Co", bank, board);
		ownedUtilities = p.getOwnedUtilities();
		Iterator<Utilities> iter2 = ownedUtilities.iterator();
		Utilities ui = iter2.next();
		assertEquals("Electric Co",ui.getName());
		assertEquals(1,bank.getBankUtilitySet().size());
		
		assertEquals(4,bank.getBankRailRoad().size());
		p.buyProperty("Pennsylvania Railroad", bank, board);
		ownedRailRoad = p.getOwnedRailRoad();
		Iterator<RailRoad> iter3 = ownedRailRoad.iterator();
		RailRoad r1 = iter3.next();
		assertEquals("Pennsylvania Railroad",r1.getName());
		assertEquals(3,bank.getBankRailRoad().size());
		
		
	}
    @Test
	public final void testMortgageProperty() {
    	Board board = new Board();
		Bank bank = new Bank();		
		Player p = new Player("Trump");
		Set<Utilities> ownedUtilities= new HashSet<Utilities>();
		Set<RailRoad> ownedRailRoad = new HashSet<RailRoad>();
		Set<Properties>ownedProperty = new HashSet<Properties>();
		p.buyProperty("Mediterranean Avenue", bank, board);
		ownedProperty = p.getOwnedProperties();
		Iterator<Properties> iter = ownedProperty.iterator();
		Properties prop = iter.next();
		assertEquals("Mediterranean Avenue",prop.getName());
		assertEquals(21,bank.getBankPropertiesSet().size());
		
		
		assertEquals(2,bank.getBankUtilitySet().size());
		p.buyProperty("Electric Co", bank, board);
		ownedUtilities = p.getOwnedUtilities();
		Iterator<Utilities> iter2 = ownedUtilities.iterator();
		Utilities ui = iter2.next();
		assertEquals("Electric Co",ui.getName());
		assertEquals(1,bank.getBankUtilitySet().size());
		
		assertEquals(4,bank.getBankRailRoad().size());
		p.buyProperty("Pennsylvania Railroad", bank, board);
		ownedRailRoad = p.getOwnedRailRoad();
		Iterator<RailRoad> iter3 = ownedRailRoad.iterator();
		RailRoad r1 = iter3.next();
		assertEquals("Pennsylvania Railroad",r1.getName());
		assertEquals(3,bank.getBankRailRoad().size());
		
		// Mortgaging test
		//System.out.println(p.getBalance());
		p.mortgageProperty("Mediterranean Avenue", bank, board);
		//System.out.println(p.getBalance());
		assertEquals(1,p.getMortgageProperties().size());
		assertEquals(true,p.getOwnedProperties().isEmpty());
		assertEquals(1120,p.getBalance());
		
		p.mortgageProperty("Electric Co", bank, board);
		//System.out.println(p.getBalance());
		assertEquals(1,p.getMortgageUtilities().size());
		assertEquals(true,p.getOwnedUtilities().isEmpty());
		assertEquals(1195,p.getBalance());		
		
		p.mortgageProperty("Pennsylvania Railroad", bank, board);
		//System.out.println(p.getBalance());
		assertEquals(1,p.getMortgageRailRoad().size());
		assertEquals(true,p.getOwnedRailRoad().isEmpty());
		assertEquals(1295,p.getBalance());	
		
	}
	
}
