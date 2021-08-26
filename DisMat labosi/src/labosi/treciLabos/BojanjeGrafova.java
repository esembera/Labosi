package labosi.treciLabos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BojanjeGrafova{
	int boja[];
	int V;
	public BojanjeGrafova(int V) {
		this.V = V;
	}

	boolean provjera(int v, int graf[][], int boja[], int c){
		for (int i = 0; i < V; i++)
			if (graf[v][i] == 1 && c == boja[i])
				return false;
		return true;
	}

	boolean bojanjeGrafaUtil(int graf[][], int m, int boja[], int v){
		if (v == V)
			return true;

		for (int c = 1; c <= m; c++){
			if (provjera(v, graf, boja, c)){
				boja[v] = c;

				if (bojanjeGrafaUtil(graf, m,	boja, v + 1))
					return true;

				boja[v] = 0;
			}
		}

		return false;
	}

	boolean BojanjeGrafa(int graf[][], int m){
		boja = new int[V];
		for (int i = 0; i < V; i++)
			boja[i] = 0;

		if (!bojanjeGrafaUtil(graf, m, boja, 0)){
			return false;
		}

		return true;
	}

	public static void main(String args[]){
		
		Scanner in = new Scanner(System.in);
		
		System.out.printf("Unesite ime datoteke: ");
		
		String putanja = in.nextLine();
		
		//String putanja = "graf.txt";
		
		File file = new File(putanja);
		
		try {
			Scanner scanner = new Scanner(file);
			int n = scanner.nextInt();
			BojanjeGrafova Bojanje 
			= new BojanjeGrafova(n);		
			scanner.nextLine();
			
			int graf[][] = new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					graf[i][j]=scanner.nextInt();
				}
			}
			scanner.close();
			in.close();
		
		for(int i=1;i<=15;i++) {
			if(Bojanje.BojanjeGrafa(graf, i)) {
				System.out.println(i);
				break;
			}
			
		}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
