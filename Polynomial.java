public class Polynomial{
	// fields
	double[] coefficients;
	
	// constructor
	public Polynomial() {
		coefficients = new double[] {0};
	}
	
	public Polynomial(double[] coeff) {
		coefficients = new double[coeff.length];
		for(int i = 0; i < coeff.length; i++) {
			coefficients[i] = coeff[i];
		}
	}
	
	
	public Polynomial add(Polynomial to_add) {
		int new_len = Math.max(coefficients.length, to_add.coefficients.length);
		double[] new_co = new double[new_len];
		
		for (int i = 0; i < new_len; i++) {
			double a = 0;
			if (i < coefficients.length) {
				a = coefficients[i];
			}
			double b = 0;
			if (i < to_add.coefficients.length) {
				b = to_add.coefficients[i];
			}
			
			new_co[i] = a + b;
		}
		
		Polynomial new_poly = new Polynomial(new_co);
		
		return new_poly;
	}
	
	public double evaluate(double x) {
		double result = 0;
		for(int i = 0; i < coefficients.length; i++) {
			result = result + coefficients[i] * Math.pow(x,i);
		}
		
		return result;
		
	}
	
	public boolean hasRoot(double val) {
		if(coefficients.length < 1) {
			return false;
		}
		
		double result = 0;
		for(int i = 0; i < coefficients.length; i++) {
			result = result + coefficients[i] * Math.pow(val,i);
		}
		
		return (result == 0);
	}
	
	
}

