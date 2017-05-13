package au.com.unico.utils;

public class GCDCalculator {
	
	/**
	 * This method calculates the GCD of two numbers
	 * @param parameter1
	 * @param parameter2
	 * @return
	 */
	public static int calcualteGCD(int parameter1, int parameter2) {
		if (parameter2 == 0) {
			return parameter1;
		}
		return calcualteGCD(parameter2, parameter1 % parameter2);
	}

}
