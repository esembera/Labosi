package labosi.drugiLabos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class Main {
	
	public static int najvCiklus(int[][] matrix, int trenutni, int poc, int duzina, boolean[] proso) {
		int najveci=0;
		
		if(duzina > 1 && trenutni==poc)
			return duzina;
		
		
		proso[trenutni] = true;
		
		for(int i = 0; i < matrix.length; i++) {
			if(matrix[trenutni][i] == 1 && (i == poc || !proso[i])) {
				najveci = Math.max(najveci, najvCiklus(matrix, i, poc, duzina+1, proso.clone()));
			}
		}
		
		
		if(najveci <= 2)
			return 0;
		
		return najveci;
	}
	
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.printf("Unesite ime datoteke: ");
		String putanja = in.nextLine();
		
		File file = new File(putanja);
		
		try {
			Scanner scanner = new Scanner(file);
			int n = scanner.nextInt();
			scanner.nextLine();
			
			int matrix[][] = new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					matrix[i][j]=scanner.nextInt();
				}
			}
			
			scanner.close();
			in.close();
			
			int najveci = 0;
			for(int i=0;i<n;i++) {
				najveci=Math.max(najveci, najvCiklus(matrix,i,i,0,new boolean[n]));
			}
			
			System.out.println(najveci);
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
