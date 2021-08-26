package hr.fer.oop.novizad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeLoader {

	public static List<Employee> loadFromFile(String fileName) throws FileNotFoundException{
		FileReader fr = new FileReader(fileName);
		
		BufferedReader br = new BufferedReader(fr);
		List<Employee> lista = new ArrayList();
		
		try {
			String temp = br.readLine();
			while(temp!=null) {
				String[] temp1 = temp.split(";");
				lista.add(new Employee(temp1[0],Double.parseDouble(temp1[1]),new Date(Integer.parseInt(temp1[2]),Integer.parseInt(temp1[3]),Integer.parseInt(temp1[4]))));		
				temp=br.readLine();
			}
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
}
