package hr.fer.oop.lab4;

class ObradaPodataka {
	public static int obradi() {
		int broj;
		int velicina;
		int suma=0;
		int obradeno;
		velicina=DataSource.size();
		for(int i=0;i<velicina;i++) {
			try {
			 broj=DataSource.getNextNumber();
			 try {
				 obradeno=DataProcessUtil.processNumber(broj);
				 suma+=obradeno;
			 }catch(Exception e) {
				 obradeno=DataProcessUtil.handleException(e,broj);
				 suma+=obradeno;
			 }
			}catch(Exception e) {
				
			}
		}
		return suma;
			
	}
}
