package au.com.unico.utils;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class GCDCalculatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testCalcualteGCD() {
		assertEquals(GCDCalculator.calcualteGCD(20, 16), 4);
		assertEquals(GCDCalculator.calcualteGCD(200, 100), 100);
		assertEquals(GCDCalculator.calcualteGCD(100, 80), 20);
		assertEquals(GCDCalculator.calcualteGCD(25, 75), 25);
		
	}

}
