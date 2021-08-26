package labosi.prviLabos;

import java.util.Arrays;
import java.util.Scanner;


//klasa Matrix je preuzeta sa sljedeceg linka: https://github.com/rchen8/Algorithms/blob/master/Matrix.java

public class Matrix {

	private static double determinant(double[][] matrix) {
		if (matrix.length != matrix[0].length)
			throw new IllegalStateException("invalid dimensions");

		if (matrix.length == 2)
			return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

		double det = 0;
		for (int i = 0; i < matrix[0].length; i++)
			det += Math.pow(-1, i) * matrix[0][i]
					* determinant(minor(matrix, 0, i));
		return det;
	}

	private static double[][] inverse(double[][] matrix) {
		double[][] inverse = new double[matrix.length][matrix.length];

		// minors and cofactors
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				inverse[i][j] = Math.pow(-1, i + j)
						* determinant(minor(matrix, i, j));

		// adjugate and determinant
		double det = 1.0 / determinant(matrix);
		for (int i = 0; i < inverse.length; i++) {
			for (int j = 0; j <= i; j++) {
				double temp = inverse[i][j];
				inverse[i][j] = inverse[j][i] * det;
				inverse[j][i] = temp * det;
			}
		}

		return inverse;
	}

	private static double[][] minor(double[][] matrix, int row, int column) {
		double[][] minor = new double[matrix.length - 1][matrix.length - 1];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; i != row && j < matrix[i].length; j++)
				if (j != column)
					minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
		return minor;
	}

	private static double[][] multiply(double[][] a, double[][] b) {
		if (a[0].length != b.length)
			throw new IllegalStateException("invalid dimensions");

		double[][] matrix = new double[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				double sum = 0;
				for (int k = 0; k < a[i].length; k++)
					sum += a[i][k] * b[k][j];
				matrix[i][j] = sum;
			}
		}

		return matrix;
	}

	private static double[][] rref(double[][] matrix) {
		double[][] rref = new double[matrix.length][];
		for (int i = 0; i < matrix.length; i++)
			rref[i] = Arrays.copyOf(matrix[i], matrix[i].length);

		int r = 0;
		for (int c = 0; c < rref[0].length && r < rref.length; c++) {
			int j = r;
			for (int i = r + 1; i < rref.length; i++)
				if (Math.abs(rref[i][c]) > Math.abs(rref[j][c]))
					j = i;
			if (Math.abs(rref[j][c]) < 0.00001)
				continue;

			double[] temp = rref[j];
			rref[j] = rref[r];
			rref[r] = temp;

			double s = 1.0 / rref[r][c];
			for (j = 0; j < rref[0].length; j++)
				rref[r][j] *= s;
			for (int i = 0; i < rref.length; i++) {
				if (i != r) {
					double t = rref[i][c];
					for (j = 0; j < rref[0].length; j++)
						rref[i][j] -= t * rref[r][j];
				}
			}
			r++;
		}

		return rref;
	}

	private static double[][] transpose(double[][] matrix) {
		double[][] transpose = new double[matrix[0].length][matrix.length];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				transpose[j][i] = matrix[i][j];
		return transpose;
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Unesite prvo rjesenje x_0 karakteristicne jednadzbe: ");
		double x_0 = in.nextDouble();
		System.out.print("Unesite prvo rjesenje x_1 karakteristicne jednadzbe: ");
		double x_1 = in.nextDouble();
		System.out.print("Unesite prvo rjesenje x_2 karakteristicne jednadzbe: ");
		double x_2 = in.nextDouble();
		System.out.print("Unesite vrijednost nultog clana niza a_0: ");
		double a_0 = in.nextDouble();
		System.out.print("Unesite vrijednost nultog clana niza a_1: ");
		double a_1 = in.nextDouble();
		System.out.print("Unesite vrijednost nultog clana niza a_2: ");
		double a_2 = in.nextDouble();
		System.out.print("Unesite redni broj n trazenog clana niza: ");
		int n = in.nextInt();
		
		double[][] iksevi = {{1,1,1},{x_0,x_1,x_2},{x_0*x_0,x_1*x_1,x_2*x_2}};
		double[][] aovi = {{a_0},{a_1},{a_2}};
		double[][] lambde = multiply(inverse(iksevi),aovi);
		
		double ntiClan = lambde[0][0]*Math.pow(x_0, n) + lambde[1][0]*Math.pow(x_1, n) + lambde[2][0]*Math.pow(x_2, n);
		
		System.out.println("Vrijednost n-tog clana niza pomocu formule: " + Math.ceil(ntiClan));
		
		double c1 = x_0 + x_1 + x_2;
		double c2 = -(x_0*x_1)-(x_0*x_2)-(x_1*x_2);
		double c3 = x_0*x_1*x_2;
		
		double[] priv = new double[n+1];
		priv[0] = a_0;
		priv[1] = a_1;
		priv[2] = a_2;
		
		for(int i = 3; i <= n; i++) {
			priv[i]=c1*priv[i-1]+c2*priv[i-2]+c3*priv[i-3];
		}
		
		System.out.println("Vrijednost n-tog clana niza pomocu rekurzije: " + Math.ceil(priv[n]));
	
	}
}
