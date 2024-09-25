public class Driver {
	public static void main(String [] args) {
		
		double[] co1 = {2, -3.0, 1.0, 2};
		int[] exp1 = {3, 0, 2, 1};
		
		Polynomial p1 = new Polynomial(co1, exp1); 
		System.out.println("Polynomial p1: " + p1.evaluate(1));
        System.out.println("Polynomial p1: " + p1.evaluate(0));
		
		/*System.out.println(p.evaluate(3));
		double [] c1 = {6,0,0,5};
		Polynomial p1 = new Polynomial(c1);
		double [] c2 = {0,-2,0,0,-9};
		Polynomial p2 = new Polynomial(c2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		*/
		
	}
	
	
}