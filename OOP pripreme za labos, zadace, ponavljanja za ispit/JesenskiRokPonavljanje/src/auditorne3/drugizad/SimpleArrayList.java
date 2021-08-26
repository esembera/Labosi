package auditorne3.drugizad;

public class SimpleArrayList {

	private int size = 2;
	private Object[] polje = new Object[size];
	private int n=0;

   public boolean add(Object o) {
	   if(n==size) {
		   size=size*2;
		   Object[] polje1 = new Object[size/2];
		   for(int i=0;i<size/2;i++) {
			 polje1[i]=polje[i];  
		   }
		   polje = new Object[size];
		   for(int i=0;i<size/2;i++) {
			 polje[i]=polje1[i];  
		   }
	   }
	   polje[n]=o;
	   n++;
	   return true;
   }
   
   public int size() {
	   return n;
   }
   
   public Object get(int index) {
	   return polje[index];
   }
   
   public int indexOf(Object o) {
	   for(int i=0;i<n;i++) {
		   if(o.equals(polje[i])) {
			   return i;
		   }
	   }
	   return -1;
   }
   
	public static void main(String[] args) {
		SimpleArrayList pimpek = new SimpleArrayList();
		
		pimpek.add("kita");
		pimpek.add(1);
		pimpek.add("markulin");
		pimpek.add(2);
		System.out.println(pimpek.size());
		System.out.println(pimpek.get(2));
		System.out.println(pimpek.indexOf(2));
		
		}
   
}
