// sep 25th 2:01
import java.util.HashMap;
// sep 25th 7:06

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Polynomial{
	// fields
	double[] coefficients;
	int[] exponents;
	
	// constructor
	public Polynomial() {
		this.coefficients = new double[0];
		this.exponents = new int[0];
	}
	
	public Polynomial(double[] coeff, int[] expo) {
		this.coefficients = new double[coeff.length];
		this.exponents = new int[coeff.length];
		for(int i = 0; i < coeff.length; i++) {
			this.coefficients[i] = coeff[i];
			this.exponents[i] = expo[i];
		}
	}
	
	// constructor that takes an argument of type File
	public Polynomial(File file) throws FileNotFoundException {
		ArrayList<Double> co_list = new ArrayList<>();
		ArrayList<Integer> exp_list = new ArrayList<>();
		
		Scanner input_file = new Scanner(file);
		String poly_string = input_file.nextLine();
		
		get_values(poly_string, co_list, exp_list);
		
		int result_len = co_list.size();
		coefficients = new double[result_len];
		exponents = new int[result_len];
		for(int i = 0; i < result_len; i++) {
			coefficients[i] = co_list.get(i);
			exponents[i] = exp_list.get(i);
		}
		
		input_file.close();
		
	}
	
	// constructor helper
	private void get_values(String poly, ArrayList<Double> co_list, ArrayList<Integer> exp_list) {
		String[] terms = poly.split("(?=[+-])");
		
		for(String term : terms) {
			String[] parts = term.split("x");
			double cur_coeff = 1.0;
			int cur_exp = 0;
			
			if(parts.length == 0)
				continue; // skil invalid parts
			else if(parts.length == 1)
				cur_coeff = Double.parseDouble(parts[0]); // only number is coeff, exp is default value 0
			else {
				// cur_coeff
				if(parts[0].equals("") || parts[0].equals("+"))
					continue; // 'x' or '+x': coeff=1.0
				else if(parts[0].equals("-"))
					cur_coeff = -1.0; // '-x': coeff=-1.0
				else
					cur_coeff = Double.parseDouble(parts[0]);
				
				// cur_exp
				if(parts[1].equals(""))
					continue; // exp is default value 0
				else
					cur_exp = Integer.parseInt(parts[1]);
			}
			
			co_list.add(cur_coeff);
			exp_list.add(cur_exp);	
		}
	}
	
	
	public Polynomial add(Polynomial to_add) {
		int new_len = Math.max(this.coefficients.length, to_add.coefficients.length);
		double[] new_co = new double[new_len];
		int[] new_exp = new int[new_len];		
		
		if(this.coefficients.length >= to_add.coefficients.length) {
			for(int i = 0; i < this.coefficients.length; i++) {
				new_co[i] = this.coefficients[i];
				new_exp[i] = this.exponents[i];
				for(int j = 0; j < to_add.coefficients.length; j++) {
					if (to_add.exponents[j] == this.exponents[i]) {
						new_co[i] += to_add.coefficients[j];
					}
				}
			}	
		} else {	
			for(int i = 0; i < to_add.coefficients.length; i++) {
				new_co[i] = to_add.coefficients[i];
				new_exp[i] = to_add.exponents[i];
				for(int j = 0; j < this.coefficients.length; j++) {
					new_co[i] += this.coefficients[j];
				}
			}
		}
		
		int non_zero = 0;
	    for (int i = 0; i < new_co.length; i++) {
	        if (new_co[i] != 0) {
	        	non_zero++;
	        }
	    }
	    
	    
	    // delete coefficients with value 0 and the corresponding exponents
	    double[] result_co = new double[non_zero];
		int[] result_exp = new int[non_zero];
		
		int index = 0;
		for (int i = 0; i < new_co.length; i++) {
	        if (new_co[i] != 0) {
	            result_co[index] = new_co[i];
	            result_exp[index] = new_exp[i];
	            index++;
	        }
		}
		
		Polynomial new_poly = new Polynomial(result_co, result_exp);
		
		return new_poly;
	}
	
	public double evaluate(double x) {
		double result = 0;
		for(int i = 0; i < this.coefficients.length; i++) {
			result = result + this.coefficients[i] * Math.pow(x,exponents[i]);
		}
		
		return result;
		
	}
	
	public boolean hasRoot(double val) {
		return (evaluate(val) == 0);
	}
	
	public Polynomial multiply(Polynomial to_multi) {
		
		/*int pivot_len = (this.coefficients.length * to_multi.coefficients.length);
		pivot_co = new double[pivot_len];
		pivot_exp = new int[pivot_len];
		int index = 0;
		
		for (int i = 0; i < this.coefficients.length; i++) {
			for(int j = 0; j < to_multi.coefficients.length; j++) {
				pivot_co[index] = this.coefficients[i]*to_multi.coefficients[j];
				pivot_exp[index] = this.exponents[i]+to_multi.exponents[j];
				index += 1;
			}
		}
		
		HashSet<Integer> distinct_exp = new HashSet<>();
		for(int i = 0; i < pivot_len; i++) {
			distinct_exp.add(pivot_exp[i]);
		}
		
		result_len = distinct_exp.size();
		result_co = new double[result_len] {0};
		result_exp = new int[result_len];
		Iterator<Integer> iterator = distinct_exp.iterator();
		for(int i = 0; i < result_len; i++) {
			result_exp[i] = iterator.next();
		}
		
		for (int i = 0; i < pivot_len; i++) {
			for(int j = 0; j < result_len; j++) {
				if(result_exp[j] == pivot_exp[i]) result_co[j] += pivot_co[i];
			}
		}*/
		
		HashMap<Integer, Double> result_map = new HashMap<>();
		
		for(int i = 0; i < this.coefficients.length; i++) {
			for(int j = 0; j < to_multi.coefficients.length; j++) {
				
				double new_co = this.coefficients[i] * to_multi.coefficients[j];
				int new_exp = this.exponents[i] + to_multi.exponents[j];
				
				result_map.put(new_exp, result_map.getOrDefault(new_exp, 0.0) + new_co);
					
			}
		}
		
		int result_len = result_map.size();
		double[] result_co = new double[result_len];
		int[] result_exp = new int[result_len];
		
		Integer[] keys = result_map.keySet().toArray(new Integer[0]);
		for(int i = 0; i < result_len; i++) {
			int cur_exp = keys[i];
			result_co[i] = result_map.get(cur_exp);
			result_exp[i] = cur_exp;
		}
		
		Polynomial result = new Polynomial(result_co, result_exp);
		return result;
		
	}
	
	public void saveToFile(String file_to_write) throws IOException {
		
		FileWriter fileWriter = new FileWriter(file_to_write);
        PrintWriter printWriter = new PrintWriter(fileWriter);
		
		StringBuilder poly_str = new StringBuilder();
		
		for(int i = 0; i < this.coefficients.length; i++) {
			
			double cur_coeff = this.coefficients[i];
			int cur_exp = this.exponents[i];
			
			if(cur_coeff < 0 && cur_coeff != -1) // cur_coeff < 0
				poly_str.append(cur_coeff);
			else if(cur_coeff == -1)
				poly_str.append("-");
			else { // cur_coeff > 0
				if(i == 0) {
					if(cur_coeff != 1) poly_str.append(cur_coeff);
				} else {
					poly_str.append("+");
					if(cur_coeff != 1) poly_str.append(cur_coeff);
				}
			}
			
			if(cur_exp == 0 && (cur_coeff == 1 || cur_coeff == -1))
				poly_str.append("1");
			if(cur_exp == 1)
				poly_str.append("x");
			else if(cur_exp > 1)
				poly_str.append("x").append(cur_exp);
			
		}
		
		printWriter.println(poly_str.toString());
		
		printWriter.close();
        fileWriter.close();
		
		
	}
	
	
}



