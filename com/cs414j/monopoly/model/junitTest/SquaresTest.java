package com.cs414j.monopoly.model.junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs414j.monopoly.model.Squares;

public class SquaresTest {

	@Test
	public final void testSquares() {
		Squares s1 = new Squares(1);
		Squares s2 = new Squares(4);
		Squares s3 = new Squares(5);
		Squares s4 = new Squares(39);
		Class<? extends Squares> temp = s1.getClass();
		assertEquals(temp,s1.getClass());
		temp = s2.getClass();
		assertEquals(temp,s2.getClass());
		temp = s3.getClass();
		assertEquals(temp,s3.getClass());
		temp = s4.getClass();
		assertEquals(temp,s4.getClass());
		
		
	}

}
