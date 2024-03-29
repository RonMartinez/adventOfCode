package util;

public class MathHelper {

	public static long gcd(long a, long b) {
		while (b > 0) {
			long temp = b;
			b = a % b; // % is remainder
			a = temp;
		}
		return a;
	}

	public static long lcm(long a, long b) {
		return a * (b / gcd(a, b));
	}

}
