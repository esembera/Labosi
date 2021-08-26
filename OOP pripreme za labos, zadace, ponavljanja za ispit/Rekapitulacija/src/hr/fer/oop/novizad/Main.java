package hr.fer.oop.novizad;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(EmployeeLoader.loadFromFile("./Rekapitulacija/src/hr/fer/opp/auditorne9/Zaposlenici"));
	}

}
