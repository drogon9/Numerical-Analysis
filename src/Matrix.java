/**
 * @author Drita Shujaku
 *
 */

/**Matrix has methods for find the determinant, inverse and traspose of a matrix*/
public class Matrix {

	/*public static void main(String[] args) {
		double[][] matrix = {	{1, 2, 4},
								{0, 1, 0},
								{3, -1, 2}	};
		System.out.println(determinant(matrix));
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				System.out.print(inverse(matrix)[i][j] + " ");
			}
			System.out.println();
		}
	}*/
	
	/**
	 * Returns the determinant of a given matrix using row expansion
	 * @param matrix - the matrix used to find its determinant
	 * @return the determinant
	 */
	public static double determinant(double[][] matrix){
		double sum = 0; 
		int s;
		if(matrix.length == 1){
			return(matrix[0][0]);
		}
		for(int i = 0; i < matrix.length; i++){
			double[][] smaller = new double[matrix.length-1][matrix.length-1]; //creates smaller matrix- values not in same row, column
			for(int a = 1; a < matrix.length; a++){ //finding the minor doesn't include elements of row 0, hence a starts at 1
				for(int b = 0; b < matrix.length; b++){
					if(b < i) {
						smaller[a-1][b] = matrix[a][b];
					}
					else if(b > i) {
						smaller[a-1][b-1] = matrix[a][b];
					}
				}
			}
			if(i % 2 == 0) {
				s = 1;
			}
			else {
				s = -1;
			}
			sum += s * matrix[0][i] * (determinant(smaller));
		}
		return sum;
	}
	
	/**
	 * inverse finds the inverse of a given matrix
	 * @param matrix - the matrix used to find its inverse
	 * @return the inverse
	 */
	public static double[][] inverse(double[][] matrix){
		int n = matrix.length;
		double[][] inverse = new double[n][n];
		double det = determinant(matrix);
		int s;
		if(n == 1){
			inverse[0][0] = 1.0 / matrix[0][0];
			return inverse;
		}
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++) {
				double[][] minor = new double[n-1][n-1];
				for(int a = 0; a < n; a++) {
					for(int b = 0; b < n; b++) {
						if(a == i || b == j)
							continue;
						else if(a < i && b < j) {
							minor[a][b] = matrix[a][b];
						}
						else if(a < i && b > j) {
							minor[a][b-1] = matrix[a][b];
						}
						else if(a > i && b < j) {
							minor[a-1][b] = matrix[a][b];
						}
						else {
							minor[a-1][b-1] = matrix[a][b];
						}
					}
				}
				if((i + j) % 2 == 0) {
					s = 1;
				}
				else {
					s = -1;
				}
				inverse[i][j] = s * determinant(minor) / det;
			}
		}
		return transpose(inverse);
	}
	
	/**
	 * Finds the transpose of a given matrix
	 * @param matrix - the matrix used to find its transpose
	 * @return the transpose
	 */
	public static double[][] transpose(double[][] matrix) {
		int n = matrix.length;
		double[][] tr = new double[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++)
				tr[i][j] = matrix[j][i];	
		}
		return tr;
	}
	/**
	 * Multiplies a matrix with a vector
	 * @param A - the matrix
	 * @param v - the vector
	 * @return result - the multiplication vector
	 */
	public static double[] multiply(double[][] A, double[] v) {
		double[] result = new double[A.length];
		if(A.length != A[0].length || A.length != v.length) {
			throw new RuntimeException("The square matrix and the vector should have the same length");
		}
		for (int i = 0; i < A.length; i++) {  
			for (int j = 0; j < v.length; j++) 
					result[i] += A[i][j] * v[j];
		}
		return result;
	}
}
