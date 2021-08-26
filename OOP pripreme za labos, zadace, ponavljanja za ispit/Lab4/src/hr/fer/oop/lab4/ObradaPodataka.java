package hr.fer.oop.lab4;

class PasswordStrognessChecker{
	private int malaSlova;
	private int velikaSlova;
	private int brojevi;
	private int specijalniZnakovi;
	
	public int[] analyzePassword(String password) throws WeakPasswordException{
	 int kriteriji[] = new int[4];	
	 int v=0, m=0, b=0, s=0;
	 for(int i=0;i<password.length();i++) {
		 if(password.charAt(i)>='a' && password.charAt(i)<='z') {
			 m++;
		 }else if(password.charAt(i)>='0' && password.charAt(i)<='9') {
			 b++;
		 }else if(password.charAt(i)>='A' && password.charAt(i)<='Z') {
			 v++;
		 }else {
			 s++;
		 }
		 kriteriji[0]=m;
		 kriteriji[1]=v;
		 kriteriji[2]=b;
		 kriteriji[3]=s;  
	 }
	 
	 return kriteriji;
	 
	}
	
	public PasswordStrognessChecker(int m, int v, int b, int s) {
		this.malaSlova=m;
		this.velikaSlova=v;
		this.brojevi=b;
		this.specijalniZnakovi=s;
	}
}


class WeakPasswordException extends Exception{
	public WeakPasswordException() {
		super("Password is too weak");
	}
}

