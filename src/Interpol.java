import java.awt.Point;

public class Interpol {
	
	public static void main(String[] args) {
		
		/*Point p0 = new Point(-9, 5);
		Point p1 = new Point(-4, 2);
		Point p2 = new Point(-1, -2);
		Point p3 = new Point(7, 9);
		Point[] p = {p0, p1, p2, p3};
		System.out.println(lagrangePolynomial(0, p));*/
		
	}
	
	public static double lagrangePolynomial(double x, Point[] p) {
		double P = 0;
		double[] L = new double[p.length];
		for(int i = 0; i < p.length; i++) {
			L[i] = 1;
			for(int j = 0; j < p.length; j++) {					
				if(i != j)
					L[i] *= (x - p[j].getX())/(p[i].getX() - p[j].getX());
			}
			P += L[i] * p[i].getY();
		}
		return P;
	}
}
