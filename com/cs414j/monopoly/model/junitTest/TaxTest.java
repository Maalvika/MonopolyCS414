package monopoly.cs414;

import static org.junit.Assert.*;

import org.junit.Test;

public class TaxTest {

	@Test
	public final void testTax() {
		Tax t1 = new Tax(4);
		Tax t2 = new Tax(38);
		int it = t1.getIncomeTax();
		assertEquals(200,it);
		int lt = t2.getLuxuryTax();
		assertEquals(100,lt);
		
	}

	@Test
	public final void testSetTaxValue() {
		Tax t1 = new Tax(4);
		Tax t2 = new Tax(38);
		t1.setTaxValue(4);
		t1.setTaxValue(38);
		int it = t1.getIncomeTax();
		assertEquals(200,it);
		int lt = t2.getLuxuryTax();
		assertEquals(100,lt);
		
	}

	@Test
	public final void testGetIncomeTax() {
		Tax t1 = new Tax(4);
		Tax t2 = new Tax(38);
		int it = t1.getIncomeTax();
		assertEquals(200,it);
		int lt = t2.getIncomeTax();
		assertEquals(0,lt);
	}

	@Test
	public final void testGetLuxuryTax() {
		Tax t1 = new Tax(4);
		Tax t2 = new Tax(38);
		int it = t1.getLuxuryTax();
		assertEquals(0,it);
		int lt = t2.getLuxuryTax();
		assertEquals(100,lt);
	}

}
