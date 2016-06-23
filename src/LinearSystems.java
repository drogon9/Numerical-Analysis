/**
 * @author Drita Shujaku
 *
 */

public class LinearSystems {

	private static double TOL = 1E-7; //tolerance
	private static int N = 7; //maximum number of iterations
	
	public static void main(String[] args) {
		double[][] a = {{4, 3, 0},
						{3, 4, -1},
						{0, -1, 4}};
		double[] b = {24, 30, -24};
		double[] xo = {1, 1, 1};
		double w = 1.25;
		double[] result = sor(a, xo, b, w);
		for(int i = 0; i < result.length; i++) {
			System.out.println("x[" + i + "] = " + result[i]);
		}
	}
	/**
	 * sor solves Ax = b given the parameter w and an initial approximation x(0)
	 * @param a - the coefficient's matrix
	 * @param b - the entries of b
	 * @param w - the parameter w
	 * @return xo - the approximate solution
	 * */	
	public static double[] sor(double[][] a, double[] xo, 
							   double[] b, double w) {
		
		int n = a.length;
		double[] x = new double[n];
		int k = 1;
		while(k <= N) {
			for(int i = 0; i < n; i++) {
				x[i] = (1 - w) * xo[i] + (w * (- sum(i, a, x, xo) + b[i]))/a[i][i];
			}
			if(findlInfinit(x, xo) < TOL) {
				System.out.println("The tolerance has been achieved after " + --k + " iterations");
				return xo;
			}
			k++;
			for(int i = 0; i < n; i++) {
				xo[i] = x[i];
			}
		}
		System.out.println("Maximum number of iterations exceeded");
		return xo;
	}
	
	public static double sum(int row, double[][] a, double[] x, double[] xo) {
		double sum = 0;
		for(int j = 0; j < a.length; j++) {
			if(row == j)
				continue;
			else if (row < j) {
				sum += a[row][j] * xo[j];
			}
			else {
				sum += a[row][j] * x[j];
			}
		}
		return sum;
	}
	
	public static double findlInfinit(double[] x, double[] xo) {
	      double max = Math.abs(x[0] - xo[0]);
	      for(int i = 1; i < x.length; i++) {
	         if(Math.abs(x[i] - xo[i]) > max)
	            max = Math.abs(x[i]);
	      }
	      return max;
	   }
}
