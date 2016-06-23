/**
 * @author Drita Shujaku
 *
 */

public class Newton {
	private static int N = 2;
	private static double TOL = 10E-7;
	public static double[] x = {0, 0, 0};
	
	public static void main(String[] args) {
		newton();
	}
	
	public static double function1() {
		double result = 4 * Math.pow(x[0], 2) - x[1] - 37;
		return result;
	}
	
	public static double function2() {
		return x[0] - Math.pow(x[1], 2) - 5;
	}
	
	public static double function3() {
		return x[0] + x[1] + x[2] - 3;
	}
	
	public static double[] function() {
		double[] function = new double[3];
		function[0] = function1();
		function[1] = function2();
		function[2] = function3();
		return function;
	}
	
	public static double[][] Jacobi() {
		double[][] matrix = new double[3][3];
		matrix[0][0] = 2 * x[0];
		matrix[0][1] = 1;
		matrix[0][2] = 0;
		matrix[1][0] = 1;
		matrix[1][1] = -2 * x[1];
		matrix[1][2] = 0;
		matrix[2][0] = 1;
		matrix[2][1] = 1;
		matrix[2][2] = 1;
		return matrix;
	}
	/**
	 * newton approximates the solution of the nonlinear system F(x) = 0 given an initial approximation x
	 * @return x - the approximate solution
	 */
	public static double[] newton() {
		double[] unknown = new double[3];
		double[] mult = new double[3];
		int k = 0;
		double[][] inv = new double[3][3];
		while(k < N) {
			for(int i = 0; i < 3; i++) {
				unknown[i] = x[i];
				for(int j = 0; j < 3; j++) {
					inv[i][j] = Matrix.inverse(Jacobi())[i][j];
					mult[i] +=  inv[i][j] * function()[j];
				}
			}
			for(int i = 0; i < 3; i++) {
				x[i] = unknown[i] - mult[i];
				mult[i] = 0;
			}
			if(infinitNorm(unknown, x) < TOL)
				return x;
			k++;
			System.out.print("\n" + k + ": ");
			for(int i = 0; i < 3; i++)
				System.out.print(x[i] + " ");
		}
		System.out.println("\nMaximum number of interations exceded");
		return x;
	}
	
	public static double infinitNorm(double[] k, double[] k_1) {
		double max = 0;
		double dif;
		for(int i = 0; i < k.length; i++) {
			dif = Math.abs(k[i] - k_1[i]);
			if(dif > max)
				max = dif;
		}
		return max;
	}
}
