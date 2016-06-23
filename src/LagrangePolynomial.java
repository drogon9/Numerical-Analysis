

public class LagrangePolynomial {
	
	public static double lagrangePolynomial(double x, double[] a, double[] b) {
		double P = 0;
		double[] L = new double[a.length];
		for(int i = 0; i < a.length; i++) {
			L[i] = 1;
			for(int j = 0; j < a.length; j++) {					
				if(i != j)
					L[i] *= (x - a[j])/(a[i] - a[j]);
			}
			P += L[i] * b[i];
		}
		return P;
	}
	
	public static void main(String[] args) {
		double[] a = {2, 2.75, 4};
		double[] b = {1.0/2, 1/2.75, 1.0/4};
		double d = lagrangePolynomial(3, a, b);
		System.out.println(d);
	}
}
