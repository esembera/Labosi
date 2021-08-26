package hr.fer.oop.novizad;

import java.util.Date;

public class Employee {
	String name;
	double placa;
	Date date;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPlaca() {
		return placa;
	}
	public void setPlaca(Double placa) {
		this.placa = placa;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void raiseSalary(double byPercent) {
		this.placa=placa*(1+byPercent/100);
	}
	
	public Employee(String Name, double placa, Date date) {
		this.date=date;
		this.name=name;
		this.placa=placa;
	}
}
