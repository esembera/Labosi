package jir.proslagodina.drugiZad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlasmanExplorer {
	List<Plasman> listaPlasmana = new ArrayList<Plasman>();

	public void loadPlasmani(String filepath) throws FileNotFoundException {
		FileReader fr = new FileReader(filepath);
		BufferedReader br = new BufferedReader(fr);
		
		String[] temp;
		
		try {
			String line = br.readLine();
			while(line!=null) {
					temp = line.split(";");
					listaPlasmana.add(new Plasman(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),temp[2],Integer.parseInt(temp[3])));
					line=br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printPobjednike(){
		listaPlasmana.stream()
			.filter(o -> o.getGodina() > 2000 && o.getPlasman()==1)
			.map(s -> s.getVozac())
			.sorted()
			.distinct()
			.forEach(s -> System.out.println(s));
	}
}