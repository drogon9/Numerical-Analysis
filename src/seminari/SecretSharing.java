package seminari;

import java.awt.Point;
import java.util.Random;

import javax.swing.JOptionPane;

public class SecretSharing {
	
	private int secret;
	private int n; //num of participants
	private int k; //least number of participants needed to determine the secret
	private int[] a; //coefficients
	private static Point[] D;
	
	public SecretSharing() {
		retrieveInformation();
		a = new int[k];
		D = new Point[n];
		chooseCoefficients();
		findPoints();
	}

	private void chooseCoefficients() {
		a[0] = secret;
		Random r = new Random();
		for(int i = 1; i < k; i++)
			a[i] = r.nextInt(200);
	}

	private void retrieveInformation() {
		String secret = JOptionPane.showInputDialog("Type the secret number:");
		this.secret = new Integer(secret).intValue();
		String n = JOptionPane.showInputDialog("Type the number of participants:");
		this.n = new Integer(n).intValue();
		String k = JOptionPane.showInputDialog("Type the least number of participants needed to determine the secret:");
		this.k = new Integer(k).intValue();
	}
	
	public void findPoints() {
		int y;
		for(int i = 1; i <= n; i++) {
			y = generateShares(i);
			D[i-1] = new Point(i, y);
			System.out.println("D[" + (i-1) + "] = (" + i + ", " + y + ")");
		}
	}
	
	public int generateShares(int x) {
		int res = 0;
		for(int i = 0; i < k; i++)
			res += a[i] * Math.pow(x, i);
		return res;
	}

	public int lagrangePolynomial(double x, Point[] p) {
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
		return (int) Math.round(P);
	}
	
	public static void main(String[] args) {
		SecretSharing sc = new SecretSharing();
		Point[] p = {D[0], D[3], D[1], D[2]};
		System.out.println(sc.lagrangePolynomial(0, p));
	}
}
