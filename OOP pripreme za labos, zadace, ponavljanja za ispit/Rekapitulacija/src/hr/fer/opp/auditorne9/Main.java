package hr.fer.opp.auditorne9;

import java.io.FileNotFoundException;
import java.util.List;

import hr.fer.oop.novizad.EmployeeLoader;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String[] input = {"trava", "vatra", "vrata", "stari", "rista", "tisra"};
		 List<List<String>> result = Solution.groupAnagrams(input);
		 for(List<String> group: result) {
		 System.out.println(group);
		 }

		System.out.println(EmployeeLoader.loadFromFile("./Rekapitulacija/src/hr/fer/opp/auditorne9/Zaposlenici"));
		
		
	}

}
