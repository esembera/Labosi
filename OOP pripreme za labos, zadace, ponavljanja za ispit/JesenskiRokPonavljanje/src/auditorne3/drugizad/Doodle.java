package auditorne3.drugizad;

import java.util.Scanner;

public class Doodle {
	 private static final String DOODLE_FORMAT = "|%-10s|%-10s|%n";
	 private static final String EOI = "END";
	 private SimpleArrayList names;
	 private SimpleArrayList times;
	 public Doodle() {
	 names = new SimpleArrayList();
	 times = new SimpleArrayList();
	 }
	 public static void main(String[] args) {
	 Doodle d = new Doodle();
	 d.askTime();
	 System.out.println(d);
	 }
	 public String toTable() {
	 StringBuilder sb = new StringBuilder();
	 sb.append(String.format(DOODLE_FORMAT, " ---- ", "Time")); 
	 int len = names.size();
	 for (int i = 0; i < len; i++) {
	 sb.append(String.format(DOODLE_FORMAT, names.get(i), times.get(i)));
	 }
	 return sb.toString();
	 }
	 // Override method
	 @Override
	 public String toString() {
	 return this.toTable();
	 }
	 public void askTime() {
	 String line = null;
	 Scanner sc = new Scanner(System.in);
	 while (true) {
	 System.out.println("Tell me your name or tell me to END.");
	 line = sc.nextLine();
	 if (line.equals(EOI)) {
	 break;
	 }
	 names.add(line);
	 String name = line;
	 int time = -1;
	 while(time < 0 || time > 23) {
	 System.out.println(name + ", when can you meet (0-23)?");
	 line = sc.nextLine();
	 time = Integer.parseInt(line);
	 }
	 times.add(Integer.valueOf(time));
	 }
	 sc.close();
	 }
	}